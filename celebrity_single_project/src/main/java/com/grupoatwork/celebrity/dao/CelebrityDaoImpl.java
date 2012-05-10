package com.grupoatwork.celebrity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrity.entities.Celebrity;
import com.grupoatwork.celebrity.entities.Celebrity_;

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
	
	@Override
	public List<Celebrity> getCelebritiesByFirstName(String firstName) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Celebrity> query = builder.createQuery(Celebrity.class);
		Root<Celebrity> from = query.from(Celebrity.class);
		query.where(builder.equal(from.get(Celebrity_.firstName), firstName));
		CriteriaQuery<Celebrity> select = query.select(from);
		return entityManager.createQuery(select).getResultList();
	}
	
	@Override
	public List<Celebrity> getCelebritiesByLastName(String lastName) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Celebrity> query = builder.createQuery(Celebrity.class);
		Root<Celebrity> from = query.from(Celebrity.class);
		query.where(builder.equal(from.get(Celebrity_.lastName), lastName));
		CriteriaQuery<Celebrity> select = query.select(from);
		return entityManager.createQuery(select).getResultList();
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