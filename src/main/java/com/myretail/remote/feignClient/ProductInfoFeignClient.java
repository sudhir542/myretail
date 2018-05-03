package com.myretail.remote.feignClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* 
* Feign client for remote API (redsky) communication.
* 
*/

@FeignClient(
	    name = "productInfo-service",
	    url = "http://redsky.target.com/v2/pdp/tcin/"
)
public interface ProductInfoFeignClient {
	
	/**
	 * @param productId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "{productId}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<String> getProductInfoById(@PathVariable("productId") String productId);
}