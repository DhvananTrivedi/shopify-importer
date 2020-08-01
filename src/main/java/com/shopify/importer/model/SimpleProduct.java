package com.shopify.importer.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER, name = "type", columnDefinition = "INTEGER")
public class SimpleProduct {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "vendor")
	private String vendor;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "handle")
	private String handle;

}
