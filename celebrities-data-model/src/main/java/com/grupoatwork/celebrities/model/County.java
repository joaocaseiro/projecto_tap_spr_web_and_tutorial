package com.grupoatwork.celebrities.model;

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
public class County extends TerritoryDivision {
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name="district_id")
	private District district;
	
	@NotNull
	@Column(name="countyCode", nullable = false)
	private int code;
	
	@NotNull
	@Column(name="countyName", nullable = false, length = 60)
	@Size(min = 5, max = 60)
	private String name;

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

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
