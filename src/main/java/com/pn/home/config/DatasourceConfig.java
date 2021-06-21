package com.pn.home.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pn.home.common.AppException;
import com.pn.home.constants.AppConstants;
import com.pn.home.constants.ErrorConstants;
import com.pn.home.util.CommonUtilities;

/**
 * Datasource configuration to override application.properties and use data from
 * AWS secrets manager instead
 * 
 * Not necessarily required as Spring Boot SPI loads correct driver anyway!
 * 
 * @author pnaylor4
 **/
//@Configuration
public class DatasourceConfig {
	private static final String CLASS_NAME = DatasourceConfig.class.getName();

//	@Bean
//	public DataSource getDataSource() throws AppException, JsonProcessingException {
//		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//		AWSDBSecret databaseConfig = new AWSDBSecret();
//		ObjectMapper mapper = new ObjectMapper();
//		Logger LOGGER = LoggerFactory.getLogger(AppLogger.class);
//		String currentEnv = System.getenv(AppConstants.ENV_IDENTIFIER);
//
//		try {
//			LOGGER.info("\n\n LOOKING FOR AWS secret now - {}", AppConstants.AWS_DATABASE_IDENTIFIER_SECRET);
//			String getSecretValueResult = AWSSecretsManager
//					.getAWSSecret(CommonUtilities.getDBSecret(AppConstants.AWS_DATABASE_IDENTIFIER_SECRET));
//
//			if (getSecretValueResult != null) {
//				LOGGER.info("\nGOT SECRET FROM SECRETS MANAGER\n");
//				databaseConfig = mapper.readValue(getSecretValueResult, AWSDBSecret.class);
//
//				String fullHostURL = DatabaseConstants.SQL_JDBC_DB_PREFIX + databaseConfig.getHost()
//						+ AppConstants.COLON + databaseConfig.getPort() + DatabaseConstants.SQL_DATABASE_PREFIX;
//
//				fullHostURL = fullHostURL + ((currentEnv != null && currentEnv.equals(AppConstants.DEV_ENV_IDENTIFIER))
//						? "mfuser-master-dev001"
//						: databaseConfig.getDbInstanceIdentifier());
//
//				databaseConfig.setHost(fullHostURL);
//				LOGGER.info("\nFULL HOST URL and DB Config are {}\n{}", fullHostURL,
//						mapper.writeValueAsString(databaseConfig));
//			} else {
//				throw AppExceptionHandler.handleException(CLASS_NAME, ErrorConstants.SCR0001_MESSAGE,
//						new Exception(ErrorConstants.SCR0001));
//			}
//		} catch (Exception e) { // try a default prod setup
//			LOGGER.info("\nERRORED {} \n", e);
//			databaseConfig = DatabaseConstants.getDefaultDBConfig();
//			databaseConfig.setUsername(AppConstants.SQL_SERVER_DEFAULT_USERNAME_PROD);
//			databaseConfig.setPassword(AppConstants.SQL_SERVER_DEFAULT_PASSWORD_PROD);
//			LOGGER.info("\nUSING DEFAULT SECRETS DATA\n{}", mapper.writeValueAsString(databaseConfig));
//		}
//
//		dataSourceBuilder.driverClassName(DatabaseConstants.SQL_SERVER_DRIVER);
//		dataSourceBuilder.url(databaseConfig.getHost());
//		dataSourceBuilder.username(databaseConfig.getUsername());
//		dataSourceBuilder.password(databaseConfig.getPassword());
//
//		return dataSourceBuilder.build();
//	}
}
