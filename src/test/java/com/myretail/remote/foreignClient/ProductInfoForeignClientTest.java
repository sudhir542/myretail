package com.myretail.remote.foreignClient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.myretail.remote.feignClient.ProductInfoFeignClient;

@Component
public class ProductInfoForeignClientTest implements ProductInfoFeignClient {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myretail.remote.foreignClient.ProductInfoForeignClient#
	 * getProductInfoById(java.lang.String)
	 */
	@Override
	public ResponseEntity<String> getProductInfoById(String productId) {
		String productInfo = "{\"product\": {\"item\": {\"product_description\": {\"title\": \"The Big Lebowski (Blu-ray)\"}}}}";

		return new ResponseEntity<String>(productInfo, HttpStatus.OK);
	}

}