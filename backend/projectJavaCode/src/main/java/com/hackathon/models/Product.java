package com.hackathon.models;

public class Product {

	private String productName;
	
	private Integer count = 0;
	
	public Product() {
		super();
	}
	
	public Product(String productName) {
		super();
		this.productName = productName;
	}
	
	public Product(String productName, Integer count) {
		super();
		this.productName = productName;
		this.count = count;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || this == null) return false;
		Product prod = (Product)obj;
		if (this.getProductName().equals(prod.getProductName())) return true;
		return false;
	}
}
