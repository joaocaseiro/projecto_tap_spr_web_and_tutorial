package com.grupoatwork.celebrities.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.grupoatwork.celebrities.model.County;

public class CountyServiceImpl implements CountyService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public County save(County county) {
		if (county != null && county.getId() == 0) {
			entityManager.persist(county);
		} else {
			county = entityManager.merge(county);
		}
		return county;
	}

	@Override
	public County findById(long id) {
		return (County) entityManager
				.createQuery("from County d where d.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<County> findAll() {
		return entityManager.createQuery("from County c").getResultList();
	}

}
