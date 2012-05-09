package com.grupoatwork.celebrity.dao;

import java.util.List;

import com.grupoatwork.celebrity.entities.County;
import com.grupoatwork.celebrity.entities.Parish;

public interface ParishDao {
	public List<Parish> getParishes();

	public Parish read(Long id);

	public Parish save(Parish person);

	public void delete(Long id);

	public void delete(Parish person);

	public Parish findByParishCodeAndCounty(String parishCode, County county);
}