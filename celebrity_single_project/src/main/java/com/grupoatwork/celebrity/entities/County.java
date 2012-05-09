package com.grupoatwork.celebrity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "County", uniqueConstraints = { @UniqueConstraint(columnNames = { "district_id", "countyCode" }) })
public class County extends BaseDomain {
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name="district_id")
	private District district;
	
	@NotNull
	@Column(name = "countyCode", nullable = false, length = 10)
	@Size(min = 1, max = 5)
	private String code;
	
	@NotNull
	@Column(name = "countyName", nullable = false, length = 60)
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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
}
