package com.sparta;

import com.sparta.entities.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class functionalCSVConverter {
    static List convert(String fileName, Employees employees, List<String> erroneous) throws IOException {

        List results = Files
                .lines(Path.of(fileName)) // Calling from CSV as a stream
                .skip(1) // skips first line which is the heading - Not valid data
                .map(s -> s.split(",")) //Maps through entire array, calling split on comma
                .map(s -> {new Employee(
                        Integer.parseInt(s[0]),
                        s[1],
                        s[2],
                        s[3].charAt(0),
                        s[4],
                        s[5].charAt(0),
                        s[6],
                        LocalDate.parse(s[7]),
                        LocalDate.parse(s[8]),
                        Integer.parseInt(s[9]));
                }
                ).toList();

        return results;
    }
}