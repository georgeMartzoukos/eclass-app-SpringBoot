package gr.aueb.cf.eclassapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "COURSES")
public class Course {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name ="TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;

    public Course(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @ManyToMany
    @JoinTable(name = "COURSES_STUDENTS", joinColumns =  @JoinColumn(name = "COURSES_ID", referencedColumnName = "ID"),
                inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName =  "ID"))
    private List<Student> students = new ArrayList<>();


    public void addStudent(Student student) {
        this.students.add(student);
        for (Course course : student.getCourses()) {
            if (course == this) {
                return;
            }
        }
        student.addCourse(this);
    }

    public void deleteStudent(Student student) {
        boolean found = false;
        this.students.remove(student);

        for (Course course : student.getCourses()) {
            if (course == this) {
                found = true;
                break;
            }
        }
        if (found) student.deleteCourse(this);
    }
}
