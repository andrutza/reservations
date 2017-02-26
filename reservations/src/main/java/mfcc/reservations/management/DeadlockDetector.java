package mfcc.reservations.management;


import mfcc.reservations.management.model.Transaction;
import mfcc.reservations.management.model.WaitForGraphElement;
import mfcc.reservations.management.model.WaitForGraph;

import java.util.Set;

public class DeadlockDetector extends Thread{

    private Transaction detectDeadlock() {
        Set<WaitForGraphElement> waitForGraphElements = WaitForGraph.getWaitForGraphElements();
        for (WaitForGraphElement waitForGraphElement : waitForGraphElements) {
            if(!waitForGraphElement.getTransactions().isEmpty()
                    && isWaitingFor(waitForGraphElements, waitForGraphElement.getTransactions().get(0),
                    waitForGraphElement.getWaitingTransaction())) {
                if(waitForGraphElement.getTransactions().get(0).getDate().compareTo(waitForGraphElement.getWaitingTransaction().getDate()) < 0) {
                    return waitForGraphElement.getWaitingTransaction();
                } else {
                    return waitForGraphElement.getTransactions().get(0);
                }
            }
        }
        return null;
    }

    private boolean isWaitingFor(Set<WaitForGraphElement> waitForGraphElements, Transaction transaction, Transaction waitingTransaction) {
        for (WaitForGraphElement waitForGraphElement : waitForGraphElements) {
            if(waitForGraphElement.getWaitingTransaction().equals(transaction) && waitForGraphElement.getTransactions().contains(waitingTransaction)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            Transaction transaction = detectDeadlock();
            if(transaction != null) {
                System.out.println("Deadlock " + transaction);
                transaction.setAlive(false);
            }
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
