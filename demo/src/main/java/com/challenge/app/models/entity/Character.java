package com.challenge.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.challenge.app.models.utils.Views;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
@Entity
@Table(name="characters")
public class Character implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long idCharacter;

    @JsonView(Views.Public.class)
    @Column(nullable = true)
    @Size(min = 2, message = "Imagen url is not correct.")
    private String image;
        
    @NotNull(message = "Name cannot be null.")
    @NotEmpty(message = "Name is required.")
    @Column(length = 100, nullable = false)
    @JsonView(Views.Public.class)
    private String name;
    
    @Column()
    @NotNull(message = "Age cannot be null.")
    @NotEmpty(message = "Age is required.")
    private Integer age;

    @Column
    @NotNull(message = "Weight cannot be null.")
    @NotEmpty(message = "Weight is required.")
    private Double weight;
    
    @Column
    @NotNull(message = "History cannot be null.")
    @NotEmpty(message = "History is required.")
    private String history;

    @JsonIgnoreProperties("characters")
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(name = "characters_movies", 
        joinColumns = @JoinColumn(name = "characters_id"), 
        inverseJoinColumns = @JoinColumn(name = "movies_id"))
	private List<Movie> movies = new ArrayList<Movie>();

    public List<Movie> getMovies() {
		return this.movies;
	}

	public void addMovies(Movie movie) {
		this.movies.add(movie);
	}
	
	public void removeMovies() {
		this.movies.removeAll(movies);
	}

}
