package com.grupoatwork.celebrity.dao;

import java.util.List;

import com.grupoatwork.celebrity.entities.Celebrity;

public interface CelebrityDao {
    public List<Celebrity> getCelebrities();
    public List<Celebrity> getCelebritiesByFirstName(String firstName);
    public List<Celebrity> getCelebritiesByLastName(String lastName);
    
    public Celebrity read(Long id);
    
    public Celebrity save(Celebrity celebrity);
    
    public void delete(Long id);
    public void delete(Celebrity celebrity);
}