package com.pn.home.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Application wide constants live here Except for database constants and error
 * constants
 * 
 * @author pnaylor4
 **/
public class AppConstants {
	// APIs
	public final static String LOGIN_API = "/login";
	public final static String USER_API = "/user";
	public final static String AUTH_API = "/auth";
	public final static String AUTH_ROLES_API = "/roles";
	public final static String PREFERENCES_GET = "/{organisationId}";

	// headers
	public final static String AUTH_HEADER = "Authorization";

	// app stuff
	public final static String HEALTH_CHECK = "I'm Alive!";
	public final static String HEALTH_CHECK_URL = "/health";
	public final static String ROLE_CHECK = "/rolecheck";
	public final static String DEFAULT_TOKEN_STRING = "XM=SgSXh9K0mk3?";
	public final static String ENV_IDENTIFIER = "env";
	public final static String DEV_ENV_IDENTIFIER = "dev";
	public final static String API_USER = "/user";

	// boolean vals
	public final static boolean BOOLEAN_TRUE = true;
	public final static boolean BOOLEAN_FALSE = false;

	public final static String STRING_BOOLEAN_TRUE = "true";
	public final static String STRING_BOOLEAN_FALSE = "false";

	// URL literals
	public final static String URL_AUTHENTICATE = "/authenticate";
	public final static String URL_REGISTER = "/register";
	public final static String URL_GET_USER = "/{user}";
	public final static String URL_GET_PARTY = "/{user}/party";
	public final static String URL_ACTIVATE = "/activate/{userName}";
	public final static String URL_DEACTIVATE = "/deactivate/{userName}";
	public final static String URL_FIND_REGISTER = "/find";
	public final static String URL_UPDATE_PASSWORD = "/updatepassword";

	// string literals
	public final static String UNAUTHORISED = "Unauthorized";
	public final static String AUTHORISED = "Authorized";

	// security literals
	public final static String BEARER = "Bearer ";
	public final static String XDMP_CORRELATIONID = "X-DMP-CorrelationID";
	public final static String AUTHORISATION_HEADER = "Authorization";
	public final static String ILLEGAL_TOKEN = "Unable to get JWT Token";
	public final static String EXPIRE_TOKEN = "JWT Token has expired";
	public final static String INCORRECT_AUTH = "JWT Token does not begin with Bearer String";
	public final static String ROLE_PREFIX = "ROLE_";
	public final static String MODAL_REF = "string";
	public final static String HEADER = "header";

	// roles authorisation
	public final static String ROLE_ADMIN = "hasRole('ADMIN')";
	public final static String ROLE_BROKER = "hasRole('BROKER')";
	public final static String ROLE_CARRIER = "hasRole('CARRIER')";
	public final static String IS_AUTHENTICATED = "isAuthenticated()";
	public final static String ALL_ROLES = "hasAnyRole('ADMIN','BROKER','CARRIER','TECHNICIAN')";

	// messages
	public final static String ROLE_CHECK_MESSAGE = "BOOYAA-MF!!!";
	public final static String NO_ID = "NO ID";

	// text constants
	public final static String COMMA_DELIMITER = ",";
	public final static String COLON = ":";
	public final static String SPACE = " ";
	public final static String EMPTY_STRING = "";

	public final static String NEW_LINE = "\n";
	public final static String TAB = "\t";
	public final static String QUOTATION_MARK = "\"";
	public final static String QUESTION_MARK = "?";
	public final static String PERCENTAGE_SIGN = "%";
	public final static String OPEN_BRACKET = "(";
	public final static String CLOSE_BRACKET = ")";
	public final static String SLASH = "/";
	public final static String equals = "=";
	public final static String SQUARE_BRACKET = "[]";
	public final static String CURLY_BRACKET = "{}";
	public final static String EMPTY_SQUARE_BRACKET = "[ ]";
	public final static String NULL = "null";
	public final static String AMPERSAND = "&";

	// logger stuff
	public final static String LOGGER_NO_OBJECT = "No object";
	public final static String LOGGER_NO_ID = "No id";

	// env
	public final static String DEV_ENV = "dev";
	public final static String TST_ENV = "tst";
	public final static String PRD_ENV = "prd";

	// auth stuff
	public final static Map<String, String> AUTH_MAP = createMap();

	private static Map<String, String> createMap() {
		Map<String, String> myMap = new HashMap<String, String>();
		myMap.put("01", "QA"); // for all QA actions and related buttons etc.
		myMap.put("02", "ADMIN"); // for administrators
		myMap.put("03", "TECHNICIAN"); // for techs
		myMap.put("04", "BROKER"); // for brokers
		myMap.put("05", "CARRIER"); // for carriers
		myMap.put("06", "OTHER"); // for others?

		return myMap;
	}

	public final static String DATE_AND_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
	public final static String DATE_FORMAT = "dd/MM/yyyy";
	public final static String SQL_DATE_FORMAT = "yyyy-MM-dd";
	public final static String SQL_DATE_FORMAT_ALT = "dd-MM-yyyy";
	public final static String SQL_DATE__TIME_FORMAT_ALT = "dd-MM-yyyy HH:mm:ss";

	// AWS stuff
	public final static String URL_SECRET_REGION = "eu-west-2";
	public final static String AWS_JWT_TOKEN_ENCRYPTION_IDENTIFIER = "jwtTokenEncryption";
	public final static String AWS_DATABASE_IDENTIFIER_SECRET = "mfuser-master-"; // "mf-sql-database"; // TODO
	public final static String AWS_DEV_DATABASE_IDENTIFIER = "fnol"; // "mf-sql-database"; // TODO

}
