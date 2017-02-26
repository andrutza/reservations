package mfcc.reservations.model;


import java.time.LocalDate;

public class Reservation extends Model{

    private String name;
    private LocalDate date;
    private Integer roomId;

    public Reservation(Integer id, String name, LocalDate date, Integer roomId) {
        super(id, ModelType.RESERVATION);
        this.name = name;
        this.date = date;
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getRoomId() {
        return roomId;
    }
}
