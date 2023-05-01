package gr.aueb.cf.eclassapp.repository;

import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTitleStartingWith(String Title);

    Course findCourseById(Long id);
}
