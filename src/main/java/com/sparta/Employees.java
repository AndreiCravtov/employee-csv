package com.sparta;

import com.sparta.util.TrackedHashMap;

import java.util.*;

public class Employees {
    private final String header = "Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary\n";
    private final TrackedHashMap<Integer, Employee> employees = new TrackedHashMap<>();
    private List<Employee> sortedEmployeesCache = new ArrayList<>();

    private List<Employee> getSortedEmployees() {
        if (!employees.mapHasChanged()) return sortedEmployeesCache;
        sortedEmployeesCache = new ArrayList<>(employees.values());
        Collections.sort(sortedEmployeesCache);
        employees.setMapUnchanged();
        return sortedEmployeesCache;
    }

    public Employee[] getEmployees() {
        return getSortedEmployees().toArray(new Employee[0]);
    }

    public void addEmployee(Employee employee) {
        if (employee == null) return;

        Employee out = employees.putIfAbsent(employee.getEmployeeID(), employee);
        if (out == null || out.equals(employee)) return;

        List<Employee> sorted = getSortedEmployees();
        addEmployee(
                sorted.get(sorted.size()-1).getEmployeeID() + 1,
                employee.getNamePrefix(),
                employee.getFirstName(),
                employee.getMiddleInitial(),
                employee.getLastName(),
                employee.getGender(),
                employee.getEMail(),
                employee.getDateOfBirth(),
                employee.getDateOfJoining(),
                employee.getSalary()
        );
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
    ) {
        addEmployee(new Employee(employeeID, namePrefix, firstName, middleInitial, lastName, gender, eMail, dateOfBirth, dateOfJoining, salary));
    }

    public void addEmployee(String data) {
        addEmployee(new Employee(data));
    }

    public String serialize() {
        StringBuilder builder = new StringBuilder(header);
        for (Employee employee: getSortedEmployees())
            builder.append(employee.serialize()).append("\n");
        return builder.toString().strip();
    }
}
