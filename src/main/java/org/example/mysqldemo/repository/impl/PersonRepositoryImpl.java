package org.example.mysqldemo.repository.impl;

import org.example.mysqldemo.config.AppConfiguration;
import org.example.mysqldemo.model.Person;
import org.example.mysqldemo.repository.PersonRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    private static final String INSERT = "insert into person(first_name, last_name) values (?, ?)";
    @Override
    public void save(Person person) {
        try (
                Connection connection = AppConfiguration.getNewConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        ) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static final String UPDATE = "update person set first_name = ?, last_name = ? where id = ?";
    @Override
    public void update(Person person) {
        try (
                Connection connection = AppConfiguration.getNewConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        ) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static final String GET = "select * from person where id = ?";
    @Override
    public Person get(Integer id) {
        Person person = null;
        try (
                Connection connection = AppConfiguration.getNewConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET);
        ) {
            preparedStatement.setInt(1, id);
            boolean result = preparedStatement.execute();
            if (result) {
                ResultSet rs = preparedStatement.getResultSet();
                while (rs.next()) {
                    person = new Person();
                    person.setId(rs.getInt("id"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                }
            } else {
                System.out.println("Person was not found.");
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return person;
    }

    private static final String DELETE = "delete from person where id = ?";
    @Override
    public void delete(Integer id) {
        try (
                Connection connection = AppConfiguration.getNewConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static final String SELECT_ALL = "select * from person";
    @Override
    public List<Person> list() {
        List<Person> list = new ArrayList<>();
        try (
                Connection connection = AppConfiguration.getNewConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ) {
            boolean result = preparedStatement.execute();
            if (result) {
                ResultSet rs = preparedStatement.getResultSet();
                while (rs.next()) {
                    Person person = new Person();
                    person.setId(rs.getInt("id"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    list.add(person);
                }
            } else {
                System.out.println("Something went wrong while querying database.");
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return list;
    }
}
