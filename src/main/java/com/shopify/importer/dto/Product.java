package com.shopify.importer.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopify.importer.model.Variant;

public class Product implements Serializable{

	private static final long serialVersionUID = 5669266051014854530L;
	
	private Long id;
	private String title;
	private String vendor;
	private String product_type;
	private String handle;
	
	@JsonProperty
	@JsonIgnore
	private List<Variant> variants;

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

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public List<Variant> getVariants() {
		return variants;
	}

	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
