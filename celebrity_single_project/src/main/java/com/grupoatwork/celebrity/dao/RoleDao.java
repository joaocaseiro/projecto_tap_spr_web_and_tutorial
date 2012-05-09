package com.grupoatwork.celebrity.dao;

import java.util.List;

import com.grupoatwork.celebrity.entities.Role;

public interface RoleDao {
	public List<Role> getRoles();

	public Role read(Long id);

	public Role save(Role person);

	public void delete(Long id);

	public void delete(Role person);
}