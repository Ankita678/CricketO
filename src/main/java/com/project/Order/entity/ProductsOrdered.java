package com.project.order.entity;

import javax.persistence.*;

@Entity
@Table(name= "productsordered")
@IdClass(OrderCompositeId.class)
public class ProductsOrdered {
	
	@Id
	int ORDERID;
	@Id
	int PRODID;
	@Column(nullable = false)
	int SELLERID;
	@Column(nullable = false)
	int QUANTITY;
	@Column(nullable = false)
	String STATUS;
	@Column(nullable = false)
	double PRICE;
	
	//getters and setters
	public int getORDERID() {
		return ORDERID;
	}
	public void setORDERID(int ORDERID) {
		this.ORDERID = ORDERID;
	}
	public int getPRODID() {
		return PRODID;
	}
	public void setPRODID(int PRODID) {
		this.PRODID = PRODID;
	}
	public int getSELLERID() {
		return SELLERID;
	}
	public void setSELLERID(int SELLERID) {
		this.SELLERID = SELLERID;
	}

	public int getQUANTITY() {
		return QUANTITY;
	}
	public void setQUANTITY(int QUANTITY) {
		this.QUANTITY = QUANTITY;
	}
	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String STATUS) {
		this.STATUS = STATUS;
	}

	public double getPRICE() {
		return PRICE;
	}

	public void setPRICE(double PRICE) {
		this.PRICE = PRICE;
	}
	
	
	
}
