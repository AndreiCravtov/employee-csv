package com.sparta;

import com.sparta.exceptions.EmployeeDifferentDataSameID;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Employees {
    private final String header = "Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary";
    private Map<Integer, Employee> employees = new HashMap<>();

    public Employee[] getEmployees() {
        return employees.values().toArray(new Employee[0]);
    }

    public void addEmployee(Employee employee) throws EmployeeDifferentDataSameID {
        int id = employee.getEmployeeID();
        if (employees.containsKey(id))
            if (employee.equals(employees.get(id))) return;
            else throw new EmployeeDifferentDataSameID(); // same ID different data!! handle this via a queue
        employees.put(id, employee);
    }

    public void addEmployee(
            int employeeID,
            String namePrefix,
            String firstName,
            char middleInitial,
            String lastName,
            char gender,
            String eMail,
            String dateOfBirth,
            String dateOfJoining,
            int salary
    ) throws EmployeeDifferentDataSameID {
        addEmployee(new Employee(employeeID, namePrefix, firstName, middleInitial, lastName, gender, eMail, dateOfBirth, dateOfJoining, salary));
    }

    public void addEmployee(String data) throws EmployeeDifferentDataSameID {
        addEmployee(new Employee(data));
    }
}
