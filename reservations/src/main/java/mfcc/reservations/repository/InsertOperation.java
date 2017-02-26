package mfcc.reservations.repository;


import mfcc.reservations.model.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsertOperation extends DbOperation{

    public InsertOperation(Model data) {
        this.data = data;
    }

    @Override
    public List<Model> execute(Statement stmt) throws SQLException {
        String sql = "INSERT INTO " + data.getModelType().getTableName();
        switch (data.getModelType()){
            case ROOM:
                Room room = (Room) data;
                sql += " VALUES(" + room.getId() + ", " + room.getNumber() + ", " + room.getMaxCapacity() + ", " + room.getReservationId() + ")";
                break;
            case USER:
                User user = (User) data;
                sql += " VALUES(" + user.getId() + ", '" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getName() + "')";
                break;
            case RESERVATION:
                Reservation reservation = (Reservation) data;
                sql += "(`id`, `name`, `date`, `room_id`) VALUES(" + reservation.getId() + ", '" + reservation.getName() + "', '" + reservation.getDate() + "', " + reservation.getRoomId() + ")";
                break;
            case RESERVATIONDETAILS:
                ReservationDetails reservationDetails = (ReservationDetails) data;
                sql += " VALUES(" + reservationDetails.getId() + ", " + reservationDetails.getNumberOfPersons() + ", " + reservationDetails.getNumberOfRooms()  + ")";
                break;
        }
        stmt.execute(sql);
        return new ArrayList<>();
    }
}
