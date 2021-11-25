package com.student.database;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConn {

//    @Resource(lookup = "java:jboss/datasources/ProjectDS")
    private DataSource dataSource;

    PreparedStatement statement;



    public Connection getDbConnection() throws SQLException {
        return dataSource.getConnection();

    }

    public ResultSet execQuery(String sql) {
        if (sql == null || sql.trim().equals(""))
            return null;

        try {
            statement = getDbConnection().prepareStatement(sql);
            return statement.executeQuery(sql);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return null;

    }

    public boolean execUpdate(String sql) {
        if (sql == null || sql.trim().equals(""))
            return false;
        try {
            statement = getDbConnection().prepareStatement(sql);
            statement.executeUpdate(sql);
            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

    public void closeConnection() {

    }

    @Override
    protected void finalize() throws Throwable {
        this.closeConnection();
    }
}
