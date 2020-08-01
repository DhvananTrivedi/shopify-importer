package com.shopify.importer.dto;

public class ProductImportResponseDTO {
	
	private long numberOfProductsBeforeImport;
	private long numberOfNewProductsImported;
	private long numberOfExistingProductsDeleted;
	private long numberOfProductsAfterImport;
	private long timeTakenToQueryShopify;
	private long totalTimeTaken;
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

	public long getNumberOfProductsAfterImport() {
		return numberOfProductsBeforeImport - numberOfExistingProductsDeleted + numberOfNewProductsImported;
	}
	
	public void setNumberOfProductsAfterImport(long numberOfProductsAfterImport) {
		this.numberOfProductsAfterImport = numberOfProductsAfterImport;
	}
	public long getTimeTakenToQueryShopify() {
		return timeTakenToQueryShopify;
	}
	public void setTimeTakenToQueryShopify(long timeTakenToQueryShopify) {
		this.timeTakenToQueryShopify = timeTakenToQueryShopify;
	}
	public long getTotalTimeTaken() {
		return totalTimeTaken;
	}
	public void setTotalTimeTaken(long totalTimeTaken) {
		this.totalTimeTaken = totalTimeTaken;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
