package com.sparta;

import com.sparta.entities.Employee;
import com.sparta.util.TrackedHashMap;

import java.util.*;

/**
 * This class is used to store unique employees sorted by their ID
 */
public class Employees {
    private final TrackedHashMap<Integer, Employee> employees = new TrackedHashMap<>();
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
     * This method adds an employee object to the currently stored employees
     * @param employee the employee to be added.
     */
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

    /**
     * This method instantiates and adds an employee object to the currently stored employees, taking the data as parameters
     * @param employeeID the employee ID
     * @param namePrefix the title of the employee
     * @param firstName the first name of the employee
     * @param middleInitial the middle name initial of the employee
     * @param lastName the last name of the employee
     * @param gender a character representing the gender of the employee
     * @param eMail the email of the employee
     * @param dateOfBirth the date of birth of the employee
     * @param dateOfJoining the date of joining to the company of the employee
     * @param salary the salary of the employee
     */
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

    /**
     * This method instantiates and adds an employee object to the currently stored employees, taking the data as a CSV row string
     * @param row the CSV row
     */
    public void addEmployee(String row) {
        addEmployee(new Employee(row));
    }

    /**
     * This method serializes all the employees stored into a CSV file
     * @return the serialised CSV file as a string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary\n");
        for (Employee employee: getSortedEmployees())
            builder.append(employee.toString()).append("\n");
        return builder.toString().strip();
    }
}
