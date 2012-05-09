package com.grupoatwork.celebrities.model.util;

import com.grupoatwork.celebrities.model.Address;
import com.grupoatwork.celebrities.model.County;
import com.grupoatwork.celebrities.model.District;
import com.grupoatwork.celebrities.model.Parish;
import com.grupoatwork.celebrities.model.TerritoryDivision;

public class AddressHandler implements TerritoryDivisionHandler {

	/** Constants for reading Counties **/
	private static final int DISTRICT_CODE_INDEX = 0;
	private static final int COUNTY_CODE_INDEX = 1;
	private static final int PARISH_CODE_INDEX = 2;

	// private static final int LOCALITY_CODE_INDEX = 3;
	// private static final int LOCALITY_NAME_INDEX = 4;
	/*
	 * private static final int ARTERY_CODE_INDEX = 5; private static final int
	 * ARTERY_NAME_INDEX = 6; //Street, Avenue, etc... private static final int
	 * FIRST_PREP_NAME = 7; private static final int ARTERY_TITLE_NAME = 8;
	 * //Doctor, Engineer, Professor, etc... private static final int
	 * SECOND_PREP_NAME = 9; private static final int ARTERY_DESIGNATION = 10;
	 * private static final int LOCAL_INFORMATION = 11; private static final int
	 * PARTIAL_ROAD_DESCRIPTION = 12; private static final int DOOR_NUMBER = 13;
	 * //empty if postal_code ends with 0 or 5 private static final int
	 * CLIENT_NAME = 14; //empty if postal_code ends with 0 or 5 private static
	 * final int POSTAL_CODE = 15; private static final int
	 * POSTAL_CODE_EXTENSION = 16; private static final int
	 * POSTAL_CODE_DESIGNATION = 17;
	 */

	@Override
	public TerritoryDivision handle(String line)
			throws UnsupportedFormatException {
		String[] parts = line.split(";");
		if (parts.length != 16) {
			throw new UnsupportedFormatException();
		}

		Address address = new Address();
		Parish parish = new Parish();
		County county = new County();
		District district = new District();

		int districtCode = Integer.parseInt(parts[DISTRICT_CODE_INDEX]);
		district.setCode(districtCode);

		int countyCode = Integer.parseInt(parts[COUNTY_CODE_INDEX]);
		county.setCode(countyCode);
		county.setDistrict(district);

		int parishCode = Integer.parseInt(parts[PARISH_CODE_INDEX]);
		parish.setCode(parishCode);
		parish.setCounty(county);

		// int localityCode = Integer.parseInt(parts[LOCALITY_CODE_INDEX]);
		// address.setLocalityCode(localityCode);

		// String localityName = parts[LOCALITY_NAME_INDEX];
		// address.setLocalityName(localityName);
		address.setParish(parish);

		return address;
	}

}
