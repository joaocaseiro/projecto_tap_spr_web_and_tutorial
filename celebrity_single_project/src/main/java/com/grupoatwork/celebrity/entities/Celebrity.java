package com.grupoatwork.celebrity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Celebrity")
public class Celebrity extends BaseDomain {
	private static final long serialVersionUID = 7330834323227961513L;

	@Column(name = "firstName", length = 15, nullable = false)
	private String firstName;

	@Column(name = "lastName", length = 15, nullable = false)
	private String lastName;

	@Column(name = "phonenr", length = 10)
	private String phoneNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}