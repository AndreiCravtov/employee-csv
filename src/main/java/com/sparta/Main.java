package com.sparta;

import com.sparta.dao.interfaces.DAO;
import com.sparta.dao.sql.EmployeeDAO;
import com.sparta.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // read from csv
        Employees employees = new Employees();
        List<String> erroneousData = new ArrayList<>();
        CSVConverter.convert("src/main/resources/EmployeeRecordsLarge.csv", employees, erroneousData);

        // sql begins here
        DAO<Employee> employeeDAO = EmployeeDAO.getInstance();

        long start = System.nanoTime();

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

        long end = System.nanoTime();

        System.out.printf("%s ms\n", (float) (end - start)/1_000_000);
    }
}