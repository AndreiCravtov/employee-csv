package com.sparta;

public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee("198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294");
        System.out.println(employee1.getSalary());
        System.out.println(employee1.serialize());
    }
}