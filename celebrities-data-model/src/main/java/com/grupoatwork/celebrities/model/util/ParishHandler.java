package com.grupoatwork.celebrities.model.util;

import com.grupoatwork.celebrities.model.County;
import com.grupoatwork.celebrities.model.District;
import com.grupoatwork.celebrities.model.Parish;
import com.grupoatwork.celebrities.model.TerritoryDivision;

public class ParishHandler implements TerritoryDivisionHandler {

	/** Constants for reading Counties **/
	private static final int DISTRICT_CODE_INDEX = 0;
	private static final int COUNTY_CODE_INDEX = 1;
	private static final int PARISH_CODE_INDEX = 2;
	private static final int PARISH_NAME_INDEX = 3;

	@Override
	public TerritoryDivision handle(String line)
			throws UnsupportedFormatException {
		String[] parts = line.split(";");
		if (parts.length != 4) {
			throw new UnsupportedFormatException();
		}

		Parish parish = new Parish();
		County county = new County();

		int districtCode = Integer.parseInt(parts[DISTRICT_CODE_INDEX]);
		District district = new District();
		district.setCode(districtCode);

		county.setDistrict(district);

		int countyCode = Integer.parseInt(parts[COUNTY_CODE_INDEX]);
		county.setCode(countyCode);

		parish.setCounty(county);

		int parishCode = Integer.parseInt(parts[PARISH_CODE_INDEX]);
		parish.setCode(parishCode);

		String parishName = parts[PARISH_NAME_INDEX];
		parish.setName(parishName);

		return parish;
	}

}
