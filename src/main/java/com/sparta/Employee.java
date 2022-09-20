package com.sparta;

/**
 * A class representing an employee record.
 */
public class Employee {
    private int employeeID;
    private String namePrefix;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private char gender;
    private String eMail;
    private String dateOfBirth;
    private String dateOfJoining;
    private int salary;

    public int getEmployeeID() {
        return employeeID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String geteMail() {
        return eMail;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public int getSalary() {
        return salary;
    }

    /**
     * Instantiates a new employee object, taking the data as parameters
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
    public Employee(
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
        this.employeeID = employeeID;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.eMail = eMail;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
    }

    /**
     * Instantiates a new employee object, taking the data as a CSV row string.
     * The string is de-serialized into the object fields
     * @param row the CSV row
     */
    public Employee(String row) throws IllegalArgumentException {
        try {
            String clean = row.replace("^[ \\t]+|[ \\t]+$", "");
            String[] elements = clean.split(",");
            this.employeeID = Integer.parseInt(elements[0]);
            this.namePrefix = elements[1];
            this.firstName = elements[2];
            this.middleInitial = elements[3].charAt(0);
            this.lastName = elements[4];
            this.gender = elements[5].charAt(0);
            this.eMail = elements[6];
            this.dateOfBirth = elements[7];
            this.dateOfJoining = elements[8];
            this.salary = Integer.parseInt(elements[9]);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method serializes the employee object.
     * It turns the fields in the employee object into a CSV row string.
     * @return the serialised CSV row string
     */
    public String serialize() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", employeeID, namePrefix, firstName, middleInitial, lastName, gender, eMail, dateOfBirth, dateOfJoining, salary);
    }
}
