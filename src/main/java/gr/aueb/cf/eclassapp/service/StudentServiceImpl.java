package gr.aueb.cf.eclassapp.service;

import gr.aueb.cf.eclassapp.dto.ExamDTO;
import gr.aueb.cf.eclassapp.dto.StudentDTO;
import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Exam;
import gr.aueb.cf.eclassapp.model.Student;
import gr.aueb.cf.eclassapp.repository.CourseRepository;
import gr.aueb.cf.eclassapp.repository.ExamRepository;
import gr.aueb.cf.eclassapp.repository.StudentRepository;
import gr.aueb.cf.eclassapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentServiceImpl implements IStudentService{

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ExamRepository examRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, ExamRepository examRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.examRepository = examRepository;
    }

    @Override
    public Student insertStudent(StudentDTO studentDTO) throws EntityAlreadyExistsException {
        return studentRepository.save(convertToStudent(studentDTO));
    }

    @Override
    public Student updateStudent(StudentDTO studentDTO) throws EntityNotFoundException {
        Student student = studentRepository.findStudentById(studentDTO.getId());
        if (student == null) throw new EntityNotFoundException(Student.class, studentDTO.getId());
        return studentRepository.save(convertToStudent(studentDTO));
    }

    @Override
    public void deleteStudent(Long id) throws EntityNotFoundException {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentsByLastname(String lastname) throws EntityNotFoundException {
        List<Student> students;
        students = studentRepository.findStudentByLastnameStartingWith(lastname);
        if (students.size() == 0) throw new EntityNotFoundException(Student.class, 0L);
        return students;
    }

    @Override
    public Student getStudentById(Long id) throws EntityNotFoundException {
        Student student;
        student = studentRepository.findStudentById(id);
        if (student == null) throw new EntityNotFoundException(Student.class, 0L);
        return student;
    }

    public void addCourse(Long courseId, Long studentId) throws EntityNotFoundException , EntityAlreadyExistsException {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) throw new EntityNotFoundException(Student.class, studentId);
        if (studentRepository.courseExists(studentId, courseId)) throw new EntityAlreadyExistsException(Course.class, courseId);
        Course course = courseRepository.findCourseById(courseId);
        if (course == null) throw new EntityNotFoundException(Course.class, courseId);

        student.addCourse(course);
        studentRepository.save(student);
    }
    @Override
    public void addExam(ExamDTO examDTO) throws EntityNotFoundException , EntityAlreadyExistsException {
        Student student = studentRepository.findStudentById(examDTO.getStudentId());
        if (student == null) throw new EntityNotFoundException(Student.class, examDTO.getStudentId());
        Course course = courseRepository.findCourseById(examDTO.getCourseId());
        if (course == null) throw new EntityNotFoundException(Course.class, examDTO.getCourseId());

        Exam exam = new Exam(student, course, examDTO.getGrade());
        examRepository.save(exam);
        student.addExam(exam);
        studentRepository.save(student);
    }

    @Override
    public List<Course> getStudentCourses(Long id) throws EntityNotFoundException {
        List<Course> courses = new ArrayList<>();
        if (studentRepository.findStudentById(id) == null) throw new EntityNotFoundException(Student.class, id);
        courses = studentRepository.getCoursesOfStudent(id);
        if (courses == null) throw new EntityNotFoundException(Course.class );
        return courses;
    }

    private static Student convertToStudent(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstname(dto.getFirstname());
        student.setLastname(dto.getLastname());
        return student;
    }
}
