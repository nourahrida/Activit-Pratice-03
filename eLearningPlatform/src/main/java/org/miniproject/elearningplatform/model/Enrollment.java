package org.miniproject.elearningplatform.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.miniproject.elearningplatform.emun.EnrollmentStatus;
import org.miniproject.elearningplatform.exception.GradeOutOfRangeException;
import org.miniproject.elearningplatform.exception.StudentUserViolationException;
import org.miniproject.elearningplatform.model.Course;
import org.miniproject.elearningplatform.model.Student;
import org.miniproject.elearningplatform.util.AutoIdGenerator;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@NoArgsConstructor
@Data
@Getter
public class Enrollment {
    @Transient
    protected final String Enrollment_Name = "ENR";
    @Transient
    protected final String StudentUser_Violation_Exception_Message = "The user you are trying to add is not a user with type 'Student'. Please verify and try again.";
    @Transient
    protected final String NegativeNumber_Violation_Exception_Message = "The Number that you are trying to use is not a positive number. Please verify and try again.";
    @Transient
    protected final String GradeOutOfRange_Violation_Exception_Message = "The Grade that you are trying to use is out of range. Please verify that the grade is between 0 and 20 and try again.";

    @Column
    @Id
    protected String ID;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Setter
    @Column
    protected LocalDate enrollmentDate;

    @Setter
    @Column
    protected EnrollmentStatus status;

    @Column
    protected double grade;

    public void setGrade(double grade) throws GradeOutOfRangeException {
        if(grade >= 0 && grade <= 20){
            this.grade = grade;
        }
        else{
            throw new GradeOutOfRangeException(GradeOutOfRange_Violation_Exception_Message);
        }
    }

    public Enrollment(Student student, Course course, LocalDate enrollmentDate, EnrollmentStatus status, double grade) throws GradeOutOfRangeException {
        this.ID = AutoIdGenerator.generateAutoId(Enrollment_Name);
        this.setStudent(student);
        this.setCourse(course);
        this.setEnrollmentDate(enrollmentDate);
        this.setStatus(status);
        this.setGrade(grade);
    }
}
