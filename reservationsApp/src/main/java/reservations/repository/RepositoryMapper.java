package reservations.repository;

import reservations.model.Reservation;
import reservations.model.ReservationDetails;
import reservations.model.Room;
import reservations.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RepositoryMapper {

    public static List<Reservation> transformToReservationList(List<Map> rawList) {
        List<Reservation> reservations = new ArrayList<>();
        for (Map map : rawList) {
            Reservation reservation = new Reservation(
                    (Integer)map.get("id"),
                    map.get("name").toString(),
                    getDate((Map) map.get("date")),
                    (Integer)map.get("roomId")
            );
            reservations.add(reservation);
        }
        return reservations;
    }

    private static LocalDate getDate(Map map) {
        return LocalDate.of(
                (int) map.get("year"),
                (int) map.get("monthValue"),
                (int) map.get("dayOfMonth")
        );
    }

    public static List<User> transformToUserList(List<Map> rawList) {
        List<User> users = new ArrayList<>();
        for (Map map : rawList) {
            User user = new User((Integer)map.get("id"), map.get("username").toString(), map.get("password").toString(), map.get("name").toString());
            users.add(user);
        }
        return users;
    }

    public static List<Room> transformToRoomList(List<Map> rawList) {
        List<Room> rooms = new ArrayList<>();
        for (Map map : rawList) {
            Room room = new Room((Integer)map.get("id"), (Integer)map.get("number"), (Integer)map.get("maxCapacity"), (Integer)map.get("reservationId"));
            rooms.add(room);
        }
        return rooms;
    }

    public static List<ReservationDetails> transformToReservationDetailsList(List<Map> rawList) {
        List<ReservationDetails> reservationsDetails = new ArrayList<>();
        for (Map map : rawList) {
            ReservationDetails reservationDetails = new ReservationDetails((Integer)map.get("id"), (Integer)map.get("numberOfPersons"), (Integer)map.get("numberOfRooms"));
            reservationsDetails.add(reservationDetails);
        }
        return reservationsDetails;
    }

}
