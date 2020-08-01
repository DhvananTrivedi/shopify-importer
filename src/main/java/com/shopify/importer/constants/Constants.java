package com.shopify.importer.constants;

import org.springframework.stereotype.Component;

@Component
public class Constants {
	
	private Constants () {
		// prevent unnecessary instantiation
	}
	
	public static final String BODY = "body";
	
	public static final String HTTPS_PREFIX = "https://";
	
	public static final String IMPORT_SUCCESS_MESSAGE = "Product import has been successful!";
	
}
