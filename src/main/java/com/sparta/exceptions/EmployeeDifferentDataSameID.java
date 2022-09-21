package com.sparta.exceptions;

public class EmployeeDifferentDataSameID extends Exception {
    public EmployeeDifferentDataSameID() {
        super("The employee being added has the same ID as an already added employee, but has different data");
    }
}
