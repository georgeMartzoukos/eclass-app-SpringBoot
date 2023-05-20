package gr.aueb.cf.eclassapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "EXAM")
public class Exam {
    @Id
    @Column(name = "ID")
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "STUDENT_ID",  nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    @Column(name = "GRADE")
    @ToString.Exclude
    private int grade;


    public Exam(Student student, Course course, int grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;

    }

}
