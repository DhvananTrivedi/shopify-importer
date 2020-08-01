package com.shopify.importer.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("1")
public class ConfigurableProduct extends SimpleProduct{

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	private Set<Variant> variants;

}
