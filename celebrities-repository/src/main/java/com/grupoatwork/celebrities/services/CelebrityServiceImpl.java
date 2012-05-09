package com.grupoatwork.celebrities.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrities.model.Celebrity;

@Repository
@Transactional(readOnly = true)
public class CelebrityServiceImpl implements CelebrityService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Celebrity save(Celebrity celebrity) {
		if (celebrity != null && celebrity.getId() == 0) {
			entityManager.persist(celebrity);
		} else {
			celebrity = entityManager.merge(celebrity);
		}
		return celebrity;
	}

	@Override
	public Celebrity findById(long id) {
		return (Celebrity) entityManager
				.createQuery("from Celebrity c where c.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Celebrity> findAll() {
		return entityManager.createQuery("from Celebrity c").getResultList();
	}

	/*
	 * @Autowired private CelebrityDao repository;
	 * 
	 * @Override
	 * 
	 * @Transactional public Celebrity save(Celebrity celebrity) { return
	 * repository.save(celebrity); }
	 * 
	 * @Override public Celebrity findById(long id) { return
	 * repository.findById(id); }
	 * 
	 * @Override public List<Celebrity> findAll() { return repository.findAll();
	 * }
	 */
}
