package com.pn.home.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pn.home.builder.ResponseBuilder;
import com.pn.home.common.*;
import com.pn.home.constants.AppConstants;
import com.pn.home.constants.ErrorConstants;
import com.pn.home.model.JwtRequest;
import com.pn.home.service.JwtUserDetailsService;
import com.pn.home.util.AppUtils;
import com.pn.home.util.HeadersBuilder;
import com.pn.home.util.JwtTokenUtil;

@RestController
@CrossOrigin
@RequestMapping(AppConstants.LOGIN_API)
public class LoginController {
	private static String CLASS_NAME = LoginController.class.getName();

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestHeader Map<String, String> headers,
			@RequestBody JwtRequest authenticationRequest) throws Exception {
		List<String> token = new ArrayList<String>();
		AppLogger.log(CLASS_NAME, "login", AppConstants.NO_ID, authenticationRequest.getUsername(), AppLogger.ENTRY);

		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			token.add(jwtTokenUtil.generateToken(userDetails));
		} catch (Exception e) {
			return ResponseEntity.ok(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
					ErrorConstants.AUT0001, ErrorConstants.AUT0001_MESSAGE, e)));
		}

		String userGuuid = AppUtils.generateGUUID();
		AppLogger.log(CLASS_NAME, "login", userGuuid, authenticationRequest.getUsername(), AppLogger.EXIT);
		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(userGuuid, token.get(0)))
				.body(ResponseBuilder.buildSuccessResponse(token));
	}

	private void authenticate(String username, String password) throws AppException {
		AppLogger.log(CLASS_NAME, "authenticate", AppConstants.NO_ID, username, AppLogger.ENTRY);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			AppLogger.log(CLASS_NAME, "authenticate", AppConstants.NO_ID, username, AppLogger.EXIT);
		} catch (Exception e) {
			throw AppExceptionHandler.handleException(CLASS_NAME, ErrorConstants.AUT0001,
					ErrorConstants.AUT0001_MESSAGE, new Exception(ErrorConstants.AUT0001_MESSAGE));
		}
	}
}