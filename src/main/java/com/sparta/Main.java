package com.sparta;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Employees employees = new Employees();
        List<String> err = new ArrayList<>();
//        CSVConverter.convert("src/main/resources/EmployeeRecords1.csv", employees, err);
        CSVConverter.convert("src/main/resources/EmployeeRecords2.csv", employees, err);
        err.forEach(System.out::println);
//        System.out.println(employees);
//        System.out.println(employees.getEmployeeCount());
    }
}