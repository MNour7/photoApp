package com.example.nour.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the photo_order database table.
 * 
 */
@Entity
@Table(name="photo_order")
@NamedQuery(name="PhotoOrder.findAll", query="SELECT p FROM PhotoOrder p")
public class PhotoOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	private int orderId;

	@Temporal(TemporalType.DATE)
	@Column(name="order_date")
	private Date orderDate;

	private int quantity;

	//bi-directional many-to-one association to AppUser
	@ManyToOne
	@JoinColumn(name="parent_id")
	private AppUser appUser;

	//bi-directional one-to-one association to Photo
	@OneToOne(mappedBy="photoOrder")
	private Photo photo;

	public PhotoOrder() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

}