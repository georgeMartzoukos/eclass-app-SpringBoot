package gr.aueb.cf.eclassapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamDTO {
    private Long id;
    private Long studentId;
    private Long courseId;
    private int grade;


    public ExamDTO(Long id, Long studentId, Long courseId, int grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;

    }
}
