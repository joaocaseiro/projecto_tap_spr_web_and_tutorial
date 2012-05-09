package com.grupoatwork.celebrity.dao;

import java.util.List;

import com.grupoatwork.celebrity.entities.Movie;

public interface MovieDao {
	public List<Movie> getMovies();

	public Movie read(Long id);

	public Movie save(Movie person);

	public void delete(Long id);

	public void delete(Movie person);
}