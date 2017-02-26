package reservations.repository;

import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import reservations.model.Reservation;
import reservations.model.ReservationDetails;
import reservations.model.Room;
import reservations.model.User;

import java.text.DateFormat;
import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestTemplateClient {

    public List<Reservation> getReservations() {
        final String uri = "http://localhost:8080/runInTransaction";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ArrayList<Map> operations = new ArrayList<>();
        operations.add(selectReservationDetails());
        operations.add(selectReservation());
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(operations), headers);
        List result = restTemplate.postForObject(uri, httpEntity, List.class);
        return RepositoryMapper.transformToReservationList((List<Map>) result.get(1));
    }

    public List<ReservationDetails> getReservationDetails() {
        final String uri = "http://localhost:8080/runInTransaction";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ArrayList<Map> operations = new ArrayList<>();
        operations.add(selectReservation());
        operations.add(selectReservationDetails());
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(operations), headers);
        List result = restTemplate.postForObject(uri, httpEntity, List.class);
        return RepositoryMapper.transformToReservationDetailsList((List<Map>) result.get(1));
    }

    private static Map selectReservation() {
        Map<String, Object> map = new HashMap<>();
        map.put("operationType", "SELECT");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("modelType","RESERVATION");
        map.put("data", dataMap);
        return map;
    }

    private static Map selectReservationDetails() {
        Map<String, Object> map = new HashMap<>();
        map.put("operationType", "SELECT");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("modelType","RESERVATIONDETAILS");
        map.put("data", dataMap);
        return map;
    }

    public List<Reservation> addReservation(Reservation reservation) {
        final String uri = "http://localhost:8080/runInTransaction";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ArrayList<Map> operations = new ArrayList<>();
        operations.add(insertReservation(reservation));
        operations.add(updateRoom(reservation.getRoomNo(), reservation.getId()));
        operations.add(selectReservation());
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(operations), headers);
        List result = restTemplate.postForObject(uri, httpEntity, List.class);
        return RepositoryMapper.transformToReservationList((List<Map>) result.get(2));
    }

    public List<Reservation> removeReservation(Integer reservationId, Integer roomId) {
        final String uri = "http://localhost:8080/runInTransaction";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ArrayList<Map> operations = new ArrayList<>();
        operations.add(deleteRoom(roomId));
        operations.add(deleteReservation(reservationId));
        operations.add(selectReservation());
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(operations), headers);
        List result = restTemplate.postForObject(uri, httpEntity, List.class);
        return RepositoryMapper.transformToReservationList((List<Map>) result.get(2));
    }

    private Map deleteRoom(Integer roomNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("operationType", "DELETE");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", roomNo);
        dataMap.put("modelType","ROOM");
        map.put("data", dataMap);
        return map;
    }

    private Map updateRoom(Integer roomNo, Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("operationType", "UPDATE");
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> dataForUpdate = new HashMap<>();
        dataForUpdate.put("reservation_id", id);
        dataMap.put("dataForUpdate", dataForUpdate);
        dataMap.put("modelType","ROOM");
        dataMap.put("id", roomNo);
        map.put("data", dataMap);
        return map;
    }

    private Map insertReservation(Reservation reservation) {
        Map<String, Object> map = new HashMap<>();
        map.put("operationType", "INSERT");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", reservation.getId());
        dataMap.put("name", reservation.getName());
        dataMap.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dataMap.put("roomId", reservation.getRoomNo());
        dataMap.put("modelType","RESERVATION");
        map.put("data", dataMap);
        return map;
    }

    private Map deleteReservation(Integer reservationId) {
        Map<String, Object> map = new HashMap<>();
        map.put("operationType", "DELETE");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", reservationId);
        dataMap.put("modelType","RESERVATION");
        map.put("data", dataMap);
        return map;
    }

    public List<Room> getRooms() {
        final String uri = "http://localhost:8080/runInTransaction";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ArrayList<Map> operations = new ArrayList<>();
        operations.add(selectRoom());
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(operations), headers);
        List result = restTemplate.postForObject(uri, httpEntity, List.class);
        return RepositoryMapper.transformToRoomList((List<Map>) result.get(0));
    }

    private Map selectRoom() {
        Map<String, Object> map = new HashMap<>();
        map.put("operationType", "SELECT");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("modelType","ROOM");
        map.put("data", dataMap);
        return map;
    }

    public List<Room> addRoom(Room room) {
        final String uri = "http://localhost:8080/runInTransaction";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ArrayList<Map> operations = new ArrayList<>();
        operations.add(insertRoom(room));
        operations.add(selectRoom());
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(operations), headers);
        List result = restTemplate.postForObject(uri, httpEntity, List.class);
        return RepositoryMapper.transformToRoomList((List<Map>) result.get(1));
    }

    private Map insertRoom(Room room) {
        Map<String, Object> map = new HashMap<>();
        map.put("operationType", "INSERT");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", room.getId());
        dataMap.put("number", room.getNumber());
        dataMap.put("maxCapacity", room.getMaxCapacity());
        dataMap.put("reservationId", 0);
        dataMap.put("modelType","ROOM");
        map.put("data", dataMap);
        return map;
    }

    public List<User> getUsers() {
        final String uri = "http://localhost:8080/runInTransaction";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ArrayList<Map> operations = new ArrayList<>();
        operations.add(selectUser());
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(operations), headers);
        List result = restTemplate.postForObject(uri, httpEntity, List.class);
        return RepositoryMapper.transformToUserList((List<Map>) result.get(0));
    }

    private Map selectUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("operationType", "SELECT");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("modelType","USER");
        map.put("data", dataMap);
        return map;
    }

    public List<User> updateUser(Integer userId, String password) {
        final String uri = "http://localhost:8080/runInTransaction";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ArrayList<Map> operations = new ArrayList<>();
        operations.add(modifyUser(userId, password));
        operations.add(selectUser());
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(operations), headers);
        List result = restTemplate.postForObject(uri, httpEntity, List.class);
        return RepositoryMapper.transformToUserList((List<Map>) result.get(1));
    }

    private Map modifyUser(Integer userId, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("operationType", "UPDATE");
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> dataForUpdate = new HashMap<>();
        dataForUpdate.put("password", password);
        dataMap.put("dataForUpdate", dataForUpdate);
        dataMap.put("modelType","USER");
        dataMap.put("id", userId);
        map.put("data", dataMap);
        return map;
    }
}
