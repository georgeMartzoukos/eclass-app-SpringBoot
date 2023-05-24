package gr.aueb.cf.eclassapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamDTO {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
    private int grade;


    public ExamDTO(Long id, Long studentId, String studentName, Long courseId, String courseName, int grade) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
    }
}
