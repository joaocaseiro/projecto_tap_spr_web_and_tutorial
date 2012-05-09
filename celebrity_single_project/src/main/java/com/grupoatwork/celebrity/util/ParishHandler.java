package com.grupoatwork.celebrity.util;

import org.apache.tapestry5.ioc.annotations.Inject;

import com.grupoatwork.celebrity.dao.CountyDao;
import com.grupoatwork.celebrity.dao.DistrictDao;
import com.grupoatwork.celebrity.entities.BaseDomain;
import com.grupoatwork.celebrity.entities.County;
import com.grupoatwork.celebrity.entities.District;
import com.grupoatwork.celebrity.entities.Parish;

public class ParishHandler implements BaseHandler {

	@Inject
	private CountyDao countyDao;
	
	@Inject
	private DistrictDao districtDao;

	private static final int DISTRICT_CODE_INDEX = 0;
	private static final int COUNTY_CODE_INDEX = 1;
	private static final int PARISH_CODE_INDEX = 2;
	private static final int PARISH_NAME_INDEX = 3;

	public BaseDomain handle(String line) throws UnsupportedFormatException {
		String[] parts = line.split(";");
		if (parts.length != 4) {
			throw new UnsupportedFormatException();
		}
		Parish parish = new Parish();

		String districtCode = parts[DISTRICT_CODE_INDEX].trim();
		District district = districtDao.findByDistrictCode(districtCode);
		
		String countyCode = parts[COUNTY_CODE_INDEX].trim();
		County county = countyDao.findByCountyCodeAndDistrict(countyCode, district);
		parish.setCounty(county);

		String code = parts[PARISH_CODE_INDEX].trim();
		parish.setCode(code);

		String name = parts[PARISH_NAME_INDEX].trim();
		parish.setName(name);

		return parish;
	}

	public void setCounties(CountyDao countyDao) {
		this.countyDao = countyDao;
	}

	public void setDistricts(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

}
