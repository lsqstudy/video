package com.lsqstudy.system.util;

import com.lsqstudy.system.domain.Administrator;

import java.util.List;

public class ActivierAdministrator {

	private Administrator administrator;
	private List<String> roles;
	private List<String> permissions;

	public ActivierAdministrator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivierAdministrator(Administrator administrator, List<String> roles, List<String> permissions) {
		super();
		this.administrator = administrator;
		this.roles = roles;
		this.permissions = permissions;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "ActivierAdministrator [administrator=" + administrator + ", roles=" + roles + ", permissions="
				+ permissions + "]";
	}

}
