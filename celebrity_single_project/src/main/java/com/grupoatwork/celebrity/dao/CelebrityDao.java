package com.grupoatwork.celebrity.dao;

import java.util.List;

import com.grupoatwork.celebrity.entities.Celebrity;

public interface CelebrityDao {
    public List<Celebrity> getCelebrities();
    public Celebrity read(Long id);
    public Celebrity save(Celebrity person);
    public void delete(Long id);
    public void delete(Celebrity person);
}