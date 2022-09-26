package com.sparta;

import com.sparta.entities.Employee;
import com.sparta.util.RecordValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FunctionalCSVConverter {
    static void convert(String fileName, Employees employees, List<String> erroneous) throws IOException {

        Files
                .lines(Path.of(fileName)) // Calling from CSV as a stream
                .skip(1) // skips first line which is the heading - Not valid data
                .filter(s -> { // remove corrupted data
                    if (RecordValidator.isRecordValid(s.split(","))) return true;
                    erroneous.add(s);
                    return false;
                })
                .map(s -> s.split(",")) // splits on comma
                .map(s -> {
                    String[] birthDateElems = s[7].split("/");
                    String[] joinDateElems = s[8].split("/");

                    return new Employee(
                        Integer.parseInt(s[0]),
                        s[1],
                        s[2],
                        s[3].charAt(0),
                        s[4],
                        s[5].charAt(0),
                        s[6],
                        LocalDate.of(Integer.parseInt(birthDateElems[2]), Integer.parseInt(birthDateElems[0]), Integer.parseInt(birthDateElems[1])),
                        LocalDate.of(Integer.parseInt(joinDateElems[2]), Integer.parseInt(joinDateElems[0]), Integer.parseInt(joinDateElems[1])),
                        Integer.parseInt(s[9])
                    );
                })
                .forEach(e -> {
                    if (!employees.addEmployee(e))
                        erroneous.add(String.format(
                                "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                                e.getId(),
                                e.getNamePrefix(),
                                e.getFirstName(),
                                e.getMiddleInitial(),
                                e.getLastName(),
                                e.getGender(),
                                e.getEMail(),
                                e.getDateOfBirth().format(DateTimeFormatter.ofPattern("d/MM/uuuu")),
                                e.getDateOfJoining().format(DateTimeFormatter.ofPattern("d/MM/uuuu")),
                                e.getSalary()
                        ));
                });
    }
}