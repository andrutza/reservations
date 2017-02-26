package mfcc.reservations.model;


public enum ModelType {
    ROOM("room"),
    USER("user"),
    RESERVATION("reservation"),
    RESERVATIONDETAILS("reservation_details");

    private String tableName;

    ModelType(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
