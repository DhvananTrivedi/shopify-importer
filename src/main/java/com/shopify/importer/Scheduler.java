package com.shopify.importer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	
	@Scheduled(cron="${cronExpression}")
//	@Scheduled(cron="0 * * ? * *")
	public void importProducts() {
		System.out.println("the thing is scheduled!! and running too!");
	}

}
