package com.pn.home.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

/**
 * builds required headers for all responses
 * 
 * @author pnaylor4
 **/
public class HeadersBuilder {

	public static HttpHeaders buildHeaders(String guuid, String IDToken) {
		List<String> exposedHeaders = getExposedHeaders();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=UTF-8");
		headers.add("Accept-Charset", "charset=UTF-8");
		headers.add("Accept-Encoding", "*");
		headers.add("Accept-Language", "en-UK,en-US");
		headers.add("Accept", "application/json");
		headers.add("X-DMP-CorrelationID", guuid);
		headers.add("X-DMP-IDToken", IDToken);
		headers.setAccessControlExposeHeaders(exposedHeaders);

		return headers;
	}

	public static HttpHeaders buildHeaders(Map<String, String> allHeaders) {
		HttpHeaders headers = new HttpHeaders();

		allHeaders.forEach((key, value) -> {
			headers.add(key, value);
		});

		return headers;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List<String> getExposedHeaders() {
		List exposedHeaders = new ArrayList<String>();

		exposedHeaders.add("Access-Control-Allow-Headers");
		exposedHeaders.add("X-DMP-CorrelationID");

		return exposedHeaders;
	}
}
