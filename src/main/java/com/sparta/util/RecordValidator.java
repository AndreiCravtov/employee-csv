package com.sparta.util;

public class RecordValidator {
    private static final int NUM_OF_RECORDS = 10;

    public static boolean isRecordValid(String[] employeeRecord) {
        return (employeeRecord.length == NUM_OF_RECORDS &&
                isPosInt(employeeRecord[0]) &&
                isTitleValid(employeeRecord[1]) &&
                isNameValid(employeeRecord[2]) &&
                isMiddleInitialValid(employeeRecord[3]) &&
                isNameValid(employeeRecord[4]) &&
                isGenderValid(employeeRecord[5]) &&
                isEmailValid(employeeRecord[6]) &&
                isDateValid(employeeRecord[7]) &&
                isDateValid(employeeRecord[8]) &&
                isPosInt(employeeRecord[9])
        );
    }

    private static boolean isPosInt(String num) {
        try {
            int num_int = Integer.parseInt(num);
            if (num_int >= 0)
                return true;
        } catch (Exception ignored) {}
        return false;
    }

    private static boolean isTitleValid(String title) {
        String[] titles = new String[] {"Prof.", "Mrs.", "Mr.", "Ms.", "Dr.", "Drs.", "Hon."};
        for (String honoraryTitle : titles)
            if(honoraryTitle.equals(title)) return true;
        return false;
    }

    private static boolean isNameValid(String name) {
        return (name.matches("^[a-zA-Z]*$"));
    }

    private static boolean isMiddleInitialValid(String middleInitial) {
        return (middleInitial.matches("^[A-Z]") || middleInitial.length() != 1);
    }

    private static boolean isGenderValid(String gender) {
        return gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("M");
    }

    private static boolean isEmailValid(String email) {
        return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    private static boolean isDateValid(String date) {
        return date.matches("^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:(?:0?2)(\\/|-|\\.)(?:29)\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
    }
}

