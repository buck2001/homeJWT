package com.pn.home.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pn.home.constants.DatabaseConstants;

@Entity
@Table(name = DatabaseConstants.USER_TABLE)
public class DAOUser {
	@Id
	@SequenceGenerator(name = "CLIENT_SEQUENCE", sequenceName = "CLIENT_SEQUENCE_ID", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String username;

	@Column
	private String roles;

	@Column
	private String party;

	@Column
	private String activated;

	@Column
	private String org;

	@Column
	@JsonIgnore
	private String password;
	
	@Column
	private String templates;

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getActivated() {
		return activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
	
	public String getTemplates() {
		return templates;
	}
	
	public void setTemplates(String templates) {
		this.templates = templates;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}