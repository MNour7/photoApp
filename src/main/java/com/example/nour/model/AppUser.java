package com.example.nour.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the app_user database table.
 * 
 */
@Entity
@Table(name="app_user")
@NamedQuery(name="AppUser.findAll", query="SELECT a FROM AppUser a")
public class AppUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="app_user_id")
	private int appUserId;

	private String email;

	private String firstname;

	private String lastname;

	@Lob
	private String password;

	//bi-directional one-to-one association to School
	@OneToOne
	@JoinColumn(name="app_user_id", referencedColumnName="admin_id")
	private School school;

	//bi-directional many-to-one association to Child
	@OneToMany(mappedBy="appUser")
	private List<Child> childs;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="appUser")
	private List<UserRole> userRoles;

	//bi-directional many-to-one association to Photo
	@OneToMany(mappedBy="appUser")
	private List<Photo> photos;

	//bi-directional many-to-one association to PhotoOrder
	@OneToMany(mappedBy="appUser")
	private List<PhotoOrder> photoOrders;

	public AppUser() {
	}

	public int getAppUserId() {
		return this.appUserId;
	}

	public void setAppUserId(int appUserId) {
		this.appUserId = appUserId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<Child> getChilds() {
		return this.childs;
	}

	public void setChilds(List<Child> childs) {
		this.childs = childs;
	}

	public Child addChild(Child child) {
		getChilds().add(child);
		child.setAppUser(this);

		return child;
	}

	public Child removeChild(Child child) {
		getChilds().remove(child);
		child.setAppUser(null);

		return child;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setAppUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setAppUser(null);

		return userRole;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setAppUser(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setAppUser(null);

		return photo;
	}

	public List<PhotoOrder> getPhotoOrders() {
		return this.photoOrders;
	}

	public void setPhotoOrders(List<PhotoOrder> photoOrders) {
		this.photoOrders = photoOrders;
	}

	public PhotoOrder addPhotoOrder(PhotoOrder photoOrder) {
		getPhotoOrders().add(photoOrder);
		photoOrder.setAppUser(this);

		return photoOrder;
	}

	public PhotoOrder removePhotoOrder(PhotoOrder photoOrder) {
		getPhotoOrders().remove(photoOrder);
		photoOrder.setAppUser(null);

		return photoOrder;
	}

}