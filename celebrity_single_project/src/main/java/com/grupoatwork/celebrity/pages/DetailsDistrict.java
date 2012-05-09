package com.grupoatwork.celebrity.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.grupoatwork.celebrity.dao.DistrictDao;
import com.grupoatwork.celebrity.entities.District;

public class DetailsDistrict {
	@Inject
	private DistrictDao districtDao;
	
	@Persist
	private District district;
	private String title = "District Details Page for Tapestry";

	public void setDistrict(District district) {
		this.district = district;
	}
	
	public District getDistrict() {
		return district;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Object onSuccess() {
		districtDao.save(district);
		return Index.class;
	}
}
