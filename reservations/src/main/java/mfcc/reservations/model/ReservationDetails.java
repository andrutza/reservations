package mfcc.reservations.model;


public class ReservationDetails extends Model{

    private Integer numberOfPersons;
    private Integer numberOfRooms;

    public ReservationDetails(Integer id, Integer numberOfPersons, Integer numberOfRooms) {
        super(id, ModelType.RESERVATIONDETAILS);
        this.numberOfPersons = numberOfPersons;
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }
}
