package org.miniproject.elearningplatform.controller;


import org.miniproject.elearningplatform.model.Teacher;
import org.miniproject.elearningplatform.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    protected TeacherService teacherService;

    // Create a new teacher
    @PostMapping("/add")
    @ResponseBody // Use this annotation to send a JSON response
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    // Retrieve a teacher by ID
    @GetMapping("/{id}")
    @ResponseBody
    public Teacher getTeacher(@PathVariable String id) {
        return teacherService.getTeacherById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
    }

    // Optional: List all teachers
    @GetMapping("/")
    @ResponseBody
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
}
