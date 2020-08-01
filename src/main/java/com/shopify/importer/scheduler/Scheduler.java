package com.shopify.importer.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shopify.importer.dto.ProductImportResponseDTO;
import com.shopify.importer.service.ProductService;

@Component
public class Scheduler {
	
	private static final Logger LOG = LoggerFactory.getLogger(Scheduler.class);
	
	@Autowired
	ProductService productService;
	
	@Scheduled(cron="${cronExpression}")
	public void importProducts() {
		LOG.info("Product Import job has been triggered");
		
		final ProductImportResponseDTO response = productService.syncLocalDb();
		
		LOG.info("Product Import job has finished execution with response = {}", response);
	}

}
