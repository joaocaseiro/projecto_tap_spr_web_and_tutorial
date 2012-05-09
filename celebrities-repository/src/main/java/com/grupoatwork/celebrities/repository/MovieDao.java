package com.grupoatwork.celebrities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrities.model.Movie;

@Transactional(readOnly = true)
public interface MovieDao extends JpaRepository<Movie, Long> {
	Movie findById(long id);
}
