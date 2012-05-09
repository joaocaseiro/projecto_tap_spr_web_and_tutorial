package com.grupoatwork.celebrity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrity.entities.Address;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class AddressDaoImpl implements AddressDao {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Address> getAddresses() {
		return entityManager
				.createQuery(
						"from Address p order by p.parish, p.streetName, p.streetNumber")
				.getResultList();
	}

	public Address read(Long id) {
		return (Address) entityManager
				.createQuery("from Address p where p.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Address save(Address parish) {
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
	public void delete(Address parish) {
		entityManager.remove(parish);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}