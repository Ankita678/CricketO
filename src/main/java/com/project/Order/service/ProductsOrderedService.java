package com.project.Order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Order.dto.OrderDetailsDTO;
import com.project.Order.entity.OrderCompositeId;
import com.project.Order.entity.OrderDetails;
import com.project.Order.entity.ProductsOrdered;
import com.project.Order.repository.OrderRepository;
import com.project.Order.repository.ProductsOrderedRepository;
@Service
public class ProductsOrderedService {
	
	@Autowired
	ProductsOrderedRepository productsOrderedRepo;
	
	@Autowired
	OrderRepository orderRepo;

	public List<OrderDetailsDTO> getOrderBySellerId(int SELLERID) throws Exception{
		List <ProductsOrdered> productsOrdered = productsOrderedRepo.findBySELLERID(SELLERID);
		List<OrderDetailsDTO> orderDetails = new ArrayList <>();
		
		if(productsOrdered!=null) {
			for(ProductsOrdered p:productsOrdered) {
				Optional<OrderDetails> order = orderRepo.findById(p.getORDERID());
				if(order.isPresent()) {
				OrderDetails order2 = order.get();
				OrderDetailsDTO order1 = OrderDetailsDTO.valueOf(order2);
				orderDetails.add(order1);
				}
			}
			
		}else {
			throw new Exception("Service.NO_PRODUCTS_FOUND");
		}
		 return orderDetails;
	}
	
	public String updateStatus(int orderId,int prodId,String status) {
		OrderCompositeId compKey = new OrderCompositeId();
		compKey.setORDERID(orderId);
		compKey.setPRODID(prodId);
		Optional<ProductsOrdered> products = productsOrderedRepo.findById(compKey);
		if(products.isPresent()) {
			ProductsOrdered productsOrdered = products.get();
			productsOrdered.setSTATUS(status);
			productsOrderedRepo.save(productsOrdered);
			return "Order status updated successfully";
		}
			return "Error in updating the order status!!!!";
		}
	
}
