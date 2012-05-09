package com.grupoatwork.celebrities.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.UniqueConstraint;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Role", uniqueConstraints = { @UniqueConstraint(columnNames = { "celebrity_id", "movie_id" }) })
public class Role extends BaseDomain {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="celebrity_id")
	private Celebrity celebrity;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="movie_id")
	private Movie movie;
	
	@NotNull
	@Column(name="occupation")
	@Enumerated(EnumType.STRING)
	private Occupation occupation;
	
	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
}
