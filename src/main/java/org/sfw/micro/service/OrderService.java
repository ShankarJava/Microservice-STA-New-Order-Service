package org.sfw.micro.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.sfw.micro.model.Order;
import org.sfw.micro.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class OrderService {

	int orderID;

	@Autowired
	RestTemplate restTemplate;

	private String BASE_URI = "http://CART-SERVICE/cart-service/";
	List<Product> productList = new ArrayList<>();

	@HystrixCommand(fallbackMethod = "fallbaclCheckout")
	public List<Product> checkOut(String category, String brand) {
		System.out.println("OrderService-checkOut-Before");
		String uri = BASE_URI + "add-many-to-cart/" + category + "/" + brand;
		List<LinkedHashMap<String, Object>> response = restTemplate.getForObject(uri, List.class);

		for (LinkedHashMap<String, Object> productMap : response) {
			Integer pID = (Integer) productMap.get("productID");
			String name = (String) productMap.get("name");
			String brnd = (String) productMap.get("brand");
			String categry = (String) productMap.get("category");
			Double price = (Double) productMap.get("price");
			Product product = new Product(pID, name, brnd, categry, price);
			productList.add(product);
		}
		System.out.println("OrderService-checkOut-After");
		return productList;
	}

	public List<Product> fallbaclCheckout(String category, String brand) {
		System.out.println("Hystrix Fall Back");
		return new ArrayList();
	}

	public Order placeOrder(String streetName, String city) {
		double total = 0;
		for (Product product : productList) {
			total += product.getPrice();
		}
		Order order = new Order(++orderID, productList, total, streetName, city);
		return order;
	}

}
