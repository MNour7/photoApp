package com.example.nour.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the class database table.
 * 
 */
@Entity
@NamedQuery(name="Class.findAll", query="SELECT c FROM Class c")
public class Class implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="class_id")
	private int classId;

	private String name;

	//bi-directional many-to-one association to School
	@ManyToOne
	@JoinColumn(name="school_id")
	private School school;

	//bi-directional many-to-one association to Child
	@OneToMany(mappedBy="clazz")
	private List<Child> childs;

	//bi-directional many-to-one association to Photo
	@OneToMany(mappedBy="clazz")
	private List<Photo> photos;

	public Class() {
	}

	public int getClassId() {
		return this.classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
		child.setClazz(this);

		return child;
	}

	public Child removeChild(Child child) {
		getChilds().remove(child);
		child.setClazz(null);

		return child;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setClazz(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setClazz(null);

		return photo;
	}

}