package org.miniproject.elearningplatform.service;


import org.miniproject.elearningplatform.model.Teacher;
import org.miniproject.elearningplatform.repository.ITeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final ITeacherRepository teacherRepository;

    public TeacherService(ITeacherRepository studentRepository) {
        this.teacherRepository = studentRepository;
    }


    public Teacher addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(String teacherId) {
        return teacherRepository.findById(teacherId);
    }

    public void updateTeacher(Teacher updatedTeacher) {
        if (teacherRepository.findById(updatedTeacher.getId()).isPresent()) {
            teacherRepository.save(updatedTeacher);
        }
    }

    public void deleteTeacher(String teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
