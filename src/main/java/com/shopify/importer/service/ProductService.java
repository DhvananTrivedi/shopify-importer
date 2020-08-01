package com.shopify.importer.service;

import static com.shopify.importer.constants.Constants.BODY;
import static com.shopify.importer.constants.Constants.HTTPS_PREFIX;
import static com.shopify.importer.constants.Constants.IMPORT_SUCCESS_MESSAGE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.shopify.importer.dto.ProductImportResponseDTO;
import com.shopify.importer.dto.ShopifyProductResponse;
import com.shopify.importer.model.ConfigurableProduct;
import com.shopify.importer.model.SimpleProduct;
import com.shopify.importer.repository.SimpleProductRepository;

@Component
public class ProductService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

	@Value("${shopify.api-key}")
	private String apiKey;

	@Value("${shopify.password}")
	private String password;

	@Value("${shopify.product.api}")
	private String api;
	
	@Autowired
	private SimpleProductRepository simpleProductRepository;
	
	public ProductImportResponseDTO syncLocalDb() {
		final ProductImportResponseDTO responseDto = new ProductImportResponseDTO();

		try {
			final ShopifyProductResponse shopifyProductResponse = fetchNewProducts(responseDto);

			final Set<SimpleProduct> productsFromShopify = shopifyProductResponse.getProducts().stream()
					.map(product -> {
						if (CollectionUtils.isEmpty(product.getVariants())) {
							return new SimpleProduct(product);
						} else {
							return new ConfigurableProduct(product);
						}
					}).collect(Collectors.toSet());

			updateDBIfNecessary(productsFromShopify, responseDto);
			responseDto.setMessage(IMPORT_SUCCESS_MESSAGE);
			LOG.debug(IMPORT_SUCCESS_MESSAGE);
		} catch (Exception e) {
			LOG.error("product import was interrupted due to exception : {}", e);
			responseDto.setMessage("product import was interrupted due to exception - " + e.getMessage());
		}
		
		return responseDto;
	}

	private ShopifyProductResponse fetchNewProducts(final ProductImportResponseDTO responseDto) throws IOException{
		final String uri = HTTPS_PREFIX + apiKey + ":" + password + api;
		final RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(apiKey, password);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		final HttpEntity<String> entity = new HttpEntity<>(BODY, headers);
		
		final long startTime = System.currentTimeMillis();
		final ShopifyProductResponse shopifyProductResponse = restTemplate.exchange(uri, HttpMethod.GET, entity,
				ShopifyProductResponse.class).getBody();
		final long timeTaken = System.currentTimeMillis() - startTime;
		
		responseDto.setTimeTakenToQueryShopify(timeTaken);
		LOG.info("time taken to query /{} was {} ms", api, timeTaken);
		
		return shopifyProductResponse;
	}
	
	private void updateDBIfNecessary(Set<SimpleProduct> productsFromShopify, ProductImportResponseDTO responseDto) {
		final long start = System.currentTimeMillis();
		final List<SimpleProduct> existingProducts = simpleProductRepository.findAll();
		responseDto.setNumberOfProductsBeforeImport(existingProducts.size());
		
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
		responseDto.setNumberOfNewProductsImported(productsToSave.size());
		
		if (!CollectionUtils.isEmpty(existingProductsToDelete)
				|| !CollectionUtils.isEmpty(idToExistingProduct.keySet())) {
			existingProductsToDelete.addAll(idToExistingProduct.values());
			simpleProductRepository.deleteAll(existingProductsToDelete);
			responseDto.setNumberOfExistingProductsDeleted(existingProductsToDelete.size());
		}
		long timeTaken = System.currentTimeMillis() - start;
		LOG.info("time taken to update products in db = {} ms", timeTaken);
	}
	

}
