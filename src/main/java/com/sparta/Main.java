package com.sparta;

import com.sparta.dao.interfaces.DAO;
import com.sparta.dao.sql.EmployeeDAO;
import com.sparta.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Employees employees = new Employees();
        List<String> err = new ArrayList<>();
        CSVConverter.convert("src/main/resources/EmployeeRecords1.csv", employees, err);

        DAO<Employee> employeeDAO = EmployeeDAO.getInstance();
        int newID = employeeDAO.insert(employees.getEmployees()[0]);
        System.out.println(employeeDAO.findById(newID));
    }
}