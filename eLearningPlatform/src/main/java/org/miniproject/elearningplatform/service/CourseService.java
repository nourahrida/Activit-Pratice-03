package org.miniproject.elearningplatform.service;

import org.miniproject.elearningplatform.model.Course;
import org.miniproject.elearningplatform.repository.ICourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    private final ICourseRepository courseRepository;

    @Autowired
    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void addCourse(Course course) {
        try {
            courseRepository.save(course);
            logger.info("Course added successfully: {}", course.getName());
        } catch (DataAccessException e) {
            logger.error("Failed to add course: {}", e.getMessage());
            throw e;
        }
    }

    public List<Course> getAllCourses() {
        try {
            return courseRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve all courses: {}", e.getMessage());
            throw e;
        }
    }

    public Optional<Course> getCourseById(String courseId) {
        try {
            Optional<Course> course = courseRepository.findById(courseId);
            course.ifPresent(c -> logger.info("Found course with ID: {}", courseId));
            course.ifPresentOrElse(c -> logger.info("Found course with ID: {}", courseId),
                    () -> logger.info("No course found with ID: {}", courseId));
            return course;
        } catch (DataAccessException e) {
            logger.error("Error retrieving course with ID: {}: {}", courseId, e.getMessage());
            throw e;
        }
    }

    public Course updateCourse(Course updatedCourse) {
        try {
            Course course = courseRepository.save(updatedCourse);
            logger.info("Course updated successfully: {}", updatedCourse.getID());
        } catch (DataAccessException e) {
            logger.error("Error updating course with ID: {}: {}", updatedCourse.getID(), e.getMessage());
            throw e;
        }
        return updatedCourse;
    }

    public boolean deleteCourse(String courseId) {
        try {
            courseRepository.deleteById(courseId);
            logger.info("Course deleted successfully: {}", courseId);
            return true;
        } catch (DataAccessException e) {
            logger.error("Error occurred while deleting course with ID: {}: {}", courseId, e.getMessage());
            return false;
        }
    }

    public List<Course> searchCourses(String keyword) {
        // Custom repository method to implement or manual filtering after retrieval
        // For demonstration, assuming it's a manual filter after retrieval
        try {
            List<Course> allCourses = courseRepository.findAll();
            List<Course> filteredCourses = allCourses.stream()
                    .filter(course -> course.getName().contains(keyword) || course.getDescription().contains(keyword))
                    .collect(Collectors.toList());
            logger.info("Found {} courses containing keyword: {}", filteredCourses.size(), keyword);
            return filteredCourses;
        } catch (DataAccessException e) {
            logger.error("Error searching courses with keyword: {}: {}", keyword, e.getMessage());
            return new ArrayList<>();
        }
    }
}
