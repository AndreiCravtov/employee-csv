package com.sparta;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * A to convert a .csv file to a string
 */
public class CSVConverter {
    static String convert(String fileName) {
        try (FileReader reader = new FileReader("EmployeeRecords1.csv")) {
            BufferedReader br =new BufferedReader(reader);
            String line = "";
            String s = "";
            final StringBuffer buffer = new StringBuffer(2048);
            while ((line = br.readLine()) != null) {
                s += line;
            }
            return s;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
