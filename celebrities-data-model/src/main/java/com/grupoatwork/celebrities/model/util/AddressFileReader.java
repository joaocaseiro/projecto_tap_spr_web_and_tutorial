package com.grupoatwork.celebrities.model.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.grupoatwork.celebrities.model.TerritoryDivision;
import com.grupoatwork.celebrities.model.util.TerritoryDivisionHandler.UnsupportedFormatException;

public class AddressFileReader {
	public static List<TerritoryDivision> districtReader(InputStream stream,
			TerritoryDivisionHandler handler) {
		List<TerritoryDivision> divisions = new ArrayList<TerritoryDivision>();
		try {
			InputStreamReader in = new InputStreamReader(stream, "UTF-8");
			BufferedReader bufIn = new BufferedReader(in);

			String line;
			while ((line = bufIn.readLine()) != null) {
				TerritoryDivision division = handler.handle(line);
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
