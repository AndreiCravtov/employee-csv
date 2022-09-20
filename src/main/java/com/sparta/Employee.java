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
     * It turns the fields in the employee object into a CSV row string.
     * @return the serialised CSV row string
     */
    public String serialize() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", employeeID, namePrefix, firstName, middleInitial, lastName, gender, eMail, dateOfBirth, dateOfJoining, salary);
    }
}
