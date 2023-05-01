package gr.aueb.cf.eclassapp.repository;

import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>{
    List<Student> findStudentByLastnameStartingWith(String lastname);

    Student findStudentById(Long id);

//    @Query("SELECT C.title FROM Course C, STUDENTS_COURSES SC, Student S WHERE SC.COURSE_ID = C.id AND SC.STUDENT_ID = S.id AND S.id = ?1 ")
//    List<Course> getCoursesOfStudent(Long studentId);
    @Query("select count(*) > 0 from Student s JOIN s.courses c WHERE s.id = ?1 and c.id = ?2")
    boolean courseExists(Long studentId, Long courseId);

    @Query("SELECT c FROM Student s JOIN s.courses c WHERE s.id = ?1")
    List<Course> getCoursesOfStudent(Long studentId);
}
