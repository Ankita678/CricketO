package com.project.order.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class OrderCompositeId implements Serializable {
	
	private int ORDERID;
	private int PRODID;
	public int getORDERID() {
		return ORDERID;
	}
	public void setORDERID(int ORDERID) {
		ORDERID = this.ORDERID;
	}
	public int getPRODID() {
		return PRODID;
	}
	public void setPRODID(int PRODID) {
		PRODID = this.PRODID;
	}
	
}
