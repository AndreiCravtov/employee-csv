package com.sparta;

import com.sparta.entities.Employee;
import com.sparta.util.TrackedHashMap;

import java.util.*;

/**
 * This class is used to store unique employees sorted by their ID
 */
public class Employees {
    private final TrackedHashMap<Integer, Employee> employees = new TrackedHashMap<>();
    TrackedHashMap<Integer,Employee> erroneous = new TrackedHashMap<>();
    private List<Employee> sortedEmployeesCache = new ArrayList<>();

    /**
     * Either grabs an upto-date sorted employees list cache or sorts the hash map if the cache isn't upto-date.
     * @return A list of employees sorted by ID in ascending order
     */
    private List<Employee> getSortedEmployees() {
        if (!employees.mapHasChanged()) return sortedEmployeesCache;
        sortedEmployeesCache = new ArrayList<>(employees.values());
        Collections.sort(sortedEmployeesCache);
        employees.setMapUnchanged();
        return sortedEmployeesCache;
    }

    /**
     * Gets all employees stored and returns a sorted array.
     * @return employee array sorted by ID in ascending order
     */
    public Employee[] getEmployees() {
        return getSortedEmployees().toArray(new Employee[0]);
    }

    /**
     * Gets the number of employees stored
     * @return the number of employees
     */
    public int getEmployeeCount() { return employees.size(); }

    /**
     * This method adds an employee object to the currently stored employees
     * @param employee the employee to be added.
     */
    public void addEmployee(Employee employee) {
        if (employee == null) return;

        Employee out = employees.putIfAbsent(employee.getId(), employee);
        if (out == null || out.equals(employee))
        {
            erroneous.put(employee.getId(), employee);
        }
        else
        {
            List<Employee> sorted = getSortedEmployees();
            addEmployee(new Employee(
                    sorted.get(sorted.size()-1).getId() + 1,
                    employee.getNamePrefix(),
                    employee.getFirstName(),
                    employee.getMiddleInitial(),
                    employee.getLastName(),
                    employee.getGender(),
                    employee.getEMail(),
                    employee.getDateOfBirth(),
                    employee.getDateOfJoining(),
                    employee.getSalary()
            ));
        }
    }

    /**
     * This method serializes all the employees stored into a CSV file
     * @return the serialised CSV file as a string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{\n");
        for (Employee employee: getSortedEmployees())
            builder.append(employee.toString()).append("\n");
        return builder.append("}").toString();
    }
}