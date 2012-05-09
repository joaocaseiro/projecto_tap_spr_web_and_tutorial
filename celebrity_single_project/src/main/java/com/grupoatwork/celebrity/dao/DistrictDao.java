package com.grupoatwork.celebrity.dao;

import java.util.List;

import com.grupoatwork.celebrity.entities.District;

public interface DistrictDao {
	public List<District> getDistricts();

	public District read(Long id);

	public District save(District person);

	public void delete(Long id);

	public void delete(District district);

	public District findByDistrictCode(String districtCode);
}