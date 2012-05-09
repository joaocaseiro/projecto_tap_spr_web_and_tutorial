package com.grupoatwork.celebrities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrities.model.County;

@Transactional(readOnly = true)
public interface CountyDao extends JpaRepository<County, Long> {
	County findById(long id);
}
