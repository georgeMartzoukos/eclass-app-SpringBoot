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



}
