package org.example.mysqldemo.config;

import org.example.mysqldemo.repository.PersonRepository;
import org.example.mysqldemo.repository.impl.PersonRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppConfiguration {

    public static Connection getNewConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "anton";
        String pass = "root";
        return DriverManager.getConnection(url, user, pass);
    }

    public static PersonRepository getPersonRepository() {
        return new PersonRepositoryImpl();
    }

}
