package mfcc.reservations.management.model;


import java.time.LocalDateTime;
import java.util.List;

public class Transaction {

    private int id;
    private boolean isAlive;
    private String name;
    private List<Operation> operations;
    private LocalDateTime date;

    public Transaction(int id, String name, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.isAlive = true;
    }

    public int getId() {
        return id;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "name='" + name + '\'' +
                '}';
    }
}
