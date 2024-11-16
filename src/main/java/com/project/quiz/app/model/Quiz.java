package com.project.quiz.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;


    //Quiz → Question: A single quiz can have multiple questions.
    //Question → Quiz: A single question can be associated with multiple quizzes.

    //When we use the @ManyToMany annotation, JPA automatically creates a join table to manage the relationship between the two entities.
    // This table will have two columns:
    //One column to store the Quiz IDs (foreign keys).
    //One column to store the Question IDs (foreign keys).
    //This table doesn't need to be explicitly created in model, and we don't have to define it in the code;
    // Hibernate/JPA handles it automatically.
    @ManyToMany
    private List<Question> questions;


}
