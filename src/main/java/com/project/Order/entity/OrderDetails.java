package com.project.order.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int ORDERID;
	@Column(nullable = false)
	int BUYERID;
	@Column(nullable = false)
	double AMOUNT;
	@Column(nullable = false)
	LocalDate DATE;
	@Column(nullable = false)
	String ADDRESS;
	@Column(nullable = false)
	String STATUS;

	//parameterised constructor
	public OrderDetails(int ORDERID, int BUYERID, double AMOUNT, LocalDate DATE, String ADDRESS, String STATUS) {
		super();
		this.ORDERID = ORDERID;
		this.BUYERID = BUYERID;
		this.AMOUNT = AMOUNT;
		this.DATE = DATE;
		this.ADDRESS = ADDRESS;
		this.STATUS = STATUS;
	}

	//parameterless constructor
	public OrderDetails() {
		super();
	}

	//getters and setters
	public int getORDERID() {
		return ORDERID;
	}
	public void setORDERID(int ORDERID) {
		this.ORDERID = ORDERID;
	}
	public int getBuyerId() {
		return BUYERID;
	}
	public void setBuyerId(int BUYERID) {
		this.BUYERID = BUYERID;
	}
	public double getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(double AMOUNT) {
		this.AMOUNT = AMOUNT;
	}
	public LocalDate getDATE() {
		return DATE;
	}
	public void setDATE(LocalDate DATE) {
		this.DATE = DATE;
	}

	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String ADDRESS) {
		this.ADDRESS = ADDRESS;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String STATUS) {
		this.STATUS = STATUS;
	}
	
}
