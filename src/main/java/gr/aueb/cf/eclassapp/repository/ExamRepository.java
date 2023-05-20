package gr.aueb.cf.eclassapp.repository;

import gr.aueb.cf.eclassapp.model.Exam;
import gr.aueb.cf.eclassapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
//    @Query("SELECT e FROM Student s JOIN s.exams e WHERE s.id = ?1" )
//    List<Exam> studentsExams(Long studentId);

    //List<Exam> findExamByStudent_id(Long studentId);
    @Query(value = "SELECT * FROM exam where student_id = ?1", nativeQuery = true)
    List<Exam> findExamByStudentId(Long studentId);
}
