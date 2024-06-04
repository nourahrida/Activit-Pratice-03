package org.miniproject.elearningplatform.util;

import org.miniproject.elearningplatform.model.submodel.UserPhoneNumber;

public class PhoneNumberValidator {
    // Function to validate a Moroccan phone number
    public static boolean isValidPhoneNumber(UserPhoneNumber userPhoneNumber) {
        // Check if userPhoneNumber or its number is null
        if (userPhoneNumber == null || userPhoneNumber.getNumber() == null) {
            return true;
        }

        String number = userPhoneNumber.getNumber().trim();

        // Check if the number starts with "+212" or "0"
        if (number.startsWith("+212")) {
            number = number.substring(4); // Remove the "+212" prefix
        } else if (number.startsWith("0")) {
            number = number.substring(1); // Remove the "0" prefix
        } else {
            return false;
        }

        // Check if the remaining number has exactly 9 digits and starts with 5, 6, or 7
        return number.matches("[567]\\d{8}");
    }

    public static void main(String[] args) {
        // Test cases
        UserPhoneNumber validNumber1 = new UserPhoneNumber("+212612345678");
        UserPhoneNumber validNumber2 = new UserPhoneNumber("0612345678");

        System.out.println(isValidPhoneNumber(validNumber1) ? "Valid" : "Invalid"); // Should print "Valid"
        System.out.println(isValidPhoneNumber(validNumber2) ? "Valid" : "Invalid"); // Should print "Valid"
    }
}
