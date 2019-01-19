package com.example.nour.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the school database table.
 * 
 */
@Entity
@NamedQuery(name="School.findAll", query="SELECT s FROM School s")
public class School implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="school_id")
	private int schoolId;

	@Column(name="group_price")
	private double groupPrice;

	private String name;

	@Column(name="solo_price")
	private double soloPrice;

	//bi-directional many-to-one association to Class
	@OneToMany(mappedBy="school")
	private List<Class> clazzs;
	
	//bi-directional many-to-one association to Child
	@OneToMany(mappedBy="school")
	private List<Child> childs;

	//bi-directional one-to-one association to AppUser
	@OneToOne(mappedBy="school")
	private AppUser appUser;

	public School() {
	}

	public int getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public double getGroupPrice() {
		return this.groupPrice;
	}

	public void setGroupPrice(double groupPrice) {
		this.groupPrice = groupPrice;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSoloPrice() {
		return this.soloPrice;
	}

	public void setSoloPrice(double soloPrice) {
		this.soloPrice = soloPrice;
	}

	public List<Class> getClazzs() {
		return this.clazzs;
	}

	public void setClazzs(List<Class> clazzs) {
		this.clazzs = clazzs;
	}

	public Class addClazz(Class clazz) {
		getClazzs().add(clazz);
		clazz.setSchool(this);

		return clazz;
	}

	public Class removeClazz(Class clazz) {
		getClazzs().remove(clazz);
		clazz.setSchool(null);

		return clazz;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

}