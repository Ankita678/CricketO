package com.project.order.dto;


public class CartDTO {

	private int buyerId;
	private int prodId;
	private int quantity;
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartDTO() {
		super();
	}
	public CartDTO(int buyerId, int prodId, int quantity) {
		super();
		this.buyerId = buyerId;
		this.prodId = prodId;
		this.quantity = quantity;
	}
	
}
