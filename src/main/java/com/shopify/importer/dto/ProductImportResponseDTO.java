package com.shopify.importer.dto;

public class ProductImportResponseDTO {
	
	private long numberOfProductsBeforeImport;
	private long numberOfNewProductsImported;
	private long numberOfExistingProductsDeleted;
//	private long numberOfProductsAfterImport;
	private long timeTakenToQueryShopifyInMillis;
	private long totalTimeTakenInMillis;
	private String message;
	
	public long getNumberOfProductsBeforeImport() {
		return numberOfProductsBeforeImport;
	}
	public void setNumberOfProductsBeforeImport(long numberOfProductsBeforeImport) {
		this.numberOfProductsBeforeImport = numberOfProductsBeforeImport;
	}
	public long getNumberOfNewProductsImported() {
		return numberOfNewProductsImported;
	}
	public void setNumberOfNewProductsImported(long numberOfNewProductsImported) {
		this.numberOfNewProductsImported = numberOfNewProductsImported;
	}
	public long getNumberOfExistingProductsDeleted() {
		return numberOfExistingProductsDeleted;
	}
	public void setNumberOfExistingProductsDeleted(long numberOfExistingProductsDeleted) {
		this.numberOfExistingProductsDeleted = numberOfExistingProductsDeleted;
	}
//	public void setNumberOfProductsAfterImport(long numberOfProductsAfterImport) {
//		this.numberOfProductsAfterImport = numberOfProductsAfterImport;
//	}
	public long getTimeTakenToQueryShopifyInMillis() {
		return timeTakenToQueryShopifyInMillis;
	}
	public void setTimeTakenToQueryShopifyInMillis(long timeTakenToQueryShopifyInMillis) {
		this.timeTakenToQueryShopifyInMillis = timeTakenToQueryShopifyInMillis;
	}
	public long getTotalTimeTakenInMillis() {
		return totalTimeTakenInMillis;
	}
	public void setTotalTimeTakenInMillis(long totalTimeTakenInMillis) {
		this.totalTimeTakenInMillis = totalTimeTakenInMillis;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public long getNumberOfProductsAfterImport() {
		return numberOfProductsBeforeImport - numberOfExistingProductsDeleted + numberOfNewProductsImported;
	}
	
	
}
