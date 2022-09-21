package com.sparta;

public class Main {
    public static void main(String[] args) throws Exception {
        String data = """
                198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294
                178566,Mrs.,Juliette,M,Rojo,F,juliette.rojo@yahoo.co.uk,5/8/1967,6/4/2011,193912
                647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681
                847634,Mr.,Elmer,R,Jason,M,elmer.jason@yahoo.com,4/9/1996,5/28/2017,93504
                260736,Ms.,Zelda,P,Forest,F,zelda.forest@ibm.com,11/27/1959,1/28/2014,176642
                811306,Mr.,Rhett,P,Wan,M,rhett.wan@hotmail.com,7/14/1976,1/21/2009,59406
                956633,Mr.,Hal,H,Farrow,M,hal.farrow@cox.net,3/15/1967,2/25/1991,164580
                629539,Dr.,Del,I,Fernandez,M,del.fernandez@hotmail.com,8/13/1991,4/7/2016,138662
                784160,Dr.,Corey,A,Jackman,M,corey.jackman@gmail.com,4/12/1959,6/29/1984,57616
                784160,Dr.,Corey,A,Jackman,M,corey.jackman@gmail.com,4/12/1959,6/29/1984,57616
                784160,Dr.,Corey,A,Jackman,M,corey.jackman@gmail.com,4/12/1959,6/29/1984,57616
                784160,Dr.,Corey,A,Jackman,M,corey.jackman@gmail.com,4/12/1959,6/29/1984,57616
                784160,Dr.,Corey,A,Jackman,M,corey.jackman@gmail.com,4/12/1959,6/29/1984,57616
                784160,Dr.,Corey,A,Jackman,M,corey.jackman@gmail.com,4/12/1959,6/29/1984,57616""";
        String[] records = data.split("\n");

//        String data = CSVConverter.convert("main\\resources\\EmployeeRecords1.csv");

        // Creates employees obj from data: removing any duplicates and sorts
        Employees employees = new Employees();
        for (String record: records)
            employees.addEmployee(record);

        // Get employees array
        Employee[] employeesArray = employees.getEmployees();
        for (Employee e: employeesArray)
            System.out.println(e.serialize());
        System.out.println(employees.serialize());
    }
}