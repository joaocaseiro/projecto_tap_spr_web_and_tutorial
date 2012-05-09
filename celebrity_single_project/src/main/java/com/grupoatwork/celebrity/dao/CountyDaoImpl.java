package com.grupoatwork.celebrity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrity.entities.County;
import com.grupoatwork.celebrity.entities.District;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CountyDaoImpl implements CountyDao {
	@PersistenceContext
	private EntityManager entityManager;

	public List<County> getCounties() {
		return entityManager.createQuery(
				"from County p order by p.district, p.code, p.name")
				.getResultList();
	}

	public County read(Long id) {
		return (County) entityManager
				.createQuery("from County p where p.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public County save(County county) {
		if (county.getId() == null || county.getId() == 0) {
			entityManager.persist(county);
		} else {
			county = entityManager.merge(county);
		}
		return county;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		entityManager.remove(read(id));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(County county) {
		entityManager.remove(county);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public County findByCountyCodeAndDistrict(String countyCode, District district) {
		return (County) entityManager
				.createQuery("from County p where p.code = :code and p.district = :district_id")
				.setParameter("code", countyCode).setParameter("district_id", district).getSingleResult();
	}
}