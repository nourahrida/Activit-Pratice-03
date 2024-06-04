package org.miniproject.elearningplatform.repository;

import org.miniproject.elearningplatform.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepository extends JpaRepository<Teacher, String> {
}
