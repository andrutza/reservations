package mfcc.reservations.management;

import mfcc.reservations.management.model.Lock;
import mfcc.reservations.management.model.Transaction;
import mfcc.reservations.management.model.LockType;
import mfcc.reservations.management.model.Operation;
import mfcc.reservations.model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager {

    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private static Map<Model, Lock> locks = new HashMap<>();

    public static boolean tryLock(Model model, LockType lockType, Transaction transaction) {
        boolean locked = reentrantLock.tryLock();
        if(!locked) {
            return false;
        }
        if(locks.get(model) == null) {
            return true;
        } else {
            if(locks.get(model).getTransactions().contains(transaction)) {
                removeTransactionFromLocks(model, transaction);
                return true;
            }
            if(locks.get(model).getType().equals(LockType.READ) && lockType.equals(LockType.READ)) {
                return true;
            }
            reentrantLock.unlock();
            return false;
        }
    }

    public static void releaseLocks(List<Operation> operations, Transaction transaction) {
        reentrantLock.lock();
        for (Operation operation : operations) {
            Lock lock = removeTransactionFromLocks(operation.getData(), transaction);
            if(lock != null) {
                System.out.println("Unlock: " + operation.getData() + " " + transaction);
            }
        }
        reentrantLock.unlock();
    }

    public static void addLock(Operation operation, Transaction transaction) {
        Lock lock = locks.get(operation.getData());
        if(lock != null) {
            lock.getTransactions().add(transaction);
            System.out.println("Update lock: " + operation + " " + transaction);
        } else {
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);
            locks.put(operation.getData(), new Lock(operation.getLockType(), transactions));
            System.out.println("Add lock: " + operation + " " + transaction);
        }
        reentrantLock.unlock();
    }

    public static List<Transaction> getLockingTransaction(Model data) {
        List<Transaction> transactions = null;
        reentrantLock.lock();
        Lock lock = locks.get(data);
        if(lock != null) {
            transactions = lock.getTransactions();
        }
        reentrantLock.unlock();
        return transactions;
    }

    private static Lock removeTransactionFromLocks(Model model, Transaction transaction) {
        Lock lock = locks.get(model);
        if(lock != null) {
            lock.getTransactions().remove(transaction);
            if(lock.getTransactions().size() == 0) {
                locks.remove(model);
            }
        }
        return lock;
    }
}
