package com.shopify.importer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="variant")
public class Variant implements Serializable {
	
	private static final long serialVersionUID = -9217775770010154810L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="product_id")
	private String product_id;
	
	@Column(name="inventory_quantity")
	private Long inventory_quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public Long getInventory_quantity() {
		return inventory_quantity;
	}

	public void setInventory_quantity(Long inventory_quantity) {
		this.inventory_quantity = inventory_quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
