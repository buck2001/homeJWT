package com.pn.home.model;

/**
 * @author pnaylor4 will be in the json form of {
 *         "tokenEncryptionString":"somegreatsecretfromthedarkerstreachesofmybrain" }
 */
public class AWSTokenSecret {
	private String jwtTokenEncryptionString;

	public AWSTokenSecret(String secret) {
		this.jwtTokenEncryptionString = secret;
	}

	public String getJwtTokenEncryptionString() {
		return jwtTokenEncryptionString;
	}

	public void setJwtTokenEncryptionString(String jwtTokenEncryptionString) {
		this.jwtTokenEncryptionString = jwtTokenEncryptionString;
	}
}
