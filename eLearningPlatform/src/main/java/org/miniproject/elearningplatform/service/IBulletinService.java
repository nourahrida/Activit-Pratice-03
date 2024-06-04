package org.miniproject.elearningplatform.service;

import org.miniproject.elearningplatform.model.Enrollment;
import org.miniproject.elearningplatform.model.Student;
import org.miniproject.elearningplatform.repository.IEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface IBulletinService {
    double calculatingGrade(Student student);
}
