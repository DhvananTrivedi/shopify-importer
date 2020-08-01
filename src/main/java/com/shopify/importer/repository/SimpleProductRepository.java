package com.shopify.importer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.shopify.importer.model.SimpleProduct;

@Service
public interface SimpleProductRepository extends JpaRepository<SimpleProduct, Long>{

}
