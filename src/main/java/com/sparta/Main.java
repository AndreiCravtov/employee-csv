package com.sparta;

public class Main {
    public static void main(String[] args) throws Exception {
        Employees employees = new Employees();
        CSVConverter.convert("src/main/resources/EmployeeRecordsLarge.csv", employees);
        System.out.println(employees.erroneous);
        System.out.println(employees.getEmployeeCount());
    }
}