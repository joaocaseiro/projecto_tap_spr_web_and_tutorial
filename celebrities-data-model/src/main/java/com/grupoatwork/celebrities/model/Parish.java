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
@Table(name = "Parish", uniqueConstraints = { @UniqueConstraint(columnNames = { "county_id", "parishCode" }) })
public class Parish extends TerritoryDivision {
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name="county_id")
	private County county;
	
	@NotNull
	@Column(name = "parishCode", nullable = false)
	private int code;
	
	@NotNull
	@Column(name="parishName", nullable = false, length = 60)
	@Size(min = 5, max = 60)
	private String name;

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
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
