package mfcc.reservations.model;


public class Room extends Model {

    private Integer number;
    private Integer maxCapacity;
    private Integer reservationId;

    public Room(Integer id, Integer number, Integer capacity, Integer reservationId) {
        super(id, ModelType.ROOM);
        this.number = number;
        this.maxCapacity = capacity;
        this.reservationId = reservationId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }
}
