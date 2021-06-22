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
}
