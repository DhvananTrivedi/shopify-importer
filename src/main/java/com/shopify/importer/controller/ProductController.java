package com.shopify.importer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.importer.dto.ProductImportResponseDTO;
import com.shopify.importer.service.ProductService;

@RestController
@RequestMapping("/shopify-importer/product")
@EnableScheduling
public class ProductController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/import")
	private ProductImportResponseDTO importProducts() {
		final long startTime = System.currentTimeMillis();
		
		final ProductImportResponseDTO responseDto = productService.syncLocalDb();
		long timeTaken = System.currentTimeMillis() - startTime;
		
		responseDto.setTotalTimeTaken(timeTaken);
		return responseDto;
	}


}
