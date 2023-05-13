package br.com.projetoapi.projetoapi.models;

import br.com.projetoapi.projetoapi.models.dto.CoursesDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double price;

    public Course() {
    }

    public Course(CoursesDto request) {
        this.title = request.title;
        this.description = request.description;
        this.price = request.price;
    }
    
}
