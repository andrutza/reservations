package mfcc.reservations.management.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WaitForGraph {

    private static Set<WaitForGraphElement> waitForGraph = new HashSet<>();

    public synchronized static Set<WaitForGraphElement> getWaitForGraphElements() {
        return waitForGraph;
    }

    public synchronized static void addElement(Transaction transaction, Operation operation, List<Transaction> waitingFor) {
        waitForGraph.add(new WaitForGraphElement(
                transaction,
                waitingFor,
                operation.getData()
        ));
    }

    public synchronized static void remove(Transaction transaction, Operation operation) {
        waitForGraph.remove(
                new WaitForGraphElement(transaction, null, operation.getData())
        );
    }
}
