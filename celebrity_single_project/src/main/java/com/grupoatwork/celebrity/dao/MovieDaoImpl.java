package com.grupoatwork.celebrity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrity.entities.Movie;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MovieDaoImpl implements MovieDao {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Movie> getMovies() {
		return entityManager.createQuery("from Movie p order by p.title")
				.getResultList();
	}

	public Movie read(Long id) {
		return (Movie) entityManager
				.createQuery("from Movie p where p.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Movie save(Movie movie) {
		if (movie.getId() == null || movie.getId() == 0) {
			entityManager.persist(movie);
		} else {
			movie = entityManager.merge(movie);
		}
		return movie;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		entityManager.remove(read(id));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Movie movie) {
		entityManager.remove(movie);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}