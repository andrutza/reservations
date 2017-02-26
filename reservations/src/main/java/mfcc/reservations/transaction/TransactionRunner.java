package mfcc.reservations.transaction;

import mfcc.reservations.management.model.Operation;
import mfcc.reservations.management.model.OperationType;
import mfcc.reservations.management.model.Transaction;
import mfcc.reservations.management.model.WaitForGraph;
import mfcc.reservations.management.TransactionController;
import mfcc.reservations.management.LockManager;
import mfcc.reservations.model.Model;
import mfcc.reservations.model.ModelType;
import mfcc.reservations.model.Room;
import mfcc.reservations.repository.*;

import java.util.*;

public class TransactionRunner {

    private Repository repository = new Repository();

    public List<List<Model>> runTransaction(List<Operation> operations) throws InterruptedException {
        List<List<Model>> selectResults = new ArrayList<>();
        Transaction transaction = TransactionController.generateTransaction();
        System.out.println("Starting transaction " + transaction.getName());
        Stack<Operation> undoOperations = new Stack<>();
        for (Operation operation : operations) {
            Operation undoOperation = prepareUndoOperation(operation);
            undoOperations.push(undoOperation);
            acquireLock(transaction, operation);
            if (!transaction.isAlive()) {
                System.out.println("Aborted: " + transaction);
                break;
            }
            List<Model> result = tryExecute(operation, undoOperation);
            selectResults.add(result);
            Thread.sleep(2000);
        }

        if(!transaction.isAlive()) {
            while(!undoOperations.empty()) {
                try {
                    Operation operation = undoOperations.pop();
                    System.out.println("Executing undo operation " + operation);
                    execute(operation);
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
            }
            LockManager.releaseLocks(operations, transaction);
            Thread.sleep(1000);
            return runTransaction(operations);
        } else {
            LockManager.releaseLocks(operations, transaction);
            System.out.println("Finished transaction " + transaction);
            return selectResults;
        }
    }

    private List<Model> tryExecute(Operation operation, Operation undoOperation) throws InterruptedException {
        try {
            System.out.println("Executing operation " + operation);
            return execute(operation);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
            rollback(undoOperation);
            Thread.sleep(10000);
            return tryExecute(operation, undoOperation);
        }
    }

    private List<Model> execute(Operation operation) throws RepositoryException {
        DbOperation dbOperation = null;
        switch (operation.getOperationType()){
            case INSERT:
                dbOperation = new InsertOperation(operation.getData());
                break;
            case DELETE:
                dbOperation = new DeleteOperation(operation.getData());
                break;
            case UPDATE:
                dbOperation = new UpdateOperation(operation.getData(), operation.getData().getDataForUpdate());
                break;
            case SELECT:
                dbOperation = new SelectOperation(operation.getData());
                break;
        }
        return repository.processOperation(dbOperation);
    }

    private void rollback(Operation operation){
        try {
            System.out.println("Executing undo operation " + operation);
            execute(operation);
        } catch (RepositoryException e) {
            e.getMessage();
        }
    }

    private void acquireLock(Transaction transaction, Operation operation) throws InterruptedException {
        while(transaction.isAlive() && !LockManager.tryLock(operation.getData(), operation.getLockType(), transaction)) {
            List<Transaction> waitingForTransactions = LockManager.getLockingTransaction(operation.getData());
            WaitForGraph.addElement(transaction, operation, waitingForTransactions);
            Thread.sleep(200);
        }
        WaitForGraph.remove(transaction, operation);
        if(!transaction.isAlive()) {
            return;
        }
        LockManager.addLock(operation, transaction);
    }

    private Operation prepareUndoOperation(Operation operation) {
        if(operation.getOperationType().equals(OperationType.INSERT)) {
            return new Operation(OperationType.DELETE, operation.getData());
        } else if(operation.getOperationType().equals(OperationType.DELETE)) {
            return new Operation(OperationType.INSERT, operation.getData());
        } else if(operation.getOperationType().equals(OperationType.UPDATE)) {
            SelectOperation selectOperation = new SelectOperation(operation.getData());
            List<Model> dataBeforeUpdate = null;
            try {
                dataBeforeUpdate = new Repository().processOperation(selectOperation);
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
            Model model = dataBeforeUpdate.get(0);
            Map dataForUpdate = getDataForUpdate(model);
            model.setDataForUpdate(dataForUpdate);
            return new Operation(OperationType.UPDATE, model);
        } else {
            return new Operation(OperationType.SELECT, operation.getData());
        }
    }

    private Map getDataForUpdate(Model model) {
        Map map = new HashMap();
        switch (model.getModelType()) {
            case ROOM:
                Room room = (Room) model;
                map.put("no", room.getNumber());
                map.put("max_capacity", room.getMaxCapacity());
                map.put("reservation_id", room.getReservationId());
                break;
            case USER:
                break;
            case RESERVATION:
                break;
            case RESERVATIONDETAILS:
                break;
        }
        return map;
    }

}
