package com.pn.home.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pn.home.builder.ResponseBuilder;
import com.pn.home.common.AppExceptionHandler;
import com.pn.home.constants.AppConstants;
import com.pn.home.constants.ErrorConstants;
import com.pn.home.model.UserDTO;
import com.pn.home.repository.model.DAOUser;
import com.pn.home.service.JwtUserDetailsService;
import com.pn.home.util.CommonUtilities;
import com.pn.home.util.HeadersBuilder;

@RestController
@CrossOrigin
@RequestMapping(AppConstants.USER_API)
public class UserController {
	private static String CLASS_NAME = UserController.class.getName();

	@Autowired
	private JwtUserDetailsService userDetailsService;
	// public List<Integer> b = IntStream.rangeClosed(1, 100).mapToObj(i -> new
	// Integer(i)).collect(Collectors.toList());

	/**
	 * will register a user but user will NOT be activated; activation needs
	 * carrying out later. No authentication or authorisation on this API
	 * 
	 * @param user UserDTO object
	 * @return savedUser DAOUser the FE representation of the saved user
	 **/
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize(AppConstants.ROLE_ADMIN)
	public ResponseEntity<?> saveUser(@RequestHeader Map<String, String> headers, @RequestBody UserDTO user) {
		String correlationId = CommonUtilities.getHeaderValueFor(headers, AppConstants.XDMP_CORRELATIONID);

		List<DAOUser> savedUser = new ArrayList<DAOUser>();

		try {
			savedUser = userDetailsService.save(user);
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.USR0001, ErrorConstants.USR0001_MESSAGE, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders("SomeGUUID", "SomeIDToken"))
				.body(ResponseBuilder.buildSuccessResponse(savedUser));
	}

	@RequestMapping(value = AppConstants.URL_GET_USER, method = RequestMethod.GET)
	@PreAuthorize(AppConstants.ALL_ROLES)
	public ResponseEntity<?> getUser(@RequestHeader Map<String, String> headers, @PathVariable String user) {
		String correlationId = CommonUtilities.getHeaderValueFor(headers, AppConstants.XDMP_CORRELATIONID);
		List<DAOUser> users = new ArrayList<DAOUser>();

		try {
			users = userDetailsService.getUser(user);
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.USR0004_MESSAGE, ErrorConstants.USR0004, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
				.body(ResponseBuilder.buildSuccessResponse(users));
	}

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize(AppConstants.ROLE_ADMIN)
	public ResponseEntity<?> getUsers(@RequestHeader Map<String, String> headers) {
		String correlationId = CommonUtilities.getHeaderValueFor(headers, AppConstants.XDMP_CORRELATIONID);
		List<DAOUser> users = new ArrayList<DAOUser>();

		try {
			users = userDetailsService.getUsers();
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.USR0004_MESSAGE, ErrorConstants.USR0004, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
				.body(ResponseBuilder.buildSuccessResponse(users));
	}

	@RequestMapping(value = AppConstants.URL_ACTIVATE, method = RequestMethod.PATCH)
	@PreAuthorize(AppConstants.ROLE_ADMIN)
	public ResponseEntity<?> activateUser(@RequestHeader Map<String, String> headers, @PathVariable String userName) {
		String correlationId = CommonUtilities.getHeaderValueFor(headers, AppConstants.XDMP_CORRELATIONID);
		List<DAOUser> activatedUsers = new ArrayList<DAOUser>();

		try {
			activatedUsers = userDetailsService.activateUser(userName);
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.USR0004, ErrorConstants.USR0004_MESSAGE, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
				.body(ResponseBuilder.buildSuccessResponse(activatedUsers));
	}

	@RequestMapping(value = AppConstants.URL_DEACTIVATE, method = RequestMethod.PATCH)
	@PreAuthorize(AppConstants.ROLE_ADMIN)
	public ResponseEntity<?> deactivateUser(@RequestHeader Map<String, String> headers, @PathVariable String userName) {
		String correlationId = CommonUtilities.getHeaderValueFor(headers, AppConstants.XDMP_CORRELATIONID);
		List<DAOUser> activatedUser = new ArrayList<DAOUser>();

		try {
			activatedUser = userDetailsService.deactivateUser(userName);
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.USR0001, ErrorConstants.USR0001_MESSAGE, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
				.body(ResponseBuilder.buildSuccessResponse(activatedUser));
	}

	@RequestMapping(value = AppConstants.URL_UPDATE_PASSWORD, method = RequestMethod.PATCH)
	@PreAuthorize(AppConstants.IS_AUTHENTICATED)
	public ResponseEntity<?> updatePassword(@RequestHeader Map<String, String> headers, @RequestBody UserDTO user) {
		String correlationId = CommonUtilities.getHeaderValueFor(headers, AppConstants.XDMP_CORRELATIONID);
		List<DAOUser> updatedUser = new ArrayList<DAOUser>();

		try {
			updatedUser = userDetailsService.updatePassword(user);
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.USR0001, ErrorConstants.USR0001_MESSAGE, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
				.body(ResponseBuilder.buildSuccessResponse(updatedUser));
	}

	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize(AppConstants.ROLE_ADMIN)
	public ResponseEntity<?> updateUser(@RequestHeader Map<String, String> headers, @RequestBody UserDTO user) {
		String correlationId = CommonUtilities.getHeaderValueFor(headers, AppConstants.XDMP_CORRELATIONID);
		List<DAOUser> activatedUser = new ArrayList<DAOUser>();

		try {
			activatedUser = userDetailsService.updateUser(user);
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.USR0001, ErrorConstants.USR0001_MESSAGE, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
				.body(ResponseBuilder.buildSuccessResponse(activatedUser));
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize(AppConstants.ROLE_ADMIN)
	public ResponseEntity<?> deleteUser(@RequestHeader Map<String, String> headers, @RequestParam String username) {
		String correlationId = CommonUtilities.getHeaderValueFor(headers, AppConstants.XDMP_CORRELATIONID);
		List<DAOUser> activatedUser = new ArrayList<DAOUser>();

		try {
			activatedUser = userDetailsService.deleteUser(username);
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.USR0001, ErrorConstants.USR0001_MESSAGE, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
				.body(ResponseBuilder.buildSuccessResponse(activatedUser));
	}

	@RequestMapping(value = AppConstants.URL_GET_PARTY, method = RequestMethod.GET)
	public ResponseEntity<?> getParty(@RequestHeader Map<String, String> headers, @PathVariable String user) {
		String correlationId = CommonUtilities.getHeaderValueFor(headers, AppConstants.XDMP_CORRELATIONID);
		String party;

		try {
			party = userDetailsService.getUser(user).stream().findFirst().get().getParty();
		} catch (Exception e) {
			return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
					.body(ResponseBuilder.buildErrorResponse(AppExceptionHandler.handleException(CLASS_NAME,
							ErrorConstants.USR0004_MESSAGE, ErrorConstants.USR0004, e)));
		}

		return ResponseEntity.ok().headers(HeadersBuilder.buildHeaders(correlationId, "SomeIDToken"))
				.body(ResponseBuilder.buildSuccessResponse(new ArrayList<>(Arrays.asList(party))));
	}
}