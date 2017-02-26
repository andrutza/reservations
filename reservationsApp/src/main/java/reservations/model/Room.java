package reservations.model;


public class Room {

    private int id;
    private int number;
    private int maxCapacity;
    private int reservationId;

    public Room(int id, int number, int capacity, int reservationId) {
        this.id = id;
        this.number = number;
        this.maxCapacity = capacity;
        this.reservationId = reservationId;
    }

    public Room() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getNumber() {
        return number;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
