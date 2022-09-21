package com.sparta;

/**
 * A to convert a .csv file to a string
 */
public class CSVConverter {
    String[] convert(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            BufferedReader br - new BufferedReader(reader);
            String line = "";
            String[] tempArr;
            String records = "";
            while((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                for(String tempStr : tempArr) {
                    records=records+tempArr;
                }
            br.close();

            return records;
        }
        catch(IOException ioe) {
        ioe.printStackTrace();
    }
        return null;
    }
}
