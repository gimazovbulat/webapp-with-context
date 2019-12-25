package ru.itis.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionManager {
    private Connection connection;

    public DbConnectionManager(String username, String password, String url) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(url, username, password);

    }

    public Connection getConnection() {
        return connection;
    }
}
