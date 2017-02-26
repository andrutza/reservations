package mfcc.reservations.management.model;


import java.util.List;

public class Lock {

    private LockType type;
    private List<Transaction> transactions;

    public Lock(LockType type, List<Transaction> transactions) {
        this.type = type;
        this.transactions = transactions;
    }

    public LockType getType() {
        return type;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "type=" + type +
                '}';
    }
}
