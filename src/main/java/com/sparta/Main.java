package com.sparta;

public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee("198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294");
        Employee employee2 = new Employee("207808,Ms.,Renetta,T,Hafner,F,renetta.hafner@aol.com,1/29/1975,8/22/1998,180289");
        Employee employee3 = new Employee("198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294");
        System.out.println(employee1.equals(employee1));
        System.out.println(employee1.equals(employee2));
        System.out.println(employee1.equals(employee3));

        System.out.println(employee1.hashCode());
        System.out.println(employee2.hashCode());
        System.out.println(employee3.hashCode());
    }
}