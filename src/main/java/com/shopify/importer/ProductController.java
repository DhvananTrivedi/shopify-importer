package com.shopify.importer;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shopify.importer.dto.ShopifyProductResponseDTO;

@RestController
@RequestMapping("/shopify-importer/product")
@EnableScheduling
public class ProductController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/sync")
	private String sync() {
		
		final ShopifyProductResponseDTO body = getProductsFromShopify();
		
		return "response ayo bhai " + body;
	}

	private ShopifyProductResponseDTO getProductsFromShopify() {
		String url = "https://b08da2a32e91866eaba15f796b373a1c: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@jaydip-oh.myshopify.com/admin/api/2020-07/products.json";
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("b08da2a32e91866eaba15f796b373a1c", "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");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		ResponseEntity<ShopifyProductResponseDTO> response = restTemplate.exchange(url
															, HttpMethod.GET, entity, ShopifyProductResponseDTO.class);
		
		ShopifyProductResponseDTO body = response.getBody();
		return body;
	}

}
