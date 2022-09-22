package com.sparta;
import com.sparta.entities.Employee;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * A to convert a .csv file to a string
 */
public class CSVConverter {
    static void convert(String fileName, Employees employees) {
        try (FileReader reader = new FileReader(fileName)) {
            BufferedReader br =new BufferedReader(reader);
            br.readLine(); // skips 1st header line
            String line;
            final StringBuffer buffer = new StringBuffer(2048);
            Employee employee;
            while ((line = br.readLine()) != null) {
                String[] elements = line.strip().split(",");
                if (elements.length != 10) throw new IllegalArgumentException();
                try {
                    employee = new Employee(
                            Integer.parseInt(elements[0]),
                            elements[1],
                            elements[2],
                            elements[3].charAt(0),
                            elements[4],
                            elements[5].charAt(0),
                            elements[6],
                            elements[7],
                            elements[8],
                            Integer.parseInt(elements[9])
                    );
                } catch (Exception e) {throw new IllegalArgumentException();}
                employees.addEmployee(employee);
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
