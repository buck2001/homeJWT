package com.pn.home.constants;

import org.springframework.context.annotation.Configuration;

/**
 * Database constants such as JDBC connection information or stored procedures
 * etc.
 * 
 * @author peter
 **/
@Configuration
public class DatabaseConstants {
	// ? database access
	public final static String USER_TABLE = "mfclms_user_tbl";

	// user data SQL queries
	public final static String USERS_GET = "SELECT * FROM " + USER_TABLE + " WHERE username = ?";
	public final static String USERS_ACTIVATED = "SELECT * FROM " + USER_TABLE + " WHERE activated = 'true'";
	public final static String USERS_ACTIVATE_USER = "UPDATE " + USER_TABLE
			+ " SET activated = 'true' WHERE username = ?";
	public final static String USERS_ALL = "SELECT * FROM " + USER_TABLE;

	// datasource config
	public final static String DATABASE_URL = "jdbc:sqlserver://localhost:1433;databaseName=mfuser-master-dev001";
	public final static String SQL_SERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public final static String DATABASE_NAME = "databaseName=mfuser-master-dev001";
	public final static String DB_USER = "sa";
	public final static String DB_PASS = "Sausages_99";
}
