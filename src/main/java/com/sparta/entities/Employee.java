package com.sparta.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A class representing an employee record.
 */
public class Employee extends DataObject {
    private String namePrefix;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private char gender;
    private String eMail;
    private final LocalDate dateOfBirth;
    private final LocalDate dateOfJoining;
    private int salary;

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
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
            LocalDate dateOfBirth,
            LocalDate dateOfJoining,
            int salary
    ) {
        super(employeeID);
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
     * This method serializes the employee object.
     * It turns the fields in the employee object into a CSV row string.
     * @return the serialised CSV row string
     */
    @Override
    public String toString() {
        return String.format(
                "{\n\tID: %s,\n\tName: %s %s %s %s,\n\tGender: %s,\n\tE-Mail: %s,\n\tDate of birth: %s,\n\tDate of joining: %s,\n\tSalary: %s\n}",
                this.getId(),
                namePrefix,
                firstName,
                middleInitial,
                lastName,
                gender,
                eMail,
                dateOfBirth.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                dateOfJoining.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                salary
        );
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
        return this.getId() == employee.getId() &&
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
        hash = 53 * hash + this.getId();
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
}
