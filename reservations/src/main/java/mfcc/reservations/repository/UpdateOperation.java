package mfcc.reservations.repository;


import mfcc.reservations.model.Model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateOperation extends DbOperation{

    private Map<String, Object> dataToUpdate;

    public UpdateOperation(Model data, Map<String, Object> dataForUpdate) {
        this.data = data;
        this.dataToUpdate = dataForUpdate;

    }

    @Override
    public List<Model> execute(Statement stmt) throws SQLException {
        String sql = "UPDATE  " + getTableName();
        sql += " SET ";
        int size = dataToUpdate.size();
        for(Map.Entry entry : dataToUpdate.entrySet()) {
            if(size == 1) {
                sql += entry.getKey() + "='" + entry.getValue() + "'";
            } else {
                sql += entry.getKey() + "='" + entry.getValue() + "',";
            }
            size--;
        }
        sql += " WHERE id=" + data.getId();
        stmt.executeUpdate(sql);
        return new ArrayList<>();
    }
}
