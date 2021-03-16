package com.project.Order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Order.entity.OrderCompositeId;
import com.project.Order.entity.ProductsOrdered;

@Repository
public interface ProductsOrderedRepository extends JpaRepository<ProductsOrdered, OrderCompositeId>{

	public List<ProductsOrdered> findBySELLERID(int SELLERID);
	public List<ProductsOrdered> findByORDERID(int ORDERID);
}
