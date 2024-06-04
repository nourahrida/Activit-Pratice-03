package org.miniproject.elearningplatform.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.miniproject.elearningplatform.exception.EmailValidationException;
import org.miniproject.elearningplatform.exception.PhoneNumberValidationException;
import org.miniproject.elearningplatform.model.Course;
import org.miniproject.elearningplatform.model.User;
import org.miniproject.elearningplatform.model.submodel.UserAddress;
import org.miniproject.elearningplatform.model.submodel.UserPhoneNumber;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@NoArgsConstructor
@Data
public class Teacher extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    protected Course course;

    public Teacher(String fname, String lname, String email, LocalDate birthdate, UserAddress address, Course course) throws EmailValidationException, PhoneNumberValidationException {
        super(fname, lname, email, birthdate, address);
        this.course = course;
    }

}
