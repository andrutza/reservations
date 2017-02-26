package mfcc.reservations.repository;


import mfcc.reservations.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectOperation extends DbOperation{

    public SelectOperation(Model data) {
        this.data = data;
    }

    @Override
    public List<Model> execute(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM " + data.getModelType().getTableName();
        if(data.getId() != null){
            sql+= " WHERE id = " + data.getId();
        }
        ResultSet rs = stmt.executeQuery(sql);
        return transformResultSetToModel(rs, data.getModelType());
    }

    private List<Model> transformResultSetToModel(ResultSet rs, ModelType type) throws SQLException {
        List<Model> result = new ArrayList<>();
        while(rs.next()) {
            switch (type){
                case ROOM:
                    result.add(new Room(rs.getInt("id"), rs.getInt("no"), rs.getInt("max_capacity"), rs.getInt("reservation_id")));
                    break;
                case USER:
                    result.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("name")));
                    break;
                case RESERVATION:
                    result.add(new Reservation(rs.getInt("id"), rs.getString("name"), rs.getDate("date").toLocalDate(), rs.getInt("room_id")));
                    break;
                case RESERVATIONDETAILS:
                    result.add(new ReservationDetails(rs.getInt("id"), rs.getInt("no_persons"), rs.getInt("no_rooms")));
                    break;
            }
        }

        return result;
    }

}
