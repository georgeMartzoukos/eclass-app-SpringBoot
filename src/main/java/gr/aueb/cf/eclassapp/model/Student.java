package gr.aueb.cf.eclassapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "STUDENTS")
public class Student {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Exclude
    private Long id;

    @Column(name = "FIRSTNAME" , length = 50, nullable = true, unique = false)
    @ToString.Exclude
    private String firstname;

    @Column(name = "LASTNAME" , length = 50, nullable = true,unique = false)
    @ToString.Exclude
    private String lastname;

    public Student(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;

    }

    @ManyToMany
    @ToString.Exclude
    @JoinTable(name = "STUDENTS_COURSES", joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name ="COURSE_ID", referencedColumnName = "ID"))
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        this.courses.add(course);
        for (Student student : course.getStudents()) {
            if (student == this) {
                return;
            }
        }
        course.addStudent(this);
    }

    public void deleteCourse(Course course) {
        boolean found = false;
        this.courses.remove(course);

        for (Student student : course.getStudents()) {
            if (student == this) {
                found = true;
                break;
            }
        }

        if (found) course.deleteStudent(this);
    }

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<Exam> exams = new ArrayList<>();

    public void addExam(Exam exam) {
        this.exams.add(exam);

        exam.setStudent(this);
        exam.setCourse(exam.getCourse());
    }

    public void removeExam(Exam exam) {
        this.exams.remove(exam);
        exam.setStudent(null);
        exam.setCourse(null);
    }

}
