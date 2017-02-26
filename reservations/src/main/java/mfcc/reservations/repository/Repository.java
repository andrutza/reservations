package mfcc.reservations.repository;

import mfcc.reservations.management.model.OperationType;
import mfcc.reservations.model.*;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class Repository {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String FIRST_DB_URL = "jdbc:mysql://localhost/reservations_data";
    static final String SECOND_DB_URL = "jdbc:mysql://localhost/reservations_management";

    static final String USER = "root";
    static final String PASS = "";

    public void processOperation(int id, ModelType modelType, Model data, Map<String,Object> dataForUpdate, OperationType operationType) {

    }

    private void cleanUp(Connection firstConn, Connection secondConn, Statement stmt, ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
            }
            if (stmt != null)
                stmt.close();
        } catch (SQLException ignored) {
        }// nothing we can do
        try {
            if (firstConn != null)
                firstConn.close();
            if (secondConn != null)
                secondConn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }//end finally try
    }

    private Statement initializeStatement(Connection firstConnection, Connection secondConnection, String tableName) throws SQLException {
        DatabaseMetaData firstDatabaseMetaData = firstConnection.getMetaData();
        DatabaseMetaData secondDatabaseMetaData = secondConnection.getMetaData();
        ResultSet resultSet = firstDatabaseMetaData.getTables(null, null, "%", null);
        while(resultSet.next()) {
            if(resultSet.getString(3).equals(tableName)) {
                return firstConnection.createStatement();
            }
        }
        resultSet = secondDatabaseMetaData.getTables(null, null, "%", null);
        while(resultSet.next()) {
            if(resultSet.getString(3).equals(tableName)) {
                return secondConnection.createStatement();
            }
        }
        return null;
    }

    public List<Model> processOperation(DbOperation dbOperation) throws RepositoryException {
        Connection firstConn = null;
        Connection secondConn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);
            firstConn = DriverManager.getConnection(FIRST_DB_URL, USER, PASS);
            secondConn = DriverManager.getConnection(SECOND_DB_URL, USER, PASS);
            stmt = initializeStatement(firstConn, secondConn, dbOperation.getTableName());
            return dbOperation.execute(stmt);
        } catch (Exception e) {
            throw new RepositoryException("Operation failed!");
        } finally {
            cleanUp(firstConn, secondConn, stmt, rs);
        }
    }
}
