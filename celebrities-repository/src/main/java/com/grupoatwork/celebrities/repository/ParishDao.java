package com.grupoatwork.celebrities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrities.model.Parish;

@Transactional(readOnly = true)
public interface ParishDao extends JpaRepository<Parish, Long> {
	Parish findById(long id);
}
