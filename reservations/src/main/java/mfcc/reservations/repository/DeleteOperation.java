package mfcc.reservations.repository;


import mfcc.reservations.model.Model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteOperation extends DbOperation{

    public DeleteOperation(Model data) {
        this.data = data;
    }

    @Override
    public List<Model> execute(Statement stmt) throws SQLException {
        String sql = "DELETE FROM  " + data.getModelType().getTableName() + " WHERE id=" + data.getId();
        stmt.executeUpdate(sql);
        return new ArrayList<>();
    }
}
