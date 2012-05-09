package com.grupoatwork.celebrity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrity.entities.Celebrity;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CelebrityDaoImpl implements CelebrityDao {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Celebrity> getCelebrities() {
		return entityManager
				.createQuery(
						"from Celebrity p order by p.lastName, p.firstName, p.phoneNumber")
				.getResultList();
	}

	public Celebrity read(Long id) {
		return (Celebrity) entityManager
				.createQuery("from Celebrity p where p.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Celebrity save(Celebrity person) {
		if (person.getId() == null || person.getId() == 0) {
			entityManager.persist(person);
		} else {
			person = entityManager.merge(person);
		}
		return person;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		entityManager.remove(read(id));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Celebrity person) {
		entityManager.remove(person);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}