package com.myretail.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.domain.Product;
import com.myretail.remote.feignClient.ProductInfoFeignClient;
import com.myretail.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

/*
 * Service that helps API do its thing
 */

@Service
public class ProductService {
	
protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductInfoFeignClient productInfoForeignClient;
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductService() {

	}
	
	/**
	 * @param productId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public Product getProductById(String productId) throws JsonParseException, JsonMappingException, IOException {
		// From application DB
		Product product = productRepository.getProductByproductId(productId);
		// From external API
		product.setTitle(this.getTitleForProduct(productId));
		logger.info("Title from RFemote API   "+ product.getTitle());
		return product;
	}
	
	/**
	 * @param productId
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws Exception
	 * 
	 * get the title from 
	 */
	@SuppressWarnings({"unchecked","rawtypes"}) 
	private String getTitleForProduct(String productId) throws JsonParseException, JsonMappingException, IOException {
		Map<String, Map> infoMap = getProductInfoFromProductInfoService(productId);

		Map<String,Map> productMap = infoMap.get("product");
        Map<String,Map> itemMap = productMap.get("item");
        Map<String,String> prodDescrMap = itemMap.get(("product_description"));
        
        return prodDescrMap.get("title");
	}
	
	/**
	 * @param productId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * 
	 * Getting remote data using Feign product service.
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	private Map<String, Map> getProductInfoFromProductInfoService(String productId) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper infoMapper = new ObjectMapper();
		System.out.println(productInfoForeignClient);
		ResponseEntity<String> response = productInfoForeignClient.getProductInfoById(productId);
		System.out.println(response.getStatusCode().value());
		Map<String, Map> infoMap = infoMapper.readValue(response.getBody(), Map.class);
		
		return infoMap;
	}

	/**
	 * @param product
	 */
	public void updateProductById(Product product) {
		productRepository.save(product);
	}

}
