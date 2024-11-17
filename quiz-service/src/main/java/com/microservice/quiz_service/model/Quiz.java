package com.microservice.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.bouncycastle.util.Integers;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;



    //@ManyToMany Represents a relationship between two entities.
    //The previous mapping implied a mapping where questions refers to another entity (e.g., a Question entity). The database would create a join table to map the many-to-many relationship between the owning entity and the Question entity.
    //Represents a collection of values rather than a relationship between entities.
    //The questionIds are simple values (not entities), stored in a separate table that maps these integers to the owning entity.
    @ElementCollection
    private List<Integer> questionIds;


}
