package com.grupoatwork.celebrity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "Celebrity")
@XmlRootElement(name = "celebrity")
@XmlType(name = "", propOrder = { "id", "version", "firstName", "lastName", "phonenr" })
public class Celebrity extends BaseDomain {
	private static final long serialVersionUID = 7330834323227961513L;

	@XmlElement
	@Column(name = "firstName", length = 15, nullable = false)
	private String firstName;

	@XmlElement
	@Column(name = "lastName", length = 15, nullable = false)
	private String lastName;

	@XmlElement
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