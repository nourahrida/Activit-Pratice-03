package org.miniproject.elearningplatform.service.impl;

import org.miniproject.elearningplatform.model.Enrollment;
import org.miniproject.elearningplatform.model.Student;
import org.miniproject.elearningplatform.repository.IEnrollmentRepository;
import org.miniproject.elearningplatform.service.IBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class BulletinServiceImpl implements IBulletinService {

    private final IEnrollmentRepository enrollmentRepository;

    @Autowired
    public BulletinServiceImpl(IEnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public double calculatingGrade(Student student) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(student.getId());
        if (enrollments.isEmpty()) {
            return 0.0;
        }
        double totalScore = enrollments.stream()
                .mapToDouble(Enrollment::getGrade) // Assuming each enrollment has a getScore method.
                .sum();
        return totalScore / enrollments.size();
    }
}
