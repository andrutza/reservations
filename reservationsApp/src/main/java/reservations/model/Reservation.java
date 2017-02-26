package reservations.model;


import java.time.LocalDate;

public class Reservation{

    private Integer id;
    private String name;
    private LocalDate date;
    private Integer roomNo;

    public Reservation() {
    }

    public Reservation(Integer id, String name, LocalDate date, Integer roomNo) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.roomNo = roomNo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }
}
