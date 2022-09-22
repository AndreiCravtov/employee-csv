package com.sparta;
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
            String s = "";
            br.readLine();
            String line1=null;
            final StringBuffer buffer = new StringBuffer(2048);
            while ((line1 = br.readLine()) != null) {
                s = line1 + "\n";
                employees.addEmployee(s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
