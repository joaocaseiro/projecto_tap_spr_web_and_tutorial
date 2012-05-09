package com.grupoatwork.celebrities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrities.model.Role;

@Transactional(readOnly = true)
public interface RoleDao extends JpaRepository<Role, Long> {
	Role findById(long id);
}
