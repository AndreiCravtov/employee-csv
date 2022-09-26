package com.sparta;

import com.sparta.dao.interfaces.DAO;
import com.sparta.dao.sql.EmployeeDAO;
import com.sparta.entities.Employee;
import com.sparta.util.Benchmarker;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Benchmarker benchmarker = new Benchmarker();

        benchmarker.start("Loading from CSV"); //---------
        // read from csv
        Employees employees = new Employees();
        List<String> erroneousData = new ArrayList<>();
        FunctionalCSVConverter.convert("src/main/resources/EmployeeRecords1.csv", employees, erroneousData);
        erroneousData.forEach(System.out::println);
        benchmarker.stop(); //---------

        benchmarker.start("SQL stuff"); //---------

        // sql begins here
        DAO<Employee> employeeDAO = EmployeeDAO.getInstance();

        // write to sql
        List<Thread> threads = new ArrayList<>();
        for (Employee employee: employees) {
            Thread thread = new Thread(() -> {
                employeeDAO.insert(employee);
            });
            thread.start();
            threads.add(thread);
        }
        for (Thread thread: threads)
            thread.join();

        // reset employees table
        employeeDAO.deleteAll();

        benchmarker.stop(); //---------

        benchmarker.drainToConsole();
    }
}