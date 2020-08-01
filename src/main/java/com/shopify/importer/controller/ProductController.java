package com.shopify.importer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.importer.dto.ProductImportResponseDTO;
import com.shopify.importer.model.ConfigurableProduct;
import com.shopify.importer.model.SimpleProduct;
import com.shopify.importer.service.ProductService;

@RestController
@RequestMapping("/shopify-importer/product")
@EnableScheduling
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/import")
	public ProductImportResponseDTO importProducts() {
		final long startTime = System.currentTimeMillis();
		
		final ProductImportResponseDTO responseDto = productService.syncLocalDb();
		long timeTaken = System.currentTimeMillis() - startTime;
		
		responseDto.setTotalTimeTakenInMillis(timeTaken);
		return responseDto;
	}
	
	@GetMapping("/simple/get-all")
	public List<SimpleProduct> getAllSimpleProducts () {
		return productService.getAllSimpleProducts();
	}

	
	@GetMapping("/configurable/get-all")
	public List<ConfigurableProduct> getAllSimpleProduct() {
		return productService.getAllConfigurableProducts();
	}

}
