package reservations.model;


public class ReservationDetails{

    private int id;
    private int numberOfPersons;
    private int numberOfRooms;

    public ReservationDetails(int id, int numberOfPersons, int numberOfRooms) {
        this.numberOfPersons = numberOfPersons;
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }
}
