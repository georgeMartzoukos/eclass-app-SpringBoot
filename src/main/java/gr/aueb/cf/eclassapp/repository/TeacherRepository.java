package gr.aueb.cf.eclassapp.repository;

import gr.aueb.cf.eclassapp.model.Course;
import gr.aueb.cf.eclassapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByLastnameStartingWith(String lastname);

    Teacher findTeacherById(Long id);
//    @Query("SELECT count(*) > 0 from Teacher, t JOIN t.courses c WHERE t.id = ?1 and c.id =?2")
//    boolean courseExists(Long teacherId, Long courseId);

//    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Teacher t JOIN t.courses c WHERE t.id = ?1 AND c.id = ?2")
//    boolean courseExists(Long teacherId, Long courseId);




}
