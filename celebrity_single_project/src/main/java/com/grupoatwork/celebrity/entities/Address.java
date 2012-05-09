package com.grupoatwork.celebrity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

@Entity
@Table(name="Address", uniqueConstraints = { @UniqueConstraint(columnNames = { "parish_id", "streetName", "streetNumber" }) })
public class Address extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name="parish_id")
	private Parish parish;
	
	@NotNull
	@Column(name="streetName", nullable = false, length = 60)
	@Size(min = 5, max = 60)
	private String streetName;
	
	@NotNull
	@Column(name="streetNumber", nullable = false)
	private int streetNumber;
	
/*	@NotNull
	@Column(name="localityCode", nullable = false)
	private int localityCode;
	
	@NotNull
	@Column(name="localityName", nullable = false)
	private String localityName;
	*/

	public Parish getParish() {
		return parish;
	}

	public void setParish(Parish parish) {
		this.parish = parish;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	/*public int getLocalityCode() {
		return localityCode;
	}

	public void setLocalityCode(int localityCode) {
		this.localityCode = localityCode;
	}

	public String getLocalityName() {
		return localityName;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}*/
}
