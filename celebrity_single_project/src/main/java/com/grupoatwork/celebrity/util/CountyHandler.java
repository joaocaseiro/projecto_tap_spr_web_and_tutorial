package com.grupoatwork.celebrity.util;

import org.apache.tapestry5.ioc.annotations.Inject;

import com.grupoatwork.celebrity.dao.DistrictDao;
import com.grupoatwork.celebrity.entities.BaseDomain;
import com.grupoatwork.celebrity.entities.County;
import com.grupoatwork.celebrity.entities.District;

public class CountyHandler implements BaseHandler {

	@Inject
	private DistrictDao districtDao;

	private static final int DISTRICT_CODE_INDEX = 0;
	private static final int COUNTY_CODE_INDEX = 1;
	private static final int COUNTY_NAME_INDEX = 2;

	public BaseDomain handle(String line) throws UnsupportedFormatException {
		String[] parts = line.split(";");
		if (parts.length != 3) {
			throw new UnsupportedFormatException();
		}
		County county = new County();

		String districtCode = parts[DISTRICT_CODE_INDEX].trim();
		District district = districtDao.findByDistrictCode(districtCode);
		county.setDistrict(district);

		String code = parts[COUNTY_CODE_INDEX].trim();
		county.setCode(code);

		String name = parts[COUNTY_NAME_INDEX].trim();
		county.setName(name);

		return county;
	}

	public void setDistricts(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

}
