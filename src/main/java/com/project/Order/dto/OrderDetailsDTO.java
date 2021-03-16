package com.project.order.dto;




import java.time.LocalDate;

import com.project.order.entity.*;

public class OrderDetailsDTO {
	int ORDERID;
	int BUYERID;
	double AMOUNT;
	LocalDate DATE;
	String ADDRESS;
	String STATUS;

	//parameterised constructor
	public OrderDetailsDTO(int ORDERID, int BUYERID, double AMOUNT, LocalDate DATE, String ADDRESS, String STATUS) {
		super();
		this.ORDERID = ORDERID;
		this.BUYERID = BUYERID;
		this.AMOUNT = AMOUNT;
		this.DATE = DATE;
		this.ADDRESS = ADDRESS;
		this.STATUS = STATUS;
	}
	
	//parameterless constructor
	public OrderDetailsDTO() {
		super();
	}
	
	//getters and setters
		public int getORDERID() {
			return ORDERID;
		}
		public void setORDERID(int ORDERID) {
			this.ORDERID = ORDERID;
		}
		public int getBUYERID() {
			return BUYERID;
		}
		public void setBUYERID(int BUYERID) {
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

		@Override
		public String toString() {
			return "OrderdetailsDTO [ORDERID=" + ORDERID + ", BUYERID=" + BUYERID + ", AMOUNT=" + AMOUNT + ", date="
					+ DATE + ", ADDRESS=" + ADDRESS + ", STATUS=" + STATUS + "]";
		}

	
		// Converts Entity into DTO
		public static OrderDetailsDTO valueOf(OrderDetails orderdetails) {
			OrderDetailsDTO orderdetailsDTO = new OrderDetailsDTO();
			orderdetailsDTO.setORDERID(orderdetails.getORDERID());
			orderdetailsDTO.setBUYERID(orderdetails.getBuyerId());
			orderdetailsDTO.setAMOUNT(orderdetails.getAMOUNT());
			orderdetailsDTO.setDATE(orderdetails.getDATE());
			orderdetailsDTO.setADDRESS(orderdetails.getADDRESS());
			orderdetailsDTO.setSTATUS(orderdetails.getSTATUS());
			return orderdetailsDTO;
			
		}
		
		public OrderDetails createEntity() {
			OrderDetails orderDetails =new OrderDetails();
			orderDetails.setADDRESS(this.getADDRESS());
			orderDetails.setAMOUNT(this.getAMOUNT());
			orderDetails.setBuyerId(this.getBUYERID());
			orderDetails.setDATE(this.getDATE());
			orderDetails.setORDERID(this.getORDERID());
			orderDetails.setSTATUS(this.getSTATUS());
			
			return orderDetails;
			
		}
	
}

