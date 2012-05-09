package com.grupoatwork.celebrities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.grupoatwork.celebrities.model.Celebrity;

@Transactional(readOnly = true)
public interface CelebrityRepo extends JpaRepository<Celebrity, Long> {
	Celebrity findById(long id);
}
