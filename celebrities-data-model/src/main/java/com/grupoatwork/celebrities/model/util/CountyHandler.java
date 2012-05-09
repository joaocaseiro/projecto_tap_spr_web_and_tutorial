package com.grupoatwork.celebrities.model.util;

import com.grupoatwork.celebrities.model.County;
import com.grupoatwork.celebrities.model.District;
import com.grupoatwork.celebrities.model.TerritoryDivision;

public class CountyHandler implements TerritoryDivisionHandler {

	/** Constants for reading Counties **/
	private static final int DISTRICT_CODE_INDEX = 0;
	private static final int COUNTY_CODE_INDEX = 0;
	private static final int COUNTY_NAME_INDEX = 1;

	@Override
	public TerritoryDivision handle(String line) throws UnsupportedFormatException {
		String[] parts = line.split(";");
		if (parts.length != 3) {
			throw new UnsupportedFormatException();
		}
		County county = new County();

		int districtCode = Integer.parseInt(parts[DISTRICT_CODE_INDEX]);

		District district = new District();
		district.setCode(districtCode);
		county.setDistrict(district);

		int countyCode = Integer.parseInt(parts[COUNTY_CODE_INDEX]);
		county.setCode(countyCode);

		String countyName = parts[COUNTY_NAME_INDEX];
		county.setName(countyName);

		return county;
	}

}
