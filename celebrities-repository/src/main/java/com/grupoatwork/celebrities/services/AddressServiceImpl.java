package com.grupoatwork.celebrities.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrities.model.Address;

@Repository
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Address save(Address address) {
		if (address != null && address.getId() == 0) {
			entityManager.persist(address);
		} else {
			address = entityManager.merge(address);
		}
		return address;
	}

	@Override
	public Address findById(long id) {
		return (Address) entityManager
				.createQuery("from Address c where c.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> findAll() {
		return entityManager.createQuery("from Address c").getResultList();
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
