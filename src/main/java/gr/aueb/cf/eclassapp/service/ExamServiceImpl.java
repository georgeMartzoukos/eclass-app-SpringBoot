package gr.aueb.cf.eclassapp.service;

import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Exam;
import gr.aueb.cf.eclassapp.model.Student;
import gr.aueb.cf.eclassapp.repository.CourseRepository;
import gr.aueb.cf.eclassapp.repository.ExamRepository;
import gr.aueb.cf.eclassapp.repository.StudentRepository;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExamServiceImpl implements IExamService{

    private final ExamRepository examRepository;

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public ExamServiceImpl(ExamRepository examRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Exam> getExamsOfStudent(Long studentId) throws EntityNotFoundException {
        Student student;
        List<Exam> exams = new ArrayList<>();
        student = studentRepository.findStudentById(studentId);
        if (student == null) throw new EntityNotFoundException(Student.class, studentId);
        exams = student.getExams();
        return exams;
    }

    @Override
    public List<Exam> getExamsOfCourse(Long courseId) throws EntityNotFoundException {
        Course course;
        List<Exam> exams = new ArrayList<>();
        course =  courseRepository.findCourseById(courseId);
        if (course == null) throw new EntityNotFoundException(Course.class, courseId);
        exams = course.getExams();
        return exams;
    }
}
