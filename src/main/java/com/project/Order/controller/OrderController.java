package com.project.order.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.project.order.service.OrderService;
import com.project.order.service.ProductsOrderedService;
import com.project.order.dto.CartDTO;
import com.project.order.dto.OrderDTO;
import com.project.order.dto.OrderDetailsDTO;
import com.project.order.dto.ProductDTO;
import com.project.order.dto.ProductsOrderedDTO;
import com.project.order.dto.BuyerDTO;
import com.project.order.entity.OrderDetails;
import com.project.order.entity.ProductsOrdered;
import com.project.order.repository.OrderRepository;
import com.project.order.repository.ProductsOrderedRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class OrderController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ProductsOrderedRepository productsOrderedRepo;
	
	@Autowired
	ProductsOrderedService productsOrderedService;
	
	//Get All Orders
	@GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDetailsDTO> getAllOrders() throws Exception{
		logger.info("Fetching all Orders");
		return orderService.getAllOrders();
	}
	
	//Get Order by Order Id
	@GetMapping(value="/orders/{orderid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDTO getOrderById(@PathVariable Integer orderid) throws Exception{
		logger.info("Fetching a particular order for id {}",orderid);
		OrderDTO order = orderService.getOrderById(orderid);
		return order;
	}
	
	//Place Order
	@PostMapping(value="/orders/placeOrder",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> placeOrder(@RequestBody OrderDetailsDTO orderdetailsDTO) throws Exception{
		ResponseEntity<String> response = null;
		if(orderdetailsDTO.getADDRESS().length() > 100) {
			response = new ResponseEntity<String>("Controller.INVALID_ADDRESS",HttpStatus.BAD_REQUEST);
			return response;
		}
		String carturl = "http://localhost:8100/api/getcart/";		
		String producturl = "http://localhost:8200/api/products/";
		String buyerurl = "http://localhost:8100/api/buyers/";
		
		RestTemplate restTemplate = new RestTemplate();
		int buyerid = orderdetailsDTO.getBUYERID();
		
		ResponseEntity<CartDTO[]> cartDTO = restTemplate.getForEntity(carturl+buyerid, CartDTO[].class);
		List <CartDTO> cartList = Arrays.asList(cartDTO.getBody());
		
		BuyerDTO buyerdto = restTemplate.getForObject(buyerurl+buyerid, BuyerDTO.class);
		String rewardPoints = buyerdto.getRewardPoints();
		int rewardPointsInt = Integer.parseInt(rewardPoints);
		double discount = rewardPointsInt/4;
		double amount = 0.0;
		for(CartDTO cartDTO1:cartList) {
			int prodid = cartDTO1.getProdId();
			ProductDTO productDTO = restTemplate.getForObject(producturl+prodid, ProductDTO.class);
			double price = productDTO.getPrice();
			int cartQty = cartDTO1.getQuantity();
			int productStock = productDTO.getStock();
			if(cartQty > productStock) {
				response = new ResponseEntity<String>("Controller.INVALID_STOCK",HttpStatus.BAD_REQUEST);
				return response;
			}else {
				int qty = productStock-cartQty;
				String producturl1 = "http://localhost:8200/api/products/updatestock";
				ProductDTO pdto = new ProductDTO();
				pdto.setProdId(prodid);
				pdto.setStock(qty);
				restTemplate.put(producturl1, pdto);
			}
			amount += price*cartQty;				
		}
		amount -= discount;
		int updatedRewardPoints = (int)(amount/100);
		updatedRewardPoints -= rewardPointsInt;
		String updatedRPs = Integer.toString(Math.abs(updatedRewardPoints));
		
		String rewardPointsUrl = "http://localhost:8100/api/buyer/updateRewardPoints";
		BuyerDTO bdto = new BuyerDTO();
		bdto.setBuyerId(buyerid);
		bdto.setRewardPoints(updatedRPs);
		restTemplate.put(rewardPointsUrl, bdto);
		
		OrderDetailsDTO newOrderDetailsDTO = new OrderDetailsDTO();
		newOrderDetailsDTO.setBUYERID(orderdetailsDTO.getBUYERID());
		newOrderDetailsDTO.setADDRESS(orderdetailsDTO.getADDRESS());
		newOrderDetailsDTO.setAMOUNT(amount);
		newOrderDetailsDTO.setDATE(LocalDate.now());
		newOrderDetailsDTO.setSTATUS("ORDER PLACED");
		
        OrderDetails orderdetails = newOrderDetailsDTO.createEntity();
        orderRepo.save(orderdetails);
        
        for(CartDTO cartDTO2:cartList) {
			int prodid = cartDTO2.getProdId();
			ProductDTO productDTO = restTemplate.getForObject(producturl+prodid, ProductDTO.class);
			
	        ProductsOrderedDTO newProductOrderedDTO = new ProductsOrderedDTO();
	        newProductOrderedDTO.setORDERID(orderdetails.getORDERID());
	        newProductOrderedDTO.setPRODID(prodid);
	        newProductOrderedDTO.setSELLERID(productDTO.getSellerId());
	        newProductOrderedDTO.setQUANTITY(cartDTO2.getQuantity());
	        newProductOrderedDTO.setPRICE(productDTO.getPrice());
	        newProductOrderedDTO.setSTATUS("ORDER PLACED");
	        
	        ProductsOrdered productOrdered = newProductOrderedDTO.createEntity();
	        productsOrderedRepo.save(productOrdered);
	        
        }
        
        for(CartDTO cartDTO3:cartList) {
        	 String removeCartUrl = "http://localhost:8100/api/cart/remove";
        	 HttpEntity<CartDTO> httpEntity = new HttpEntity<CartDTO>(cartDTO3);

			 restTemplate.exchange(removeCartUrl, HttpMethod.DELETE, httpEntity,Void.class);
        }
	    response =new ResponseEntity<String>("Order placed successfully!!!",HttpStatus.OK);
        return response;
	}
	

	
	@PutMapping(value="/orders/seller/status",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateStatus (@RequestBody ProductsOrderedDTO product) {
		Integer orderId=product.getORDERID();
		Integer prodId=product.getPRODID();
		String status= product.getSTATUS();
		return productsOrderedService.updateStatus(orderId,prodId,status);
	}
	
	@GetMapping(value="/orders/reOrder/{orderid}/{buyerid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public String reOrder(@PathVariable int orderid, @PathVariable int buyerid) {
		
		String response = orderService.reOrder(orderid, buyerid);
		return response;
	}

}
