package mfcc.reservations.management.model;

import mfcc.reservations.model.Model;

public class Operation {

    private OperationType operationType;
    private Model data;

    public Operation() {
    }

    public Operation(OperationType operationType, Model data) {
        this.operationType = operationType;
        this.data = data;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Model getData() {
        return data;
    }

    public LockType getLockType() {
        switch (operationType) {
            case INSERT:
                return LockType.WRITE;
            case DELETE:
                return LockType.WRITE;
            case UPDATE:
                return LockType.WRITE;
            case SELECT:
                return LockType.READ;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationType=" + operationType +
                ", data=" + data +
                '}';
    }
}
