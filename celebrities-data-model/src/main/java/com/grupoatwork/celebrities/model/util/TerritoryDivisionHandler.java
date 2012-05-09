package com.grupoatwork.celebrities.model.util;

import com.grupoatwork.celebrities.model.TerritoryDivision;

public interface TerritoryDivisionHandler {
	public class UnsupportedFormatException extends Exception {
		private static final long serialVersionUID = 1L;
	}
	
	public TerritoryDivision handle(String line) throws UnsupportedFormatException;
}
