package com.grupoatwork.celebrity.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.grupoatwork.celebrity.entities.BaseDomain;
import com.grupoatwork.celebrity.util.BaseHandler.UnsupportedFormatException;

public class AddressFileReader {
	public static List<BaseDomain> reader(InputStream stream, BaseHandler handler) {
		List<BaseDomain> divisions = new ArrayList<BaseDomain>();
		try {
			InputStreamReader in = new InputStreamReader(stream, "UTF-8");
			BufferedReader bufIn = new BufferedReader(in);

			String line;
			while ((line = bufIn.readLine()) != null) {
				BaseDomain division = handler.handle(line);
				divisions.add(division);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedFormatException e) {
			e.printStackTrace();
		}

		return divisions;
	}
}
