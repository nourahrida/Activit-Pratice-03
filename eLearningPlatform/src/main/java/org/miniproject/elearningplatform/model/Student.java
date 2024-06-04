package org.miniproject.elearningplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.miniproject.elearningplatform.exception.EmailValidationException;
import org.miniproject.elearningplatform.exception.PhoneNumberValidationException;
import org.miniproject.elearningplatform.model.User;
import org.miniproject.elearningplatform.model.submodel.UserAddress;
import org.miniproject.elearningplatform.model.submodel.UserPhoneNumber;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Student extends User {
    // this is a subClass of User
    @Column
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<Enrollment> enrollments = new HashSet<>();

    public Student(String fname, String lname, String email, LocalDate birthday, UserAddress address) throws PhoneNumberValidationException, EmailValidationException {
        super(fname, lname, email, birthday, address);
    }

}
