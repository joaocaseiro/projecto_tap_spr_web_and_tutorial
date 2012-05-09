package com.grupoatwork.celebrities.pages;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.grupoatwork.celebrities.model.Address;
import com.grupoatwork.celebrities.model.Celebrity;
import com.grupoatwork.celebrities.model.County;
import com.grupoatwork.celebrities.model.District;
import com.grupoatwork.celebrities.model.Parish;
import com.grupoatwork.celebrities.model.TerritoryDivision;
import com.grupoatwork.celebrities.model.util.AddressFileReader;
import com.grupoatwork.celebrities.model.util.DistrictHandler;
import com.grupoatwork.celebrities.services.AddressService;
import com.grupoatwork.celebrities.services.CelebrityService;
import com.grupoatwork.celebrities.services.CountyService;
import com.grupoatwork.celebrities.services.DistrictService;
import com.grupoatwork.celebrities.services.ParishService;

public class Index {

	@Inject
	private DistrictService districtService;
	@Inject
	private CountyService countyService;
	@Inject
	private ParishService parishService;
	@Inject
	private AddressService addressService;
	@Inject
	private CelebrityService celebrityService;

	@Inject
	private DistrictHandler districtHandler;

	@Inject
	@Path("context:resources/distritos.txt")
	private Asset file;

	private String title = "Index Page for Tapestry";

	private District district;
	private County county;
	private Parish parish;
	private Address address;
	private Celebrity celebrity;

	void onActivate() {
		initAndLoad();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Parish getParish() {
		return parish;
	}

	public void setParish(Parish parish) {
		this.parish = parish;
	}

	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}

	public List<Celebrity> getCelebrities() {
		List<Celebrity> list = celebrityService.findAll();
		return list;
	}

	public List<District> getDistricts() {
		List<District> list = districtService.findAll();
		return list;
	}

	public List<County> getCounties() {
		List<County> list = countyService.findAll();
		return list;
	}

	public List<Parish> getParishes() {
		List<Parish> list = parishService.findAll();
		return list;
	}

	public List<Address> getAddresses() {
		List<Address> list = addressService.findAll();
		return list;
	}

	public void initAndLoad() {
		List<District> lists = getDistricts();
		if (lists != null && lists.size() == 0) {
			InputStream stream;
			try {
				stream = file.getResource().openStream();
				if (stream != null) {
					DistrictHandler handler = new DistrictHandler();
					List<TerritoryDivision> districts = AddressFileReader
							.districtReader(stream, handler);

					District district = null;
					for (int i = 0 ; i < districts.size() ; i++) {
						district = (District) districts.get(i);
						district.setId(new Long(i+1));
						district.setEntityVersion(new Long(1));
						System.out.println(district.toString());
						districtService.save((District) district);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
