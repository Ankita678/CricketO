package com.project.order.dto;


import java.time.LocalDate;

import java.util.List;

import com.project.order.entity.OrderDetails;

public class OrderDTO {

	private int orderid;
	private int buyerid;
	private double amount;
	private LocalDate date;
	private String address;
	private String status;
	List<ProductsOrderedDTO> productsOrdered;
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<ProductsOrderedDTO> getProductsOrdered() {
		return productsOrdered;
	}
	public void setProductsOrdered(List<ProductsOrderedDTO> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}
	
	
	public static OrderDTO valueOf(OrderDetails orderDetails) {
		OrderDTO orderDTO=new OrderDTO();
		orderDTO.setOrderid(orderDetails.getORDERID());
		orderDTO.setBuyerid(orderDetails.getBuyerId());
		orderDTO.setAmount(orderDetails.getAMOUNT());
		orderDTO.setDate(orderDetails.getDATE());
		orderDTO.setAddress(orderDetails.getADDRESS());
		orderDTO.setStatus( orderDetails.getSTATUS());
		return orderDTO;
	}
	
	
}
