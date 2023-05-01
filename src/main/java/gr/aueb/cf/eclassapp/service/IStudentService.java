package gr.aueb.cf.eclassapp.service;

import gr.aueb.cf.eclassapp.dto.CourseDTO;
import gr.aueb.cf.eclassapp.dto.StudentDTO;
import gr.aueb.cf.eclassapp.dto.TeacherDTO;
import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Student;
import gr.aueb.cf.eclassapp.model.Teacher;
import gr.aueb.cf.eclassapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IStudentService {
    Student insertStudent(StudentDTO studentDTO) throws EntityAlreadyExistsException;
    Student updateStudent(StudentDTO studentDTO) throws EntityNotFoundException;
    void deleteStudent(Long id) throws EntityNotFoundException;
    List<Student> getStudentsByLastname(String lastname) throws EntityNotFoundException;
    Student getStudentById(Long id) throws EntityNotFoundException;
    void addCourse(Long courseId, Long studentId) throws EntityNotFoundException, EntityAlreadyExistsException;

    List<Course> getStudentCourses(Long id) throws EntityNotFoundException;
}
