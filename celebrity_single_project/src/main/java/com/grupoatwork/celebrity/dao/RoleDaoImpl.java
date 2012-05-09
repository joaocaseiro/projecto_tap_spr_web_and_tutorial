package com.grupoatwork.celebrity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrity.entities.Role;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class RoleDaoImpl implements RoleDao {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Role> getRoles() {
		return entityManager.createQuery("from Role p order by p.celebrity")
				.getResultList();
	}

	public Role read(Long id) {
		return (Role) entityManager.createQuery("from Role p where p.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Role save(Role role) {
		if (role.getId() == null || role.getId() == 0) {
			entityManager.persist(role);
		} else {
			role = entityManager.merge(role);
		}
		return role;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		entityManager.remove(read(id));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Role role) {
		entityManager.remove(role);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}