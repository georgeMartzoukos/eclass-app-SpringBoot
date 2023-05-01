package gr.aueb.cf.eclassapp.service;

import gr.aueb.cf.eclassapp.dto.CourseDTO;
import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Teacher;
import gr.aueb.cf.eclassapp.repository.CourseRepository;
import gr.aueb.cf.eclassapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CourseServiceImpl implements ICourseService{

    private  final CourseRepository courseRepository;
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course insertCourse(CourseDTO courseDTO) throws EntityAlreadyExistsException {
        return courseRepository.save(mapToCourse(courseDTO));
    }

    @Override
    public Course updateCourse(CourseDTO courseDTO) throws EntityNotFoundException {
        Course course = courseRepository.findCourseById(courseDTO.getId());
        if (course == null) throw new EntityNotFoundException(Course.class, course.getId());
        return courseRepository.save(mapToCourse(courseDTO));
    }

    @Override
    public void deleteCourse(Long id) throws EntityNotFoundException {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCoursesByTitle(String title) throws EntityNotFoundException {
        List<Course> courses = new ArrayList<>();
        courses = courseRepository.findByTitleStartingWith(title);
        if (courses == null) throw new EntityNotFoundException(Course.class);
        return courses;
    }

    @Override
    public Course getCourseById(Long id) throws EntityNotFoundException {
        Course course;
        course = courseRepository.findCourseById(id);
        if (course == null) throw new EntityNotFoundException(Course.class, 0L);
        return course;
    }

    private static Course mapToCourse(CourseDTO courseDTO) {
        return new Course(courseDTO.getId(),  courseDTO.getTitle(), courseDTO.getDescription());
    }
}
