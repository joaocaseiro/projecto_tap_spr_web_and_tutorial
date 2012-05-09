package com.grupoatwork.celebrities.services;

import java.util.List;

import com.grupoatwork.celebrities.model.County;

public interface CountyService {
	public County save(County county);
	County findById(long id);
	List<County> findAll();
}
