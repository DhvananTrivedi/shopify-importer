package com.shopify.importer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shopify.importer.dto.ShopifyProductResponse;
import com.shopify.importer.model.SimpleProduct;

@RestController
@RequestMapping("/shopify-importer/product")
@EnableScheduling
public class ProductController {

	@Autowired
	private SimpleProductRepository simpleProductRepository;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/sync")
	private String sync() {
		
		final ShopifyProductResponse body = getProductsFromShopify();
		
//		final Set<SimpleProduct> productsFromShopify = body.getProducts().stream().map(product -> {
//			
//			if (CollectionUtils.isEmpty(product.getVariants())) {
//				
//				return new SimpleProduct(product);
//			} else {
//				return new ConfigurableProduct(product);
//			}
//			
//		}).collect(Collectors.toSet());
//		
//		checkShit(productsFromShopify);
		
		productService.syncLocalDb();
		
		return "response ayo bhai " + body;
	}

	private void checkShit(Set<SimpleProduct> productsFromShopify) {
		final List<SimpleProduct> existingProducts = simpleProductRepository.findAll();
		
		final List<SimpleProduct> productsToSave = new ArrayList<>();
		final List<SimpleProduct> existingProductsToDelete = new ArrayList<>();

		final Map<Long, SimpleProduct> idToExistingProduct = existingProducts.stream()
				.collect(Collectors.toMap(SimpleProduct::getId, p -> p));

		productsFromShopify.forEach(newProduct -> {
			final Long id = newProduct.getId();
			final SimpleProduct existingProduct = idToExistingProduct.get(id);
			
			if (existingProduct == null) {
				productsToSave.add(newProduct);
			} else if (existingProduct != null) {
				if (!existingProduct.equals(newProduct)) {
					productsToSave.add(newProduct);
					existingProductsToDelete.add(existingProduct);
				}
				idToExistingProduct.remove(id);
			}
		});
		
		simpleProductRepository.saveAll(productsToSave);
		
		if (!CollectionUtils.isEmpty(existingProductsToDelete) 
				|| !CollectionUtils.isEmpty(idToExistingProduct.keySet())) {
			existingProductsToDelete.addAll(idToExistingProduct.values());
			simpleProductRepository.deleteAll(existingProductsToDelete);
		}

	}

	private ShopifyProductResponse getProductsFromShopify() {
		
		String url = "https://b08da2a32e91866eaba15f796b373a1c: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@jaydip-oh.myshopify.com/admin/api/2020-07/products.json";
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("b08da2a32e91866eaba15f796b373a1c", "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");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		final HttpEntity<String> entity = new HttpEntity<>("body", headers);
		final ResponseEntity<ShopifyProductResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, ShopifyProductResponse.class);
		
		return response.getBody();
	}

}
