package com.student.misc.database.db;

import javax.sql.DataSource;
import java.sql.Connection;

public class EntityManagerDb implements EntityManagerI{
//    @Resource(lookup = "java:jboss/datasources/ProjectDS")
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws Exception{
        return this.dataSource.getConnection();
    }
}
