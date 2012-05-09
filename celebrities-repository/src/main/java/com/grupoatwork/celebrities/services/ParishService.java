package com.grupoatwork.celebrities.services;

import java.util.List;

import com.grupoatwork.celebrities.model.Parish;

public interface ParishService {
	public Parish save(Parish parish);
	Parish findById(long id);
	List<Parish> findAll();
}
