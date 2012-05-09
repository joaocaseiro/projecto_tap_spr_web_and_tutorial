package com.grupoatwork.celebrity.util;

import com.grupoatwork.celebrity.entities.BaseDomain;
import com.grupoatwork.celebrity.entities.Celebrity;

public class CelebrityHandler implements BaseHandler {

	private static final int FIRST_NAME_INDEX = 0;
	private static final int LAST_NAME_INDEX = 1;
	private static final int PHONENR_INDEX = 2;

	public BaseDomain handle(String line) throws UnsupportedFormatException {
		String[] parts = line.split(";");
		if(parts.length != 3) {
			throw new UnsupportedFormatException();
		}
		Celebrity celebrity = new Celebrity();
		
		String firstName = parts[FIRST_NAME_INDEX].trim();
		celebrity.setFirstName(firstName);
		
		String lasttName = parts[LAST_NAME_INDEX].trim();
		celebrity.setLastName(lasttName);
		
		String phonenr = parts[PHONENR_INDEX].trim();
		celebrity.setPhoneNumber(phonenr);
		
		return celebrity;
	}

}
