package com.pn.home.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pn.home.builder.ResponseBuilder;
import com.pn.home.common.AppExceptionHandler;
import com.pn.home.constants.AppConstants;
import com.pn.home.constants.ErrorConstants;
import com.pn.home.service.AuthService;
import com.pn.home.util.HeadersBuilder;

@RestController
@CrossOrigin
@RequestMapping(AppConstants.AUTH_API)
public class AuthController {
	private static String CLASS_NAME = AuthController.class.getName();

	@Autowired
	private AuthService authService;

	/**
	 * will register a user but user will NOT be activated; activation needs
	 * carrying out later. No authentication or authorisation on this API
	 * 
	 * @param user UserDTO object
	 * @return savedUser DAOUser the FE representation of the saved user
	 **/
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> isAuthorised(@RequestHeader Map<String, String> headers, @RequestParam String feObject) {
		List<String> isAuthorised = new ArrayList<String>();

		try {
			isAuthorised.add(authService.isAuthorised(headers, feObject));
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(headers))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.USR0001, ErrorConstants.USR0001_MESSAGE, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(headers))
				.body(ResponseBuilder.buildSuccessResponse(isAuthorised));
	}

	@RequestMapping(method = RequestMethod.GET, value = AppConstants.AUTH_ROLES_API)
	public ResponseEntity<?> getUserRoles(@RequestHeader Map<String, String> headers) {
		List<String> roles = new ArrayList<String>();

		try {
			roles = authService.getUserRoles(headers);
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(headers))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.AUT0003, ErrorConstants.AUT0003_MESSAGE, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders("SomeGUUID", "SomeIDToken"))
				.body(ResponseBuilder.buildSuccessResponse(roles));
	}
}