package org.miniproject.elearningplatform.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.miniproject.elearningplatform.exception.EmailValidationException;
import org.miniproject.elearningplatform.exception.PhoneNumberValidationException;
import org.miniproject.elearningplatform.model.submodel.UserAddress;
import org.miniproject.elearningplatform.model.submodel.UserPhoneNumber;
import org.miniproject.elearningplatform.util.AgeCalculator;
import org.miniproject.elearningplatform.util.AutoIdGenerator;
import org.miniproject.elearningplatform.util.EmailValidator;
import javax.validation.constraints.NotBlank;
import org.miniproject.elearningplatform.util.PhoneNumberValidator;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    // Constants
    @Transient
    protected final String Email_Validation_Exception_Message = "The email that has been entered is not valid, Please verify it!";
    @Transient
    protected final String PhoneNumber_Validation_Exception_Message = "The phone number that has been entered is not valid, Please verify it!";
    @Getter
    @Column
    @Id
    protected String id;
    @Column()
    @Getter
    @Setter
    protected String fname;
    @Column()
    @Getter
    @Setter
    protected String lname;
    @Column()
    @Getter
    protected String email;
    @Column()
    @Getter
    @NotBlank(message = "Birthdate is required")
    protected LocalDate birthdate;
    @Column()
    @Getter
    protected int age;
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userAddress")
    protected UserAddress address;
//    @Getter
//    @Setter
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userPhoneNumber")
//    protected UserPhoneNumber phoneNumber;

    public void setEmail(String email) throws EmailValidationException {
        if(EmailValidator.isValidEmail(email)){
            this.email = email;
        }else{
            throw new EmailValidationException(Email_Validation_Exception_Message);
        }
    }

    public void setBirthdate(LocalDate birthdate) throws IllegalArgumentException {
        // Check if the birthdate is not null and is not in the future
        if (birthdate != null && !birthdate.isAfter(LocalDate.now())) {
            this.birthdate = birthdate;
            // Calculate the age based on the birthdate
            this.age = AgeCalculator.calculateAge(this.birthdate);
        } else {
            throw new IllegalArgumentException("Birthdate must be a valid date in the past or today");
        }
    }


//    public void setPhoneNumber(UserPhoneNumber phoneNumber) throws PhoneNumberValidationException {
//        if(PhoneNumberValidator.isValidPhoneNumber(phoneNumber) && phoneNumber.getNumber() != null){
//            this.phoneNumber = phoneNumber;
//        }else{
//            throw new PhoneNumberValidationException("The phone number entered is not valid, can you re-check it again");
//        }
//    }

    public User(String fname, String lname, String email, LocalDate birthdate, UserAddress address) throws EmailValidationException, PhoneNumberValidationException {
        this.id = AutoIdGenerator.generateAutoId(fname, lname);
        this.setFname(fname);
        this.setLname(lname);
        this.setEmail(email);
        this.setBirthdate(birthdate);
        this.setAddress(address);
//        this.setPhoneNumber(phoneNumber);
    }
    public String GenerateId(String fname, String lname){
        return AutoIdGenerator.generateAutoId(fname, lname);
    }
    @PrePersist
    protected void onPrePersist() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = AutoIdGenerator.generateAutoId(this.fname, this.lname);
        }
    }
}