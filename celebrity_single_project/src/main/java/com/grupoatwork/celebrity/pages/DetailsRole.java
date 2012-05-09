package com.grupoatwork.celebrity.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.grupoatwork.celebrity.dao.RoleDao;
import com.grupoatwork.celebrity.entities.Role;

public class DetailsRole {
	@Inject
	private RoleDao roleDao;

	@Persist
	private Role role;
	private String title = "Role Details Page for Tapestry";

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getDistrict() {
		return role;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object onSuccess() {
		roleDao.save(role);
		return Index.class;
	}
}
