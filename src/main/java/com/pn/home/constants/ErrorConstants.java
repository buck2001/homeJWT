package com.pn.home.constants;

/**
 * All error constants live here Error codes and Error messages
 * 
 * @author pnaylor4
 **/
public class ErrorConstants {
	// error codes for authentication and authorization
	public static final String AUT0001 = "AUT0001";
	public static final String AUT0002 = "AUT0002";
	public static final String AUT0003 = "AUT0003";

	// error codes for user actions
	public static final String USR0001 = "USR0001";
	public static final String USR0002 = "USR0002";
	public static final String USR0003 = "USR0003";
	public static final String USR0004 = "USR0004";
	public static final String USR0005 = "USR0005";

	// Error codes for user template actions
	public static final String TEMP0001 = "TEMP0001";
	public static final String TEMP0002 = "TEMP0002";
	public static final String TEMP0003 = "TEMP0003";
	public static final String TEMP0004 = "TEMP0004";
	public static final String TEMP0005 = "TEMP0005";
	public static final String TEMP0006 = "TEMP0006";
	public static final String TEMP0007 = "TEMP0007";
	public static final String TEMP0008 = "TEMP0008";

	// Error codes for preferences
	public static final String PRE0001 = "PRE0001";
	public static final String PRE0002 = "PRE0002";
	public static final String PRE0003 = "PRE0003";
	public static final String PRE0004 = "PRE0004";

	// error message for utils
	public static final String UTL0001 = "UTL0001";

	// error codes for AWS secrets manager
	public static final String SCR0001 = "SCR0001";

	// error messages
	public static final String SCR0001_MESSAGE = "Unable to access AWS secrets manager";
	public static final String AUT0001_MESSAGE = "Unable to login!";
	public static final String AUT0002_MESSAGE = "User not found with username: ";
	public static final String AUT0003_MESSAGE = "Unable to retrieve user roles ";
	public static final String USR0001_MESSAGE = "Error saving user";
	public static final String USR0002_MESSAGE = "Unable to update user";
	public static final String USR0003_MESSAGE = "Unable to delete user";
	public static final String USR0004_MESSAGE = "User not activated";
	public static final String USR0005_MESSAGE = "Unable to find user";
	public static final String UTL0001_MESSAGE = "Unable to generate GUUID";

	// preferences error message
	public static final String PRE0001_MESSAGE = "Unable to get preference";
	public static final String PRE0002_MESSAGE = "Unable to add preference";
	public static final String PRE0003_MESSAGE = "Unable to update preference";
	public static final String PRE0004_MESSAGE = "Unable to delete preference";

	// Template error messages
	public static final String TEMP0001_MESSAGE = "Unable to create template for the user";
	public static final String TEMP0002_MESSAGE = "Unable to update template for the user";
	public static final String TEMP0003_MESSAGE = "Unable to delete template for the user";
	public static final String TEMP0004_MESSAGE = "Template is a duplicate";
	public static final String TEMP0005_MESSAGE = "Template does not exist";
	public static final String TEMP0006_MESSAGE = "Unable to get user template";
	public static final String TEMP0007_MESSAGE = "Unable to get templates associated with user";
	public static final String TEMP0008_MESSAGE = "There are no templates associated with the user";
}
