package com.myretail.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.myretail.domain.Product;
import com.myretail.remote.feignClient.ProductInfoFeignClient;
import com.myretail.remote.foreignClient.ProductInfoForeignClientTest;
import com.myretail.repository.ProductRepository;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@InjectMocks
	ProductService productService;

	@Mock 
	ProductRepository productrepositoryMock;

	@Mock
	private ProductInfoFeignClient productInfoForeignClient;

	/**
	 * Setup for Mockito before any test run.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * 
	 *             Positive test.
	 * 
	 */
	@Test
	public void getProductByIdTest() throws Exception {
		// Repository data from mock
		Map<String, String> currency = new HashMap<>();
		currency.put("value", "50");
		currency.put("currency_code", "USD");
		Product mockProduct = new Product("13860428", "", currency);
		System.out.println(productrepositoryMock);
		Mockito.when(productrepositoryMock.getProductByproductId(Mockito.anyString())).thenReturn(mockProduct);

		Mockito.when(productInfoForeignClient.getProductInfoById(Mockito.anyString()))
				.thenReturn(new ProductInfoForeignClientTest().getProductInfoById("13860428"));

		// Actual Result
		Product actualProduct = productService.getProductById("13860428");

		// Expected Result
		Map<String, String> currency1 = new HashMap<>();
		currency.put("value", "50");
		currency.put("currency_code", "USD");
		Product expectedProduct = new Product("13860428", "The Big Lebowski (Blu-ray)", currency1);

		assertEquals(expectedProduct.getProductId(), actualProduct.getProductId());
	}

	/**
	 * @throws Exception
	 * 
	 *  Check for null pointer exception when wrong product id passed to Remote service.
	 */
	@Test(expected = NullPointerException.class)
	public void getProductInfoTest_wrongProductId() throws Exception {

		Map<String, String> currency = new HashMap<>();
		currency.put("value", "50");
		currency.put("currency_code", "USD");
		Product mockProduct = new Product("13860428", "", currency);
		Mockito.when(productrepositoryMock.getProductByproductId(Mockito.anyString())).thenReturn(mockProduct);

		productService.getProductById("12345678");
	}
}

