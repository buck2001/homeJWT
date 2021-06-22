package com.pn.home.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pn.home.constants.AppConstants;
import com.pn.home.model.Authority;
import com.pn.home.model.UserTransfer;
import com.pn.home.util.JwtTokenUtil;

@Service
public class AuthService {
	public String isAuthorised(@RequestHeader Map<String, String> headers, String feIntId)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		boolean result = false;

		String token = headers.get(AppConstants.AUTH_HEADER.toLowerCase()).substring(6);
		System.out.println(token);

		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		UserTransfer usern = objectMapper.readValue(jwtTokenUtil.getUserDetailsObjectFromToken(token),
				UserTransfer.class);

		String whatToCheck = AppConstants.AUTH_MAP.get(feIntId);
		// TODO specify EXACT match on ROLE_ADMIN
		result = whatToCheck != null ? usern.getAuthorities().stream()
				.anyMatch(obj -> obj.getAuthority().toUpperCase().contains(whatToCheck.toUpperCase())) : false;

		return (result == true ? AppConstants.STRING_BOOLEAN_TRUE : AppConstants.STRING_BOOLEAN_FALSE);
	}

	public List<String> getUserRoles(@RequestHeader Map<String, String> headers)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String token = headers.get(AppConstants.AUTH_HEADER.toLowerCase()).substring(6);
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		UserTransfer usern = objectMapper.readValue(jwtTokenUtil.getUserDetailsObjectFromToken(token),
				UserTransfer.class);

		return getRolesList(AppConstants.AUTH_MAP, usern.getAuthorities());
	}

	private static <T, E> List<String> getRolesList(Map<T, E> map, List<Authority> roles) {
		List<String> rolesList = new ArrayList<>();
		roles.stream().forEach(x -> {
			for (Entry<T, E> entry : map.entrySet()) {
				if (entry.getValue().toString().equals(x.getAuthority().substring(5))) {
					rolesList.add(entry.getKey().toString());
				}
			}
		});

		return rolesList;
	}
}