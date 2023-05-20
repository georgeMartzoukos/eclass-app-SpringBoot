package gr.aueb.cf.eclassapp.rest;

import gr.aueb.cf.eclassapp.dto.ExamDTO;
import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Exam;
import gr.aueb.cf.eclassapp.model.Student;
import gr.aueb.cf.eclassapp.service.ICourseService;
import gr.aueb.cf.eclassapp.service.IExamService;
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
@RequestMapping("/api/exam")
public class ExamController {

    private final IStudentService studentService;
    private final IExamService examService;

    private final ICourseService courseService;
    @Autowired
    public ExamController(IStudentService studentService, IExamService examService, ICourseService courseService) {
        this.studentService = studentService;
        this.examService = examService;
        this.courseService = courseService;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<String> addExamsToStudent(@RequestBody ExamDTO examDTO) {
        Student student;
        Course course;
        try {
            course = courseService.getCourseById(examDTO.getCourseId());
            studentService.addExam(examDTO);
            return new ResponseEntity<>("Student with id: " + examDTO.getStudentId() + "took exams in course: " + course.getTitle(), HttpStatus.OK  );
        } catch (EntityNotFoundException | EntityAlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<List<ExamDTO>> getExamsByStudentId(@PathVariable ("studentId") Long studentId) {
        List<Exam> exams;
        List<ExamDTO> examsDTO = new ArrayList<>();
        try {
            exams = examService.getExamsOfStudent(studentId);
            for (Exam exam : exams) {
                ExamDTO examDTO = new ExamDTO(exam.getId(),exam.getStudent().getId(), exam.getCourse().getId(),exam.getGrade());
                examsDTO.add(examDTO);
            }
            return new ResponseEntity<>(examsDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
