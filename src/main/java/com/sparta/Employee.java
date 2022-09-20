package com.sparta;

public class Employee {
    int employeeID;
    String namePrefix;
    String firstName;
    String middleInitial;
    String lastName;
    char gender;
    String eMail;
    String dateOfBirth;
    String dateOfJoining;
    int salary;

    public Employee(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * This method serializes the employee object.
     * It turns the fields in the
     * @return
     */
    public String serialize() {
        return "";
    }
}
