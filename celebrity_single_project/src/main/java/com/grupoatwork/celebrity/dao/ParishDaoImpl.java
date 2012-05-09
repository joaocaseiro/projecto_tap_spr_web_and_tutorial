package com.grupoatwork.celebrity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrity.entities.County;
import com.grupoatwork.celebrity.entities.Parish;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ParishDaoImpl implements ParishDao {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Parish> getParishes() {
		return entityManager.createQuery(
				"from Parish p order by p.county, p.code, p.name")
				.getResultList();
	}

	public Parish read(Long id) {
		return (Parish) entityManager
				.createQuery("from Parish p where p.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Parish save(Parish parish) {
		if (parish.getId() == null || parish.getId() == 0) {
			entityManager.persist(parish);
		} else {
			parish = entityManager.merge(parish);
		}
		return parish;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		entityManager.remove(read(id));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Parish parish) {
		entityManager.remove(parish);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Parish findByParishCodeAndCounty(String parishCode, County county) {
		return (Parish) entityManager
				.createQuery(
						"from Parish p where p.code = :code and p.county = :county")
				.setParameter("code", parishCode).setParameter("county", county)
				.getSingleResult();
	}
}