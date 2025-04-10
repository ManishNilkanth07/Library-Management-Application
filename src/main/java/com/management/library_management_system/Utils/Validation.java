package com.management.library_management_system.Utils;

import java.util.regex.*;

public class Validation {

    public static boolean isValidName(String name) {
        String namePattern = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";
        Pattern pattern = Pattern.compile(namePattern);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidMembershipNumber(String name) {
        String membershipNumberPattern = "^[a-zA-Z]{3}\\d+$";
        Pattern pattern = Pattern.compile(membershipNumberPattern);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
