package com.myretail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myretail.domain.Product;


/**
 * Repository can be used to delegate CRUD operations 
 * against the data source here in this case its Product
 */


public interface ProductRepository extends MongoRepository<Product, String> {
	
	public Product getProductByproductId(String productId);
}
