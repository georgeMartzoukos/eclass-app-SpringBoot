package gr.aueb.cf.eclassapp.rest;

import gr.aueb.cf.eclassapp.dto.CourseDTO;
import gr.aueb.cf.eclassapp.dto.StudentDTO;
import gr.aueb.cf.eclassapp.dto.ExamDTO;
import gr.aueb.cf.eclassapp.dto.TeacherDTO;
import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Student;
import gr.aueb.cf.eclassapp.model.Teacher;
import gr.aueb.cf.eclassapp.service.ICourseService;
import gr.aueb.cf.eclassapp.service.IStudentService;
import gr.aueb.cf.eclassapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final IStudentService studentService;
    private final ICourseService courseService;
    @Autowired
    public StudentController(IStudentService studentService, ICourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @RequestMapping(path ="/", method = RequestMethod.GET)
    public ResponseEntity<List<StudentDTO>> getTeachersByLastname(@RequestParam("lastname") String lastname) {
        List<Student> students;
        try {
            students = studentService.getStudentsByLastname(lastname);
            List<StudentDTO> studentDTO = new ArrayList<>();
            for (Student student : students) {
                studentDTO.add(new StudentDTO((student.getId()), student.getFirstname(), student.getLastname()));
            }
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        try {
            studentService.insertStudent(studentDTO);
            return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
        } catch (EntityAlreadyExistsException e ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{studentId}/{courseId}", method = RequestMethod.POST)
    public ResponseEntity<String> addCourseInStudent(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
        Student student;
        try {
            studentService.addCourse(courseId, studentId);
            student = studentService.getStudentById(studentId);
            return new ResponseEntity<>("Course with id = " + courseId + " has been added to student " + student.getLastname(), HttpStatus.OK );
        }catch (EntityNotFoundException  | EntityAlreadyExistsException ex) {
            return new ResponseEntity<>("Student not found or he/she has already that course" ,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getCoursesOfStudent(@PathVariable("studentId") Long studentId) {
        List<Course> courses = new ArrayList<>();
        List<CourseDTO> courseDTOS = new ArrayList<>();


        try {
            courses = studentService.getStudentCourses(studentId);
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                CourseDTO courseDTO = mapCourses(course);
                courseDTOS.add(courseDTO);
            }
            return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
        } catch ( EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }




    private StudentDTO mapStudent(Student student) {
        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getFirstname(), student.getLastname());
        return studentDTO;
    }

    private CourseDTO mapCourses(Course course) {
        return  new CourseDTO(course.getId(), course.getTitle(), course.getDescription());
    }
}
