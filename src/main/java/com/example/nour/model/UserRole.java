package com.example.nour.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_role_id")
	private int userRoleId;

	//bi-directional many-to-one association to AppUser
	@ManyToOne
	@JoinColumn(name="app_user_id")
	private AppUser appUser;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

	public UserRole() {
	}

	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}