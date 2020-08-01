package com.shopify.importer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.shopify.importer.dto.Product;

@Entity
@DiscriminatorValue("1")
public class ConfigurableProduct extends SimpleProduct{

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	private List<Variant> variants;
	
	public ConfigurableProduct() {
		
	}
	
	public ConfigurableProduct(Product product) {
		super();
		this.id = product.getId();
		this.title = product.getTitle();
		this.vendor = product.getVendor();
		this.productType = product.getProduct_type();
		this.handle = product.getHandle();
		this.variants = product.getVariants();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((variants == null) ? 0 : variants.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigurableProduct other = (ConfigurableProduct) obj;
		if (variants == null) {
			if (other.variants != null)
				return false;
		} else if (!variants.containsAll(other.variants) && !other.variants.containsAll(variants)) {
			return false;
		}
		return true;
	}

	
}
