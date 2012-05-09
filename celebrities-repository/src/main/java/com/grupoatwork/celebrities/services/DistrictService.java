package com.grupoatwork.celebrities.services;

import java.util.List;

import com.grupoatwork.celebrities.model.District;

public interface DistrictService {
	public District save(District district);
	District findById(long id);
	List<District> findAll();
}
