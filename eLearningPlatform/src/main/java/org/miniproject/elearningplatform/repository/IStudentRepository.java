package org.miniproject.elearningplatform.repository;

import org.miniproject.elearningplatform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, String> {
    // Common operations
}

