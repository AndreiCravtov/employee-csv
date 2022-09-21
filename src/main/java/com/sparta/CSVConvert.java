package com.sparta;

/**
 * A to convert a .csv file to a string
 */
public class CSVConverter {
    String[] convert(String fileName)
    {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            String[] records = r.split("\n");
            return records;
        }
        return null;
}
