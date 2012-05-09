package com.grupoatwork.celebrities.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Celebrity")
public class Celebrity extends BaseDomain {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, max = 35)
	@Column(name = "firstName", nullable = false, length = 35)
	private String firstName;

	@NotNull
	@Size(min = 2, max = 25)
	@Column(name = "lastName", nullable = false, length = 25)
	private String lastName;

	@NotNull
	@OneToOne(optional=false)
    @JoinColumn(name="address_id", nullable=false)
	private Address address;
	/*
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="celebrityOccupationList")
	@Enumerated(EnumType.STRING)
	private Set<Occupation> roles = new HashSet<Occupation>();
	*/
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="celebrity")
    private Set<Role> roles;
	
	@NotNull
	@Column(name = "country", nullable = false)
	@Enumerated(EnumType.STRING)
	private Country country;
	
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
