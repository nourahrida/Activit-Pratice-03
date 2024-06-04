package org.miniproject.elearningplatform.util;

import java.util.Random;

public class AutoIdGenerator {
    private static int counter = 0;

    // Function to generate a random letter
    public static char randomLetter() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 'a');
    }

    // Function to generate a random string of given length
    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(randomLetter());
        }
        return sb.toString();
    }

    // Function to generate the auto ID
    public static String generateAutoId(String fname, String lname) {
        // Incrementing the counter
        counter++;

        // Extracting the first letter from first name and last name
        char fnameFirstLetter = (fname != null && fname.length() > 0) ? Character.toUpperCase(fname.charAt(0)) : ' ';
        char lnameFirstLetter = (lname != null && lname.length() > 0) ? Character.toUpperCase(lname.charAt(0)) : ' ';

        // Generating random letters for "FYJ"
        String randomLetters = randomString(3);

        // Creating the auto ID
        String autoId = String.format("%s%s_%s%04d", lname.toUpperCase(), fname.toUpperCase(), randomLetters, counter);

        return autoId;
    }

    public static String generateAutoId(String name) {
        // Incrementing the counter
        counter++;

        // Extracting the first letter from first name and last name
        char nameFirstLetter = (name != null && name.length() > 0) ? Character.toUpperCase(name.charAt(0)) : ' ';

        // Generating random letters for "FYJ"
        String randomLetters = randomString(3);

        // Creating the auto ID
        String autoId = String.format("%s%s_%s%04d", name.toUpperCase(), randomLetters, counter);

        return autoId;
    }
}
