package org.miniproject.elearningplatform.util;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {
    // Function to calculate age from birthdate
    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
