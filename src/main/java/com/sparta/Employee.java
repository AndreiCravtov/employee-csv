package com.sparta;

/**
 * A class representing an employee record.
 */
public class Employee implements Comparable<Employee> {
    private final int employeeID;
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

    public String getEMail() {
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
            String[] elements = row.strip().split(",");
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

    /**
     * Indicates whether some Employee is "equal to" this one.
     *
     * @param   obj   the reference Employee with which to compare.
     * @return  {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        final Employee employee = (Employee) obj;
        return employeeID == employee.employeeID &&
                namePrefix.equals(employee.namePrefix) &&
                firstName.equals(employee.firstName) &&
                middleInitial == employee.middleInitial &&
                lastName.equals(employee.lastName) &&
                gender == employee.gender &&
                eMail.equals(employee.eMail) &&
                dateOfBirth.equals(employee.dateOfBirth) &&
                dateOfJoining.equals(employee.dateOfJoining) &&
                salary == employee.salary;
    }

    /**
     * Returns a hash code value for this Employee. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 821;
        hash = 53 * hash + employeeID;
        hash = 53 * hash + (namePrefix != null ? namePrefix.hashCode() : 0);
        hash = 53 * hash + (firstName != null ? firstName.hashCode() : 0);
        hash = 53 * hash + middleInitial;
        hash = 53 * hash + (lastName != null ? lastName.hashCode() : 0);
        hash = 53 * hash + gender;
        hash = 53 * hash + (eMail != null ? eMail.hashCode() : 0);
        hash = 53 * hash + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        hash = 53 * hash + (dateOfJoining != null ? dateOfJoining.hashCode() : 0);
        hash = 53 * hash + salary;
        return hash;
    }

    @Override
    public int compareTo(Employee employee) {
        return Integer.compare(employeeID, employee.employeeID);
    }
}
