package com.sparta.util;

public class RecordValidation {
    private static final int NumOfRecords = 10;

    public static boolean isRecordValid(String[] employeeRecord){

        return (employeeRecord.length == NumOfRecords&&

                isTitleValid(employeeRecord[1]) &&
                isNameValid(employeeRecord[2]) &&
                isMiddleInitialValid(employeeRecord[3]) &&
                isNameValid(employeeRecord[4]) &&
                isGenderValid(employeeRecord[5]) &&
                isEmailValid(employeeRecord[6]));

    }



    private static boolean isTitleValid(String title){
        String[] titles = new String[] {"Prof.", "Mrs.", "Mr.", "Ms.", "Dr.", "Drs.", "Hon."};
        for (String honoraryTitle : titles) {
            if(honoraryTitle.equals(title)) return true;
        }
        return false;
    }

    private static boolean isNameValid(String name){
        return (name.matches("^[a-zA-Z]*$"));
    }

    private static boolean isMiddleInitialValid(String middleInitial){
        return (middleInitial.matches("^[A-Z]") || middleInitial.length() != 1);
    }

    private static boolean isGenderValid(String gender){
        return gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("M");
    }

    private static boolean isEmailValid(String email){
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
    }




}

