package com.sparta.dao.sql;

import com.sparta.dao.interfaces.DAO;
import com.sparta.entities.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class EmployeeDAO implements DAO<Employee> {
    private static Connection conn;
    private static EmployeeDAO instance;
    private static PreparedStatement findByIdPS;

    private EmployeeDAO() {}

    public static EmployeeDAO getInstance() {
        if (instance == null)
            instance = new EmployeeDAO();
        if (conn == null) {
            Properties props = new Properties();
            try {
                props.load(new FileReader("src/main/resources/dbconnect.properties"));
                conn = DriverManager.getConnection(
                        props.getProperty("mysql.url"),
                        props.getProperty("mysql.username"),
                        props.getProperty("mysql.password")
                );
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (findByIdPS == null) {
            try {
                findByIdPS = conn.prepareStatement(
                        "SELECT * FROM employees WHERE id = ?"
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    @Override
    public int insert(Employee newRow) {
        PreparedStatement insertStatement = null;
        int newId = newRow.getId();
        try {
            try {
                insertStatement = conn.prepareStatement(
                        "INSERT INTO employees(id, name_prefix, first_name, middle_name_initial, last_name, gender, email, date_birth, date_joined, salary)" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insertStatement.setInt(1, newRow.getId());
                insertStatement.setString(2, newRow.getNamePrefix());
                insertStatement.setString(3, newRow.getFirstName());
                insertStatement.setString(4, String.valueOf(newRow.getMiddleInitial()));
                insertStatement.setString(5, newRow.getLastName());
                insertStatement.setString(6, String.valueOf(newRow.getGender()));
                insertStatement.setString(7, newRow.getEMail());
                insertStatement.setDate(8, Date.valueOf(newRow.getDateOfBirth()));
                insertStatement.setDate(9, Date.valueOf(newRow.getDateOfJoining()));
                insertStatement.setInt(10, newRow.getSalary());
                insertStatement.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException e) {
                insertStatement = conn.prepareStatement(
                        "INSERT INTO employees(name_prefix, first_name, middle_name_initial, last_name, gender, email, date_birth, date_joined, salary)" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insertStatement.setString(1, newRow.getNamePrefix());
                insertStatement.setString(2, newRow.getFirstName());
                insertStatement.setString(3, String.valueOf(newRow.getMiddleInitial()));
                insertStatement.setString(4, newRow.getLastName());
                insertStatement.setString(5, String.valueOf(newRow.getGender()));
                insertStatement.setString(6, newRow.getEMail());
                insertStatement.setDate(7, Date.valueOf(newRow.getDateOfBirth()));
                insertStatement.setDate(8, Date.valueOf(newRow.getDateOfJoining()));
                insertStatement.setInt(9, newRow.getSalary());
                insertStatement.executeUpdate();

                Statement getIdStatement = conn.createStatement();
                ResultSet rs = getIdStatement.executeQuery(
                        "SELECT LAST_INSERT_ID(id) FROM employees ORDER BY id DESC LIMIT 1");
                rs.next();
                newId = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newId;
    }

    @Override
    public Employee findById(int id) {
        Employee result;
        try {
            findByIdPS.setInt(1, id);
            ResultSet rs = findByIdPS.executeQuery();
            rs.next();
            result = new Employee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4).charAt(0),
                    rs.getString(5),
                    rs.getString(6).charAt(0),
                    rs.getString(7),
                    rs.getDate(8).toLocalDate(),
                    rs.getDate(9).toLocalDate(),
                    rs.getInt(10)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void update(Employee updatedRow) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Employee> findAll() {
        return null;
    }
}
