package com.grupoatwork.celebrities.model.util;

import com.grupoatwork.celebrities.model.District;
import com.grupoatwork.celebrities.model.TerritoryDivision;

public class DistrictHandler implements TerritoryDivisionHandler {
	/** Constants for reading Districts **/
	private static final int DISTRICT_CODE_INDEX = 0;
	private static final int DISTRICT_NAME_INDEX = 1;
	
	@Override
	public TerritoryDivision handle(String line) throws UnsupportedFormatException {
		String[] parts = line.split(";");
		if(parts.length != 2) {
			throw new UnsupportedFormatException();
		}
		District district = new District();
		
		int districtCode = Integer.parseInt(parts[DISTRICT_CODE_INDEX]);
		district.setCode(districtCode);
		
		String districtName = parts[DISTRICT_NAME_INDEX];
		district.setName(districtName);
		
		return district;
	}
}
