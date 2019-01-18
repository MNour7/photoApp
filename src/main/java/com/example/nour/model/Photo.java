package com.example.nour.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@NamedQuery(name="Photo.findAll", query="SELECT p FROM Photo p")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="photo_id")
	private int photoId;

	@Temporal(TemporalType.DATE)
	@Column(name="date_take")
	private Date dateTake;

	private String name;
	
	private String path;
	
	private byte[] image;

	private String type;

	//bi-directional many-to-one association to Child
	@ManyToOne
	@JoinColumn(name="child_id")
	private Child child;

	//bi-directional many-to-one association to Class
	@ManyToOne
	@JoinColumn(name="class_id")
	private Class clazz;

	//bi-directional many-to-one association to AppUser
	@ManyToOne
	@JoinColumn(name="author_id")
	private AppUser appUser;

	//bi-directional one-to-one association to PhotoOrder
	@OneToOne
	@JoinColumn(name="photo_id", referencedColumnName="photo_id")
	private PhotoOrder photoOrder;

	public Photo() {
	}

	public int getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public Date getDateTake() {
		return this.dateTake;
	}

	public void setDateTake(Date dateTake) {
		this.dateTake = dateTake;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Child getChild() {
		return this.child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public Class getClazz() {
		return this.clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public PhotoOrder getPhotoOrder() {
		return this.photoOrder;
	}

	public void setPhotoOrder(PhotoOrder photoOrder) {
		this.photoOrder = photoOrder;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] bs) {
		this.image = bs;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}