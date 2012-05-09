package com.grupoatwork.celebrity.util;

import org.apache.tapestry5.ioc.annotations.Inject;

import com.grupoatwork.celebrity.dao.CountyDao;
import com.grupoatwork.celebrity.dao.DistrictDao;
import com.grupoatwork.celebrity.dao.ParishDao;
import com.grupoatwork.celebrity.entities.Address;
import com.grupoatwork.celebrity.entities.BaseDomain;
import com.grupoatwork.celebrity.entities.County;
import com.grupoatwork.celebrity.entities.District;
import com.grupoatwork.celebrity.entities.Parish;

public class AddressHandler implements BaseHandler {

	@Inject
	private ParishDao parishDao;
	
	@Inject
	private CountyDao countyDao;
	
	@Inject
	private DistrictDao districtDao;

	/** Constants for reading Counties **/
	private static final int DISTRICT_CODE_INDEX = 0;
	private static final int COUNTY_CODE_INDEX = 1;
	private static final int PARISH_CODE_INDEX = 2;

	//private static final int LOCALITY_CODE_INDEX = 3;
	//private static final int LOCALITY_NAME_INDEX = 4;
	//private static final int ARTERY_CODE_INDEX = 5; 
	private static final int ARTERY_NAME_INDEX = 6; //Street, Avenue, etc... 
	private static final int FIRST_PREP_NAME = 7; 
	private static final int ARTERY_TITLE_NAME = 8; //Doctor, Engineer, Professor, etc... 
	private static final int SECOND_PREP_NAME = 9; 
	private static final int ARTERY_DESIGNATION = 10;
	//private static final int LOCAL_INFORMATION = 11; 
	//private static final int PARTIAL_ROAD_DESCRIPTION = 12; 
	private static final int DOOR_NUMBER = 13; //empty if postal_code ends with 0 or 5 
	//private static final int CLIENT_NAME = 14; //empty if postal_code ends with 0 or 5 
	//private static final int POSTAL_CODE = 15; 
	//private static final int POSTAL_CODE_EXTENSION = 16; 
	//private static final int POSTAL_CODE_DESIGNATION = 17;

	public BaseDomain handle(String line) throws UnsupportedFormatException {
		String[] parts = line.split(";");
		if (parts.length != 18) {
			throw new UnsupportedFormatException();
		}

		Address address = new Address();

		String districtCode = parts[DISTRICT_CODE_INDEX];
		District district = districtDao.findByDistrictCode(districtCode);
		
		String countyCode = parts[COUNTY_CODE_INDEX];
		County county = countyDao.findByCountyCodeAndDistrict(countyCode, district);

		String parishCode = parts[PARISH_CODE_INDEX];
		Parish parish = parishDao.findByParishCodeAndCounty(parishCode, county);
		address.setParish(parish);
		
		try {
			int streetNumber = Integer.parseInt(parts[DOOR_NUMBER]);
			address.setStreetNumber(streetNumber);
		} catch(NumberFormatException e) {
			address.setStreetNumber(-1);
		}
		
		String streetName = parts[ARTERY_NAME_INDEX];
		streetName += parts[FIRST_PREP_NAME];
		streetName += parts[ARTERY_TITLE_NAME];
		streetName += parts[SECOND_PREP_NAME];
		streetName += parts[ARTERY_DESIGNATION];
		address.setStreetName(streetName);

		return address;
	}

	public void setParishes(ParishDao parishDao) {
		this.parishDao = parishDao;		
	}

	public void setCounties(CountyDao countyDao) {
		this.countyDao = countyDao;
	}

	public void setDistricts(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

}
