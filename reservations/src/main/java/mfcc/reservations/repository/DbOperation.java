package mfcc.reservations.repository;


import mfcc.reservations.model.Model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DbOperation {

    protected Model data;

    protected abstract List<Model> execute(Statement stmt) throws SQLException;

    protected String getTableName() {
        return data.getModelType().getTableName();
    }
}

