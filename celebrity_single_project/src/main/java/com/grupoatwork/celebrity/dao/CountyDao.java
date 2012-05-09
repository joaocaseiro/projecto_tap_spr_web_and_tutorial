package com.grupoatwork.celebrity.dao;

import java.util.List;

import com.grupoatwork.celebrity.entities.County;
import com.grupoatwork.celebrity.entities.District;

public interface CountyDao {
	public List<County> getCounties();

	public County read(Long id);

	public County save(County person);

	public void delete(Long id);

	public void delete(County person);

	public County findByCountyCodeAndDistrict(String countyCode, District district);
}