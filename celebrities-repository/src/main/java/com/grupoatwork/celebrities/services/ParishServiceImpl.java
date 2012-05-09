package com.grupoatwork.celebrities.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.grupoatwork.celebrities.model.Parish;

public class ParishServiceImpl implements ParishService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Parish save(Parish parish) {
		if (parish != null && parish.getId() == 0) {
			entityManager.persist(parish);
		} else {
			parish = entityManager.merge(parish);
		}
		return parish;
	}

	@Override
	public Parish findById(long id) {
		return (Parish) entityManager
				.createQuery("from Parish d where d.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parish> findAll() {
		return entityManager.createQuery("from Parish c").getResultList();
	}

}
