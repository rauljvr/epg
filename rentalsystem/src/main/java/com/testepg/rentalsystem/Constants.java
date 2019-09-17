package com.testepg.rentalsystem;

/**
 * Constants values used in several parts of the project
 */
public final class Constants {

	private Constants() {
		throw new UnsupportedOperationException("This is an inmutable class");
	}
	
	public static final String RENTAL = "rental";
	public static final String RETURN = "return";
	public static final String GAME = "game";
	public static final String USER = "user";
	
	public static final Long NEW_RELEASE = new Long(1);
	public static final int NEW_RELEASE_POINTS = 2;
	public static final int OTHER_TYPES_POINTS = 1;
	
	public static final String DATE_PATTERN = "yyyy-MM-dd";
}
