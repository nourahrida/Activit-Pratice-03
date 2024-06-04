package org.miniproject.elearningplatform.service;

import org.miniproject.elearningplatform.model.Enrollment;
import org.miniproject.elearningplatform.repository.IEnrollmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);
    private final IEnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(IEnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public void addEnrollment(Enrollment enrollment) {
        try {
            enrollmentRepository.save(enrollment);
            logger.info("Enrollment saved successfully for student ID: {}", enrollment.getStudent().getId());
        } catch (DataAccessException e) {
            logger.error("Error saving enrollment for student ID: {}: {}", enrollment.getStudent().getId(), e.getMessage());
            throw e;
        }
    }

    public List<Enrollment> getAllEnrollments() {
        try {
            return enrollmentRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("Error retrieving enrollments: {}", e.getMessage());
            throw e;
        }
    }

    public Optional<Enrollment> getEnrollmentById(String enrollmentId) {
        try {
            return enrollmentRepository.findById(enrollmentId);
        } catch (DataAccessException e) {
            logger.error("Error retrieving enrollment with ID {}: {}", enrollmentId, e.getMessage());
            throw e;
        }
    }

    public Enrollment updateEnrollment(Enrollment updatedEnrollment) {
        try {
            enrollmentRepository.save(updatedEnrollment);
            logger.info("Enrollment updated successfully for ID: {}", updatedEnrollment.getID());
        } catch (DataAccessException e) {
            logger.error("Error updating enrollment for ID {}: {}", updatedEnrollment.getID(), e.getMessage());
            throw e;
        }
        return updatedEnrollment;
    }

    public boolean deleteEnrollment(String enrollmentId) {
        try {
            enrollmentRepository.deleteById(enrollmentId);
            logger.info("Enrollment deleted successfully for ID: {}", enrollmentId);
            return true;
        } catch (DataAccessException e) {
            logger.error("Error deleting enrollment with ID {}: {}", enrollmentId, e.getMessage());
            throw e;
        }
    }

    public List<Enrollment> getEnrollmentsByStudentId(String studentId) {
        try {
            List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
            logger.info("Retrieved {} enrollments for student ID: {}", enrollments.size(), studentId);
            return enrollments;
        } catch (DataAccessException e) {
            logger.error("Error retrieving enrollments for student ID {}: {}", studentId, e.getMessage());
            throw e;
        }
    }

    public List<Enrollment> getEnrollmentsByCourseId(String courseId) {
        try {
            List<Enrollment> enrollments = enrollmentRepository.findByCourseID(courseId);
            logger.info("Retrieved {} enrollments for course ID: {}", enrollments.size(), courseId);
            return enrollments;
        } catch (DataAccessException e) {
            logger.error("Error retrieving enrollments for course ID {}: {}", courseId, e.getMessage());
            throw e;
        }
    }
}
