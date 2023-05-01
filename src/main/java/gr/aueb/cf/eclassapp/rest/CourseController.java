package gr.aueb.cf.eclassapp.rest;

import gr.aueb.cf.eclassapp.dto.CourseDTO;
import gr.aueb.cf.eclassapp.dto.TeacherDTO;
import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Teacher;
import gr.aueb.cf.eclassapp.service.ICourseService;
import gr.aueb.cf.eclassapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {
    private final ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CourseDTO> getCourse(@PathVariable("id") Long id) {
        Course course;
        CourseDTO courseDTO;
        try {
            course = courseService.getCourseById(id);
            courseDTO = mapCourse(course);
            return new ResponseEntity<>(courseDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getCourseByTitle(@RequestParam("title") String title) {
        List<Course> courses = new ArrayList<>();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        try {
            System.out.println("it got here 1");
            courses = courseService.getCoursesByTitle(title);
            System.out.println("it got here 2");

            for (int i = 0; i < courses.size(); i++) {
                CourseDTO courseDTO1 = mapCourse(courses.get(i));
                courseDTOS.add(courseDTO1);
            }
//            courses.forEach(course -> {
//                CourseDTO courseDTO = mapCourse(course);
//                courseDTOS.add(courseDTO);
//            });

            return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        Course course;
        try {
             course = courseService.insertCourse(courseDTO);
            return new ResponseEntity<>(mapCourse(course), HttpStatus.CREATED);
        } catch (EntityAlreadyExistsException e ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private CourseDTO mapCourse(Course course) {
        return new CourseDTO(course.getId(), course.getTitle(), course.getDescription());

    }


}
