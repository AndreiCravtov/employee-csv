package com.sparta;

import com.sparta.exceptions.EmployeeDifferentDataSameID;
import com.sparta.util.TrackedHashMap;

import java.util.*;

public class Employees {
    private final String header = "Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary\n";
    private final TrackedHashMap<Integer, Employee> employees = new TrackedHashMap<>();
    private List<Employee> sortedEmployeesCache = new ArrayList<>();

    private List<Employee> getSortedEmployees() {
        if (!employees.mapChanged) return sortedEmployeesCache;
        sortedEmployeesCache = new ArrayList<>(employees.values());
        Collections.sort(sortedEmployeesCache);
        employees.mapChanged = false;
        return sortedEmployeesCache;
    }

    public Employee[] getEmployees() {
        return getSortedEmployees().toArray(new Employee[0]);
    }

    public void addEmployee(Employee employee) throws EmployeeDifferentDataSameID {
        if (employee == null) return;
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

    public String serialize() {
        StringBuilder builder = new StringBuilder(header);
        for (Employee employee: getSortedEmployees())
            builder.append(employee.serialize()).append("\n");
        return builder.toString().strip();
    }
}
