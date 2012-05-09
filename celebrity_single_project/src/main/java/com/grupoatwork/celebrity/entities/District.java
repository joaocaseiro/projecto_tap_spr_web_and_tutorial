package com.grupoatwork.celebrity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "District")
public class District extends BaseDomain {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "districtCode", nullable = false, length = 10, unique = true)
	@Size(min = 1, max = 10)
	private String code;

	@NotNull
	@Column(name = "districtName", nullable = false, length = 60)
	@Size(min = 2, max = 60)
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
