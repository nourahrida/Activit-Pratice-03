package org.miniproject.elearningplatform.repository;

import org.miniproject.elearningplatform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, String> {
}
