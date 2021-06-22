package com.pn.home.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pn.home.common.AppException;
import com.pn.home.constants.DatabaseConstants;

/**
 * Datasource configuration to override application.properties and use data from
 * AWS secrets manager instead
 * 
 * Not necessarily required as Spring Boot SPI loads correct driver anyway!
 * 
 * @author pnaylor4
 **/
@Configuration
public class DatasourceConfig {
	private static final String CLASS_NAME = DatasourceConfig.class.getName();

	@Bean
	public DataSource getDataSource() throws AppException, JsonProcessingException {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//		AWSDBSecret databaseConfig = new AWSDBSecret();
//		ObjectMapper mapper = new ObjectMapper();
//		Logger LOGGER = LoggerFactory.getLogger(AppLogger.class);
//		String currentEnv = System.getenv(AppConstants.ENV_IDENTIFIER);

		dataSourceBuilder.driverClassName(DatabaseConstants.SQL_SERVER_DRIVER);
		dataSourceBuilder.url(DatabaseConstants.DATABASE_URL);
		dataSourceBuilder.username(DatabaseConstants.DB_USER);
		dataSourceBuilder.password(DatabaseConstants.DB_PASS);

		return dataSourceBuilder.build();
	}
}
