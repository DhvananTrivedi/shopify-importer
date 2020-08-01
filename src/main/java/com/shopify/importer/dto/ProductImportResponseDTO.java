package com.shopify.importer.dto;

public class ProductImportResponseDTO {
	
	private int numberBeforeImport;
	private int numberOfNewImported;
	private int timeTaken;
	private String message;
	
	public int getNumberBeforeImport() {
		return numberBeforeImport;
	}
	public void setNumberBeforeImport(int numberBeforeImport) {
		this.numberBeforeImport = numberBeforeImport;
	}
	public int getNumberOfNewImported() {
		return numberOfNewImported;
	}
	public void setNumberOfNewImported(int numberOfNewImported) {
		this.numberOfNewImported = numberOfNewImported;
	}
	public int getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
