package com.pn.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Value("${jwt.secret}")
	private String tokenEncryptionString;

	public String getTokenEncryptionString() {
		return tokenEncryptionString;
	}
}
