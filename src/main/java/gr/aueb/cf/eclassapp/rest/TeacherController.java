package gr.aueb.cf.eclassapp.rest;

import gr.aueb.cf.eclassapp.dto.CourseDTO;
import gr.aueb.cf.eclassapp.dto.TeacherDTO;
import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Teacher;
import gr.aueb.cf.eclassapp.service.ITeacherService;
import gr.aueb.cf.eclassapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherController {
    private final ITeacherService teacherService;
    @Autowired
    public TeacherController(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(path ="/teachers", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherDTO>> getTeachersByLastname(@RequestParam("lastname") String lastname) {
        List<Teacher> teachers;
        try {
            teachers = teacherService.getTeachersByLastname(lastname);
            List<TeacherDTO> teachersDTO = new ArrayList<>();
            for (Teacher teacher : teachers) {
                teachersDTO.add(new TeacherDTO((teacher.getId()), teacher.getFirstname(), teacher.getLastname()));
            }
            return new ResponseEntity<>(teachersDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/teachers/{id}", method = RequestMethod.GET)
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") Long id) {
        Teacher teacher;
        TeacherDTO teacherDTO;
        try {
            teacher = teacherService.getTeacherById(id);
            teacherDTO = map(teacher);
            return new ResponseEntity<>(teacherDTO, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path ="/teachers/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherDTO>> getTeachersByLastname() {
        List<Teacher> teachers;
        try {
            teachers = teacherService.getAllTeachers();
            List<TeacherDTO> teachersDTO = new ArrayList<>();
            for (Teacher teacher : teachers) {
                teachersDTO.add(new TeacherDTO((teacher.getId()), teacher.getFirstname(), teacher.getLastname()));
            }
            return new ResponseEntity<>(teachersDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "teachers/getCourses/{teacherId}", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getCoursesOfTeacher(@PathVariable("teacherId") Long teacherId) {
        List<Course> courses;
        List<CourseDTO> coursesDTO = new ArrayList<>();
        try {
            courses = teacherService.getTeacherCourses(teacherId);
            for (Course course : courses) {
                CourseDTO courseDTO = new CourseDTO(course.getId(), course.getTitle(), course.getDescription());
                coursesDTO.add(courseDTO);
            }
            return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
        }catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/teachers/addCourse/{teacherId}/{courseId}", method = RequestMethod.POST)
    public ResponseEntity<String> addCourseToTeacher(@PathVariable("teacherId") Long teacherId, @PathVariable("courseId") Long courseId) {
        try {
            teacherService.addCourseToTeacher(teacherId, courseId);
            return new ResponseEntity<>("Course added succesfully", HttpStatus.OK);
        } catch (EntityNotFoundException | EntityAlreadyExistsException e) {
            return new ResponseEntity<>("This course is taken by this or an other teacher",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path ="/teachers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTeacherById(@PathVariable("id")Long id) {
        Teacher teacher;
        TeacherDTO teacherDTO;
        try {
            teacher = teacherService.getTeacherById(id);
            teacherDTO = map(teacher);
            teacherService.deleteTeacher(id);

            return new ResponseEntity<>("Teacher " + teacherDTO.getLastname() + "deleted successfully" ,HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.POST)
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody TeacherDTO teacherDTO) {
        Teacher teacher;
        try {
            teacher = teacherService.insertTeacher(teacherDTO);
            return new ResponseEntity<>(map(teacher), HttpStatus.CREATED);
        } catch (EntityAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateTeacher(@RequestBody TeacherDTO teacherDTO, @PathVariable ("id") Long id) {
        try {
            teacherDTO.setId(id);
            teacherService.updateTeacher(teacherDTO);

            return new ResponseEntity<>("Teacher with id " + teacherDTO.getId() + " updated", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    private TeacherDTO map(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setFirstname(teacher.getFirstname());
        teacherDTO.setLastname(teacher.getLastname());
        return teacherDTO;
    }
}
