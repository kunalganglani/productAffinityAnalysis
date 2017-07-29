package com.hackathon.models;

public class ProductInvoice {

	private String productDescription;
	
	public ProductInvoice() {
		super();
	}
	
	public ProductInvoice(String productDescription) {
		super();
		this.productDescription = productDescription;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public String toString() {
		return "ProductInvoice [productDescription=" + productDescription + "]";
	}
}
