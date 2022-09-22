package com.sparta;

public class Main {
    public static void main(String[] args) throws Exception {
        Employees employees = new Employees();
        CSVConverter.convert("src/main/resources/EmployeeRecords1.csv", employees);

//        System.out.println(employees);
        System.out.println(employees.getEmployeeCount());
    }
}