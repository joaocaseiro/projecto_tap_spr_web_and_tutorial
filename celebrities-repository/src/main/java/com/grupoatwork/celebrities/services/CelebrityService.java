package com.grupoatwork.celebrities.services;

import java.util.List;

import com.grupoatwork.celebrities.model.Celebrity;

public interface CelebrityService {
	public Celebrity save(Celebrity celebrity);
	Celebrity findById(long id);
	List<Celebrity> findAll();
}
