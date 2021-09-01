package com.challenge.app.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.challenge.app.models.utils.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;

    @JsonView(Views.Public.class)
    @Column(nullable = true)
    @Size(min = 2, message = "Imagen url is not correct.")
    private String image;
    
    @JsonView(Views.Public.class)
    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Title is required.")
    @Size(min = 2, message = "Title must have more than 2 letters.")
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_at")
    @JsonView(Views.Public.class)
    private LocalDate createAt;

    @Min(value=1, message="Must be equal or more than 1")  
    @Max(value=5, message="Must be equal or less than 5")
    @Column(nullable = true)
    private Integer score;

    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_genre")
    private Genre genre;

    @JsonIgnoreProperties("movies")
    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, 
            fetch = FetchType.LAZY)
    private List<Character> characters = new ArrayList<Character>();

    @PrePersist
    public void prePersist() {
        createAt = LocalDate.now();
    }

}
