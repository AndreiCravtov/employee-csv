package com.sparta;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * A to convert a .csv file to a string
 */
public class CSVConverter {
    static String convert(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            BufferedReader br =new BufferedReader(reader);
            String line = "";
            String[] tempArr;
            String records = "";
            while ((line = br.readLine()) != null) {
                tempArr = line.split("");
                for (String tempStr : tempArr) {
                    records = records + tempArr;
                }
                br.close();


            }
            return records;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
