package gr.aueb.cf.eclassapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TEACHERS")
public class Teacher {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses = new ArrayList<>();

    public Teacher(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
        course.setTeacher(this);
    }
}
