package com.challenge.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name="genres")
public class Genre implements Serializable {
    
    public static final Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long idGenre;

    @Column()
    @NotNull(message = "Name cannot be null.")
    @NotEmpty(message = "Name is required.")
    private String name;

    @Column(nullable = true)
    @Size(min = 2, message = "Imagen url is not correct.")
    private String image;

    @JsonBackReference
	@OneToMany(mappedBy = "genre", cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Movie> movies = new ArrayList<Movie>();
    
}
