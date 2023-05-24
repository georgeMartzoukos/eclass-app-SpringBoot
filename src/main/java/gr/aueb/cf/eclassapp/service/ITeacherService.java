package gr.aueb.cf.eclassapp.service;

import gr.aueb.cf.eclassapp.dto.TeacherDTO;
import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Teacher;
import gr.aueb.cf.eclassapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface ITeacherService {
    Teacher insertTeacher(TeacherDTO teacherDTO) throws EntityAlreadyExistsException;
    Teacher updateTeacher(TeacherDTO teacherDTO) throws EntityNotFoundException;
    void deleteTeacher(Long id) throws EntityNotFoundException;
    List<Teacher> getTeachersByLastname(String lastname) throws EntityNotFoundException;
    Teacher getTeacherById(Long id) throws EntityNotFoundException;
    List<Teacher> getAllTeachers() throws EntityNotFoundException;
    void addCourseToTeacher(Long teacherId, Long courseId) throws EntityNotFoundException, EntityAlreadyExistsException;
    List<Course> getTeacherCourses(Long teacherId) throws EntityNotFoundException;
}
