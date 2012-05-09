package com.grupoatwork.celebrity.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Movie")
public class Movie extends BaseDomain {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, max = 35)
	@Column(name = "title", nullable = false, length = 35)
	private String title;
	
	@NotNull
	@Column(name = "lengthInMinutes", nullable = false)
	private int lengthInMinutes;
	
	@NotNull	
	@Column(name="releaseDate")
	private Date releaseDate;	
	
	@Size(min = 1000)	
	@Column(name="budgetInEuros")
	private int budgetInEuros;
	
	@Size(min = 1000)	
	@Column(name="costInEuros")
	private int costInEuros;
	
	@Size(min = 1000)	
	@Column(name="revenueInEuros")
	private int revenueInEuros;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="movie")
    private Set<Role> roles;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLengthInMinutes() {
		return lengthInMinutes;
	}

	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getBudgetInEuros() {
		return budgetInEuros;
	}

	public void setBudgetInEuros(int budgetInEuros) {
		this.budgetInEuros = budgetInEuros;
	}

	public int getCostInEuros() {
		return costInEuros;
	}

	public void setCostInEuros(int costInEuros) {
		this.costInEuros = costInEuros;
	}
	
	public int getRevenueInEuros() {
		return revenueInEuros;
	}

	public void setRevenueInEuros(int revenueInEuros) {
		this.revenueInEuros = revenueInEuros;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
