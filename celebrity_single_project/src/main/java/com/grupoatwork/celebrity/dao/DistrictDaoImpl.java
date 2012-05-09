package com.grupoatwork.celebrity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrity.entities.District;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DistrictDaoImpl implements DistrictDao {
	@PersistenceContext
	private EntityManager entityManager;

	public List<District> getDistricts() {
		return entityManager
				.createQuery(
						"from District p order by p.code, p.name")
				.getResultList();
	}

	public District read(Long id) {
		return (District) entityManager
				.createQuery("from District p where p.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public District save(District district) {
		if (district.getId() == null || district.getId() == 0) {
			entityManager.persist(district);
		} else {
			district = entityManager.merge(district);
		}
		return district;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		entityManager.remove(read(id));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(District district) {
		entityManager.remove(district);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public District findByDistrictCode(String districtCode) {
		return (District) entityManager
				.createQuery("from District p where p.code = :code")
				.setParameter("code", districtCode).getSingleResult();
	}
}