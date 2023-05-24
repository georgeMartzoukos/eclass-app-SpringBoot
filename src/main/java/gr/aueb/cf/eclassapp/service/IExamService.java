package gr.aueb.cf.eclassapp.service;

import gr.aueb.cf.eclassapp.model.Exam;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IExamService {
    List<Exam> getExamsOfStudent(Long studentId) throws EntityNotFoundException;
    List<Exam> getExamsOfCourse(Long courseId) throws EntityNotFoundException;
}
