package com.sparta;
import com.sparta.entities.Employee;
import com.sparta.util.RecordValidator;

import java.io.FileReader;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.List;

/**
 * A to convert a .csv file to a string
 */
public class CSVConverter {
    static void convert(String fileName, Employees employees, List<String> erroneous) {
        try (FileReader reader = new FileReader(fileName)) {
            BufferedReader br =new BufferedReader(reader);
            br.readLine(); // skips 1st header line
            String line;
            final StringBuffer buffer = new StringBuffer(2048);
            Employee employee;
            while ((line = br.readLine()) != null) {
                try {
                    String[] elements = line.strip().split(",");
                    if (!RecordValidator.isRecordValid(elements)) throw new IllegalArgumentException("This record is corrupt");
                    String[] birthDateElems = elements[7].split("/");
                    String[] joinDateElems = elements[8].split("/");
                    employee = new Employee(
                            Integer.parseInt(elements[0]),
                            elements[1],
                            elements[2],
                            elements[3].charAt(0),
                            elements[4],
                            elements[5].charAt(0),
                            elements[6],
                            LocalDate.of(Integer.parseInt(birthDateElems[2]), Integer.parseInt(birthDateElems[0]), Integer.parseInt(birthDateElems[1])),
                            LocalDate.of(Integer.parseInt(joinDateElems[2]), Integer.parseInt(joinDateElems[0]), Integer.parseInt(joinDateElems[1])),
                            Integer.parseInt(elements[9])
                    );
                    if (!employees.addEmployee(employee)) throw new IllegalArgumentException("Duplicate data");
                } catch (IllegalArgumentException e) {
                    erroneous.add(line);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
