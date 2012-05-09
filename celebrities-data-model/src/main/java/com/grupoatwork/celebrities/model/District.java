package com.grupoatwork.celebrities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "District")
public class District extends TerritoryDivision {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "districtCode", nullable = false)
	private int code;
	
	@NotNull
	@Column(name="districtName", nullable = false, length = 60)
	@Size(min = 5, max = 30)
	private String name;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
