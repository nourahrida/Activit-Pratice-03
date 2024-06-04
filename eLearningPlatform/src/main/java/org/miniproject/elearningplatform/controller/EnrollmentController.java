package org.miniproject.elearningplatform.controller;

import jakarta.persistence.EntityNotFoundException;
import org.miniproject.elearningplatform.model.Enrollment;
import org.miniproject.elearningplatform.model.HttpResponse;
import org.miniproject.elearningplatform.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    public EnrollmentController(){

    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addEnrollment(@RequestBody Enrollment enrollment) {
        try {
            enrollmentService.addEnrollment(enrollment);
            return ResponseEntity.ok(HttpResponse.builder().message("Enrollment added successfully").build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while processing your request: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateEnrollment(@RequestBody Enrollment enrollment) {
        try {
            Enrollment updatedEnrollment = enrollmentService.updateEnrollment(enrollment);
            return ResponseEntity.ok(updatedEnrollment);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while updating the enrollment: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteEnrollment(@PathVariable String id) {
        try {
            boolean deleted = enrollmentService.deleteEnrollment(id);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enrollment could not be deleted or does not exist.");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while deleting the enrollment: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getEnrollment(@PathVariable String id) {
        return enrollmentService.getEnrollmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/")
    @ResponseBody
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }
}
