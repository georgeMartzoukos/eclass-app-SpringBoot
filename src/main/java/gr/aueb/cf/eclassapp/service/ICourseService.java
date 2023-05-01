package gr.aueb.cf.eclassapp.service;

import gr.aueb.cf.eclassapp.dto.CourseDTO;
import gr.aueb.cf.eclassapp.dto.TeacherDTO;
import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Teacher;
import gr.aueb.cf.eclassapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface ICourseService {
    Course insertCourse(CourseDTO courseDTO) throws EntityAlreadyExistsException;
    Course updateCourse(CourseDTO courseDTO) throws EntityNotFoundException;
    void deleteCourse(Long id) throws EntityNotFoundException;
    List<Course> getCoursesByTitle(String lastname) throws EntityNotFoundException;
    Course getCourseById(Long id) throws EntityNotFoundException;
}
