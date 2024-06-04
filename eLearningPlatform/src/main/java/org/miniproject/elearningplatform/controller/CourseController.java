package org.miniproject.elearningplatform.controller;

import jakarta.persistence.EntityNotFoundException;
import org.miniproject.elearningplatform.model.Course;
import org.miniproject.elearningplatform.model.HttpResponse;
import org.miniproject.elearningplatform.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        try {
            if (courseService.getCourseById(course.getID()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course already exists.");
            } else {
                courseService.addCourse(course);
                return ResponseEntity.ok(HttpResponse.builder().message("Course added successfully").build());
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while processing your request: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateCourse(@RequestBody Course course) {
        try {
            Course updatedCourse = courseService.updateCourse(course);
            return ResponseEntity.ok(updatedCourse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while updating the course: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        try {
            boolean deleted = courseService.deleteCourse(id);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course could not be deleted or does not exist.");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while deleting the course: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getCourse(@PathVariable String id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/")
    @ResponseBody
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
}
