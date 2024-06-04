package org.miniproject.elearningplatform.controller;

import jakarta.persistence.EntityNotFoundException;
import org.miniproject.elearningplatform.exception.PhoneNumberValidationException;
import org.miniproject.elearningplatform.model.HttpResponse;
import org.miniproject.elearningplatform.model.Student;
import org.miniproject.elearningplatform.model.User;
import org.miniproject.elearningplatform.model.submodel.UserAddress;
import org.miniproject.elearningplatform.model.submodel.UserPhoneNumber;
import org.miniproject.elearningplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addStudent")
    public String showForm(Model model) throws PhoneNumberValidationException {
        Student newStudent = new Student();
        model.addAttribute("student", newStudent);
        return "formAddStudent";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute Student student, Model model) {
        try {
            System.out.println(student);
            LocalDate Birthday = LocalDate.parse(student.getBirthdate().toString());
            Student newStudent = new Student(student.getFname(), student.getLname(), student.getEmail(), Birthday, student.getAddress());
            if (studentService.getStudentById(newStudent.getId()).isPresent()) {
                model.addAttribute("error", "Student already exists.");
                return "formAddStudent";
            } else {
                studentService.addStudent(newStudent);
                return "redirect:/";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while processing your request.");
            System.err.println(e.getMessage());
            return "formAddStudent";
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student, Model model) {
        try {
            studentService.updateStudent(student);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while processing your request.");
            return "formEditStudent";
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete")
    public String deleteStudent(@RequestParam String studentId, Model model) {
        try {
            studentService.deleteStudent(studentId);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while processing your request.");
            return "students";
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Student getStudent(@PathVariable String id) {
        return studentService.getStudentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/")
    public String getAllStudentsPage(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditStudentForm(@PathVariable String id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        LocalDate birthdate = LocalDate.parse(student.getBirthdate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        student.setBirthdate(birthdate);
        System.out.println(student);
        model.addAttribute("student", student);
        return "formEditStudent";
    }
}
