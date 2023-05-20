package gr.aueb.cf.eclassapp.service;

import gr.aueb.cf.eclassapp.model.Exam;
import gr.aueb.cf.eclassapp.model.Student;
import gr.aueb.cf.eclassapp.repository.ExamRepository;
import gr.aueb.cf.eclassapp.repository.StudentRepository;
import gr.aueb.cf.eclassapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExamServiceImpl implements IExamService{

    private final ExamRepository examRepository;

    private final StudentRepository studentRepository;

    public ExamServiceImpl(ExamRepository examRepository, StudentRepository studentRepository) {
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Exam> getExamsOfStudent(Long studentId) throws EntityNotFoundException {
        Student student;
        List<Exam> exams = new ArrayList<>();
        student = studentRepository.findStudentById(studentId);
        if (student == null) throw new EntityNotFoundException(Student.class, studentId);
        exams = student.getExams();
        System.out.println(exams);
        return exams;
    }
}
