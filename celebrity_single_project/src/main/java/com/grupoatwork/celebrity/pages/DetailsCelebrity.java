package com.grupoatwork.celebrity.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.grupoatwork.celebrity.dao.CelebrityDao;
import com.grupoatwork.celebrity.entities.Celebrity;

public class DetailsCelebrity {
	@Inject
	private CelebrityDao celebrityDao;
	
	@Persist
	private Celebrity celebrity;
	private String title = "Celebrity Details Page for Tapestry";

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}
	
	public Celebrity getCelebrity() {
		return celebrity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Object onSuccess() {
		celebrityDao.save(celebrity);
		return Index.class;
	}
}
