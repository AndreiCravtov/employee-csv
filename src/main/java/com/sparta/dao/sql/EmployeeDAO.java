package com.sparta.dao.sql;

import com.sparta.dao.interfaces.DAO;
import com.sparta.entities.Employee;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class EmployeeDAO implements DAO<Employee> {
    private static EmployeeDAO instance;
    private final ConnectionPool connPool;

    private EmployeeDAO() {
        // get connection pool
        connPool = ConnectionPool.getInstance();
    }

    public static EmployeeDAO getInstance() {
        if (instance == null)
            instance = new EmployeeDAO();
        return instance;
    }

    @Override
    public int insert(Employee newRow) {
        // get connection
        Connection conn = connPool.borrowConnection();

        PreparedStatement insert;
        int newId = newRow.getId();
        try {
            try {
                insert = conn.prepareStatement(
                        "INSERT INTO employees(id, name_prefix, first_name, middle_name_initial, last_name, gender, email, date_birth, date_joined, salary)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insert.setInt(1, newRow.getId());
                insert.setString(2, newRow.getNamePrefix());
                insert.setString(3, newRow.getFirstName());
                insert.setString(4, String.valueOf(newRow.getMiddleInitial()));
                insert.setString(5, newRow.getLastName());
                insert.setString(6, String.valueOf(newRow.getGender()));
                insert.setString(7, newRow.getEMail());
                insert.setDate(8, Date.valueOf(newRow.getDateOfBirth()));
                insert.setDate(9, Date.valueOf(newRow.getDateOfJoining()));
                insert.setInt(10, newRow.getSalary());
                insert.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException e) {
                insert = conn.prepareStatement(
                        "INSERT INTO employees(name_prefix, first_name, middle_name_initial, last_name, gender, email, date_birth, date_joined, salary)" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insert.setString(1, newRow.getNamePrefix());
                insert.setString(2, newRow.getFirstName());
                insert.setString(3, String.valueOf(newRow.getMiddleInitial()));
                insert.setString(4, newRow.getLastName());
                insert.setString(5, String.valueOf(newRow.getGender()));
                insert.setString(6, newRow.getEMail());
                insert.setDate(7, Date.valueOf(newRow.getDateOfBirth()));
                insert.setDate(8, Date.valueOf(newRow.getDateOfJoining()));
                insert.setInt(9, newRow.getSalary());
                insert.executeUpdate();

                ResultSet rs = conn.createStatement().executeQuery("SELECT LAST_INSERT_ID(id) FROM employees ORDER BY LAST_INSERT_ID(id) DESC LIMIT 1");
                rs.next();
                newId = rs.getInt(1);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }

        // return connection
        connPool.returnConnection(conn);

        return newId;
    }

    @Override
    public void update(Employee updatedRow) {

    }

    @Override
    public Employee findById(int id) {
        // get connection
        Connection conn = connPool.borrowConnection();

        PreparedStatement find;
        Employee result;
        try {
            find = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
            find.setInt(1, id);
            ResultSet rs = find.executeQuery();
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
        } catch (SQLException e) { throw new RuntimeException(e); }

        // return connection
        connPool.returnConnection(conn);

        return result;
    }

    @Override
    public List<Employee> findAll() {
        // get connection
        Connection conn = connPool.borrowConnection();

        PreparedStatement find;
        ResultSet rs;
        List<Employee> result = new ArrayList<>();
        try {
            find = conn.prepareStatement("SELECT * FROM employees");
            rs = find.executeQuery();
            while (rs.next()) {
                result.add(new Employee(
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
                ));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }

        // return connection
        connPool.returnConnection(conn);

        return result;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {
        // get connection
        Connection conn = connPool.borrowConnection();

        try { conn.createStatement().execute("TRUNCATE employees"); }
        catch (SQLException e) { throw new RuntimeException(e); }

        // return connection
        connPool.returnConnection(conn);
    }
}
