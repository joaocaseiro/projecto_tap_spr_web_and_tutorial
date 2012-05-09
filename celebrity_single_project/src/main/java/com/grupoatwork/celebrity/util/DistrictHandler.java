package com.grupoatwork.celebrity.util;

import com.grupoatwork.celebrity.entities.BaseDomain;
import com.grupoatwork.celebrity.entities.District;

public class DistrictHandler implements BaseHandler {

	private static final int DISTRICT_CODE_INDEX = 0;
	private static final int DISTRICT_NAME_INDEX = 1;

	public BaseDomain handle(String line) throws UnsupportedFormatException {
		String[] parts = line.split(";");
		if(parts.length != 2) {
			throw new UnsupportedFormatException();
		}
		District district = new District();
		
		String code = parts[DISTRICT_CODE_INDEX].trim();
		district.setCode(code);
		
		String name = parts[DISTRICT_NAME_INDEX].trim();
		district.setName(name);
		
		return district;
	}

}
