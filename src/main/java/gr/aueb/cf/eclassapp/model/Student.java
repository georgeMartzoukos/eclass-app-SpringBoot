package gr.aueb.cf.eclassapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

    @Column(name = "FIRSTNAME" , length = 50, nullable = true, unique = false)
    private String firstname;

    @Column(name = "LASTNAME" , length = 50, nullable = true,unique = false)
    private String lastname;

    public Student(Long id, String firstname, String lastname, List<Course> courses) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;

    }

    @ManyToMany
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
}
