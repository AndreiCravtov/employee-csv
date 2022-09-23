package com.sparta.dao.sql;

import com.sparta.dao.interfaces.DAO;
import com.sparta.entities.Employee;

public class EmployeeDAO implements DAO<Employee> {

    private static EmployeeDAO instance = null;

    private EmployeeDAO() {}

    public static EmployeeDAO getInstance() {
        if (instance == null)
            instance = new EmployeeDAO();
        return instance;
    }

    @Override
    public void insert(Employee newRow) {

    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public void update(Employee updatedRow) {

    }

    @Override
    public void deleteById(int id) {

    }
}
