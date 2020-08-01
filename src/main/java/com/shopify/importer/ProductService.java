package com.shopify.importer;

import static com.shopify.importer.constants.Constants.BODY;
import static com.shopify.importer.constants.Constants.HTTPS_PREFIX;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.shopify.importer.dto.ShopifyProductResponse;
import com.shopify.importer.model.ConfigurableProduct;
import com.shopify.importer.model.SimpleProduct;

@Component
public class ProductService {

	@Value("${shopify.api-key}")
	private String apiKey;

	@Value("${shopify.password}")
	private String password;

	@Value("${shopify.product.api}")
	private String api;
	
	@Autowired
	private SimpleProductRepository simpleProductRepository;
	
	public void syncLocalDb() {
		final String uri = HTTPS_PREFIX + apiKey + ":" + password + api;
		final RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(apiKey, password);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		final HttpEntity<String> entity = new HttpEntity<>(BODY, headers);
		final ResponseEntity<ShopifyProductResponse> response = restTemplate.exchange(uri, HttpMethod.GET, entity,
				ShopifyProductResponse.class);

		final ShopifyProductResponse body = response.getBody();

		final Set<SimpleProduct> productsFromShopify = body.getProducts().stream().map(product -> {

			if (CollectionUtils.isEmpty(product.getVariants())) {

				return new SimpleProduct(product);
			} else {
				return new ConfigurableProduct(product);
			}

		}).collect(Collectors.toSet());

		updateDBIfNecessary(productsFromShopify);
	}
	
	private void updateDBIfNecessary(Set<SimpleProduct> productsFromShopify) {
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
	

}
