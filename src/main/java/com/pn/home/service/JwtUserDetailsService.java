package com.pn.home.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pn.home.*;
import com.pn.home.common.AppException;
import com.pn.home.common.AppExceptionHandler;
import com.pn.home.common.AppLogger;
import com.pn.home.constants.AppConstants;
import com.pn.home.constants.ErrorConstants;
import com.pn.home.model.UserDTO;
import com.pn.home.model.UserTransfer;
import com.pn.home.repository.UserDao;
import com.pn.home.repository.model.DAOUser;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private static String CLASS_NAME = JwtUserDetailsService.class.getName();

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DAOUser user = userDao.findByUsername(username);
		AppLogger.log(CLASS_NAME, "findByUsername", AppConstants.NO_ID, username, AppLogger.ENTRY);

		if (user == null) {
			AppExceptionHandler.handleException(CLASS_NAME, ErrorConstants.AUT0001, ErrorConstants.AUT0001_MESSAGE,
					new UsernameNotFoundException(ErrorConstants.AUT0002_MESSAGE + username));
		} else if (user.getActivated().equals(AppConstants.STRING_BOOLEAN_FALSE)) {
			throw new UsernameNotFoundException(ErrorConstants.USR0004_MESSAGE + username);
		}

		// OK, so now get the user roles and turn into authorities
		Set<SimpleGrantedAuthority> authorities = getAuthorities(user);

		AppLogger.log(CLASS_NAME, "findByUsername", AppConstants.NO_ID, username, AppLogger.EXIT);
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	public List<DAOUser> getUser(String username) throws AppException {
		List<DAOUser> users = new ArrayList<DAOUser>();
		DAOUser user = userDao.findByUsername(username);

		if (user == null) {
			throw AppExceptionHandler.handleException(CLASS_NAME, ErrorConstants.AUT0001,
					ErrorConstants.AUT0001_MESSAGE,
					new UsernameNotFoundException(ErrorConstants.AUT0002_MESSAGE + username));
		}

		users.add(user);

		return (users);
	}

	public List<DAOUser> getUsers() throws AppException {
		List<DAOUser> users = new ArrayList<DAOUser>();
		users = userDao.getUsers();

		return (users);
	}

	public UserDetails getUserDetailsObject(String userDetailsObject)
			throws UsernameNotFoundException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		UserTransfer user = objectMapper.readValue(userDetailsObject, UserTransfer.class);

		if (user == null) {
			AppExceptionHandler.handleException(CLASS_NAME, ErrorConstants.AUT0001, ErrorConstants.AUT0001_MESSAGE,
					new UsernameNotFoundException(ErrorConstants.AUT0002_MESSAGE + userDetailsObject));
		}

		// OK, so now get the user roles and turn into authorities
		Set<SimpleGrantedAuthority> authorities = getAuthorities2(user);

		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	public List<DAOUser> save(UserDTO user) throws AppException {
		DAOUser newUser = new DAOUser();
		List<DAOUser> savedUser = new ArrayList<DAOUser>();

		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRoles(user.getRoles());
		newUser.setActivated(AppConstants.STRING_BOOLEAN_FALSE);
		newUser.setParty(user.getParty());
		newUser.setOrg(user.getOrg());
		savedUser.add(userDao.save(newUser));

		return savedUser;
	}

	public List<DAOUser> findActivatedUsers() {
		return userDao.findActivatedUsers();
	}

	private Set<SimpleGrantedAuthority> getAuthorities(DAOUser user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		List<String> roles = Arrays.asList(user.getRoles().split(AppConstants.COMMA_DELIMITER));

		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(AppConstants.ROLE_PREFIX + role));
		});

		return authorities;
	}

	public List<DAOUser> updateUser(UserDTO user) throws AppException {
		DAOUser existingUser = new DAOUser();
		List<DAOUser> activatedUser = new ArrayList<DAOUser>();
		existingUser = userDao.findByUsername(user.getUsername());

		// TODO if found
		existingUser.setUsername(user.getUsername());
		existingUser.setParty(user.getParty());
		existingUser.setRoles(user.getRoles());
		final DAOUser updatedUser = userDao.save(existingUser);
		activatedUser.add(updatedUser);

		return activatedUser;
	}

	public List<DAOUser> updatePassword(UserDTO user) throws AppException {
		DAOUser existingUser = new DAOUser();
		List<DAOUser> activatedUser = new ArrayList<DAOUser>();
		existingUser = userDao.findByUsername(user.getUsername());

		// TODO if found
		existingUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		final DAOUser updatedUser = userDao.save(existingUser);
		activatedUser.add(updatedUser);

		return activatedUser;
	}

	public List<DAOUser> activateUser(String userName) throws AppException {
		DAOUser existingUser = new DAOUser();
		List<DAOUser> activatedUser = new ArrayList<DAOUser>();
		existingUser = userDao.findByUsername(userName);

		// TODO if found
		existingUser.setActivated(AppConstants.STRING_BOOLEAN_TRUE);
		final DAOUser updatedUser = userDao.save(existingUser);
		activatedUser.add(updatedUser);

		return activatedUser;
	}

	public List<DAOUser> deactivateUser(String userName) throws AppException {
		DAOUser existingUser = new DAOUser();
		List<DAOUser> activatedUser = new ArrayList<DAOUser>();
		existingUser = userDao.findByUsername(userName);

		// TODO if found
		existingUser.setActivated(AppConstants.STRING_BOOLEAN_FALSE);
		final DAOUser updatedUser = userDao.save(existingUser);
		activatedUser.add(updatedUser);

		return activatedUser;
	}

	public List<DAOUser> deleteUser(String username) throws AppException {
		DAOUser existingUser = new DAOUser();
		List<DAOUser> activatedUser = new ArrayList<DAOUser>();
		existingUser = userDao.findByUsername(username);

		userDao.delete(existingUser);
		existingUser.setActivated(AppConstants.STRING_BOOLEAN_FALSE);
		activatedUser.add(existingUser);

		return activatedUser;
	}

	// TODO
	private Set<SimpleGrantedAuthority> getAuthorities2(UserTransfer user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		user.getAuthorities().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		});

		return authorities;
	}
}