package com.grupoatwork.celebrities.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.grupoatwork.celebrities.model.District;

public class DistrictServiceImpl implements DistrictService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public District save(District district) {
		if (district != null && (district.getId() == 0 || district.getId() == null)) {
			entityManager.persist(district);
		} else {
			district = entityManager.merge(district);
		}
		return district;
	}

	@Override
	public District findById(long id) {
		return (District) entityManager
				.createQuery("from District d where d.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> findAll() {
		return entityManager.createQuery("from District c").getResultList();
	}

}
