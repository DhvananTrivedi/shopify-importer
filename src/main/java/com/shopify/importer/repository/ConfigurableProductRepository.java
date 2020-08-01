package com.shopify.importer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.shopify.importer.model.ConfigurableProduct;

@Service
public interface ConfigurableProductRepository extends JpaRepository<ConfigurableProduct, Long>{

}
