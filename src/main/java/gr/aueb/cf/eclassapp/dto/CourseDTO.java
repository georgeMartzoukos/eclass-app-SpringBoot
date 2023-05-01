package gr.aueb.cf.eclassapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String title;
    private String description;

    public CourseDTO(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
