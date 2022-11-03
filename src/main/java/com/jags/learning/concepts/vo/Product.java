package com.jags.learning.concepts.vo;

public class Product {

	int productId;
	String productName;
	int prodcutPrice;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProdcutPrice() {
		return prodcutPrice;
	}
	public void setProdcutPrice(int prodcutPrice) {
		this.prodcutPrice = prodcutPrice;
	}
	public Product(int productId, String productName, int prodcutPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.prodcutPrice = prodcutPrice;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", prodcutPrice=" + prodcutPrice
				+ "]";
	}
	
	


}
