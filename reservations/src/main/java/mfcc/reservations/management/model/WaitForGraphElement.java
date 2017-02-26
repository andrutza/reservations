package mfcc.reservations.management.model;


import mfcc.reservations.model.Model;

import java.util.List;

public class WaitForGraphElement {

    private Transaction waitingTransaction;
    private List<Transaction> transactions;
    private Model data;

    public WaitForGraphElement() {}

    public WaitForGraphElement(Transaction waitingTransaction, List<Transaction> transactions, Model data) {
        this.waitingTransaction = waitingTransaction;
        this.transactions = transactions;
        this.data = data;
    }

    public Transaction getWaitingTransaction() {
        return waitingTransaction;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Model getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WaitForGraphElement that = (WaitForGraphElement) o;

        if (waitingTransaction != null ? !waitingTransaction.equals(that.waitingTransaction) : that.waitingTransaction != null)
            return false;
        return data != null ? data.equals(that.data) : that.data == null;

    }

    @Override
    public int hashCode() {
        int result = waitingTransaction != null ? waitingTransaction.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
