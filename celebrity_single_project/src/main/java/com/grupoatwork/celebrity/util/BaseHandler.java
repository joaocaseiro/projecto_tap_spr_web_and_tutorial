package com.grupoatwork.celebrity.util;

import com.grupoatwork.celebrity.entities.BaseDomain;


public interface BaseHandler {
	public class UnsupportedFormatException extends Exception {
		private static final long serialVersionUID = 1L;
	}
	
	public BaseDomain handle(String line) throws UnsupportedFormatException;
}