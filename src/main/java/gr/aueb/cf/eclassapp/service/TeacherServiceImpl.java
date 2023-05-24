package gr.aueb.cf.eclassapp.service;

import gr.aueb.cf.eclassapp.dto.TeacherDTO;
import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Teacher;
import gr.aueb.cf.eclassapp.repository.CourseRepository;
import gr.aueb.cf.eclassapp.repository.TeacherRepository;
import gr.aueb.cf.eclassapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.eclassapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    @Override
    public Teacher insertTeacher(TeacherDTO teacherDTO) throws EntityAlreadyExistsException {
//        Teacher teacher = convertToTeacher(teacherDTO);
//        System.out.println(teacher);
//        if ((teacherDTO.getId() == null)) {
//            teacher = teacherRepository.save(teacher);
//        } else {
//            throw new EntityAlreadyExistsException(Teacher.class, teacher.getId());
//        }
//        return teacher;
        return teacherRepository.save(convertToTeacher(teacherDTO));
    }

    @Transactional
    @Override
    public Teacher updateTeacher(TeacherDTO teacherDTO) throws EntityNotFoundException {
        //Optional<Teacher> teacher = teacherRepository.findById(teacherDTO.getId());
        //if (teacher.isEmpty()) throw new EntityNotFoundException(Teacher.class, teacherDTO.getId());
        Teacher teacher = teacherRepository.findTeacherById(teacherDTO.getId());
        if (teacher == null) throw new EntityNotFoundException(Teacher.class, teacherDTO.getId());
        return teacherRepository.save(convertToTeacher(teacherDTO));
        //return teacherRepository.save(convertToTeacherToUpdate(teacher, teacherDTO));
    }

    @Transactional
    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws EntityNotFoundException {
        List<Teacher> teachers;
        teachers = teacherRepository.findByLastnameStartingWith(lastname);
        if (teachers.size() == 0) throw new EntityNotFoundException(Teacher.class, 0L);
        return teachers;
    }

    @Override
    public Teacher getTeacherById(Long id) throws EntityNotFoundException {
        Optional<Teacher> teacher;
        teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) throw new EntityNotFoundException(Teacher.class, 0L);
        return teacher.get();
    }

    @Override
    public List<Teacher> getAllTeachers() throws EntityNotFoundException {
        List<Teacher> teachers;
        teachers = teacherRepository.findAll();
        if (teachers.size() == 0) throw new EntityNotFoundException(Teacher.class, 0L);
        return teachers;
    }

    @Override
    public void addCourseToTeacher(Long teacherId, Long courseId) throws EntityNotFoundException, EntityAlreadyExistsException {
        List<Course> courses = new ArrayList<>();
        List<Teacher> teachers = teacherRepository.findAll();

        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null) throw new EntityNotFoundException(Teacher.class, teacherId);

        Course course = courseRepository.findCourseById(courseId);
        if (course == null) throw new EntityNotFoundException(Course.class, courseId);

        for (Teacher teacher1: teachers ) {
            courses = teacher1.getCourses();
            for (Course course1 : courses) {
                if (course1.equals(course)) {
                    throw new EntityAlreadyExistsException(Course.class, courseId);
                }
            }
        }

        teacher.addCourse(course);
        teacherRepository.save(teacher);
    }

    @Override
    public List<Course> getTeacherCourses(Long teacherId) throws EntityNotFoundException {
        List<Course> courses = new ArrayList<>();
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null) throw new EntityNotFoundException(Teacher.class, teacherId);
        courses = teacher.getCourses();
        return courses;
    }

    private static Teacher convertToTeacher(TeacherDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }

}
