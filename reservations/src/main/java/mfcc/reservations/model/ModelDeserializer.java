package mfcc.reservations.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ModelDeserializer extends StdDeserializer<Model> {
    public ModelDeserializer() {
        this(null);
    }

    public ModelDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Model deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Model model = null;
        Integer id = null;
        Map dataForUpdate = new HashMap();
        ModelType modelType = ModelType.valueOf(node.get("modelType").textValue());
        if(node.get("id") != null) {
            id = node.get("id").intValue();
        }
        if(node.get("dataForUpdate") != null) {
            dataForUpdate = new ObjectMapper().convertValue(node.get("dataForUpdate"), Map.class);
        }
        switch (modelType) {
            case ROOM:
                Integer number = getIntegerOrNull(node.get("number"));
                Integer maxCapacity = getIntegerOrNull(node.get("maxCapacity"));
                Integer reservationId = getIntegerOrNull(node.get("reservationId"));
                model = new Room(id, number, maxCapacity, reservationId);
                break;
            case USER:
                String username = getStringOrNull(node.get("username"));
                String password = getStringOrNull(node.get("password"));
                String name = getStringOrNull(node.get("name"));
                model = new User(id, username, password, name);
                break;
            case RESERVATION:
                String reservationName = getStringOrNull(node.get("name"));
                LocalDate date = getDateOrNull(node.get("date"));
                Integer roomId = getIntegerOrNull(node.get("roomId"));
                model = new Reservation(id, reservationName, date, roomId);
                break;
            case RESERVATIONDETAILS:
                Integer numberOfPersons = getIntegerOrNull(node.get("numberOfPersons"));
                Integer numberOfRooms = getIntegerOrNull(node.get("numberOfRooms"));
                model = new ReservationDetails(id, numberOfPersons, numberOfRooms);
                break;
        }
        model.setDataForUpdate(dataForUpdate);
        return model;
    }

    private Integer getIntegerOrNull(JsonNode jsonNode) {
        if(jsonNode != null) {
            return jsonNode.intValue();
        }
        return null;
    }

    private String getStringOrNull(JsonNode jsonNode) {
        if(jsonNode != null) {
            return jsonNode.textValue();
        }
        return null;
    }

    private LocalDate getDateOrNull(JsonNode jsonNode) {
        if(jsonNode != null) {
            return LocalDate.parse(jsonNode.textValue());
        }
        return null;
    }
}
