package com.project.order.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.order.dto.OrderDTO;
import com.project.order.dto.OrderDetailsDTO;
import com.project.order.dto.ProductsOrderedDTO;

import com.project.order.entity.OrderDetails;
import com.project.order.entity.ProductsOrdered;
import com.project.order.repository.OrderRepository;
import com.project.order.repository.ProductsOrderedRepository;

@Service
public class OrderService{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ProductsOrderedRepository productsOrderedRepo;

	public List<OrderDetailsDTO> getAllOrders() throws Exception {
		List<OrderDetails> orderDetails = orderRepo.findAll();
		List<OrderDetailsDTO> orderDTOs = new ArrayList<>();
		
		for(OrderDetails o : orderDetails) {
			OrderDetailsDTO orders = OrderDetailsDTO.valueOf(o);
			orderDTOs.add(orders);
		}
		logger.info("All Order details : {}", orderDTOs);
		return orderDTOs;
		
	}
	
	public OrderDTO getOrderById(Integer orderid) throws Exception{
		OrderDTO orderDTO = null;
		Optional<OrderDetails> orderdetails = orderRepo.findById(orderid);
		if(orderdetails.isPresent()) {
			OrderDetails order1 = orderdetails.get();
			orderDTO = OrderDTO.valueOf(order1);
			List<ProductsOrdered> productsOrdered=productsOrderedRepo.findByORDERID(orderid);
			List<ProductsOrderedDTO> productList=new ArrayList<>();
			for(ProductsOrdered p:productsOrdered) {
				ProductsOrderedDTO productDTO=ProductsOrderedDTO.valueOf(p);
				productList.add(productDTO);
			}
			orderDTO.setProductsOrdered(productList);			
		}
		return orderDTO;
	}
	
	public String reOrder(int ORDERID,int BUYERID) {
		Optional<OrderDetails> order = orderRepo.findById(ORDERID);
		if(order.isPresent()) {
			OrderDetails orderdetails1 = order.get();
			OrderDetailsDTO orderDetailsDTO = OrderDetailsDTO.valueOf(orderdetails1);
			if(orderDetailsDTO.getBUYERID()==BUYERID) {
				List<ProductsOrdered> productsOrdered=productsOrderedRepo.findByORDERID(ORDERID);
				List<ProductsOrderedDTO> productList=new ArrayList<>();
				for(ProductsOrdered p:productsOrdered) {
					ProductsOrderedDTO productDTO=ProductsOrderedDTO.valueOf(p);
					productList.add(productDTO);
				}
				
				OrderDetailsDTO newOrderDetailsDTO = new OrderDetailsDTO();
				newOrderDetailsDTO.setBUYERID(orderDetailsDTO.getBUYERID());
				newOrderDetailsDTO.setADDRESS(orderDetailsDTO.getADDRESS());
				newOrderDetailsDTO.setAMOUNT(orderDetailsDTO.getAMOUNT());
				newOrderDetailsDTO.setDATE(LocalDate.now());
				newOrderDetailsDTO.setSTATUS("ORDER PLACED");
				
				OrderDetails orderdetails = newOrderDetailsDTO.createEntity();
		        orderRepo.save(orderdetails);
		        
		        for(ProductsOrderedDTO p: productList) {
		        	ProductsOrderedDTO newProductOrderedDTO = new ProductsOrderedDTO();
			        newProductOrderedDTO.setORDERID(orderdetails.getORDERID());
			        newProductOrderedDTO.setPRODID(p.getPRODID());
			        newProductOrderedDTO.setSELLERID(p.getSELLERID());
			        newProductOrderedDTO.setQUANTITY(p.getQUANTITY());
			        newProductOrderedDTO.setPRICE(p.getPRICE());
			        newProductOrderedDTO.setSTATUS("ORDER PLACED");
			        
			        ProductsOrdered productOrdered = newProductOrderedDTO.createEntity();
			        productsOrderedRepo.save(productOrdered);
		        }
		        return " Reorder Successfull!!!!";
			}			
		}		
		return "Order not found for the buyer!!";
	}
}
	
	