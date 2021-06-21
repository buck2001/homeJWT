package com.pn.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pn.home.constants.DatabaseConstants;
import com.pn.home.repository.model.DAOUser;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
	@Query(value = DatabaseConstants.USERS_GET, nativeQuery = true)
	DAOUser findByUsername(String username);

	// queries
	@Query(value = DatabaseConstants.USERS_ACTIVATED, nativeQuery = true)
	List<DAOUser> findActivatedUsers();

	// example query
	@Query(value = DatabaseConstants.USERS_ALL, nativeQuery = true)
	List<DAOUser> getUsers();
}