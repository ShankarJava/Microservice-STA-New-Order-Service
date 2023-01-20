package org.sfw.micro.controllers;

import java.util.List;

import org.sfw.micro.model.Order;
import org.sfw.micro.model.Product;
import org.sfw.micro.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new-order-service")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/newCheckOut/{category}/{brand}")
	public List<Product> checkOut(@PathVariable("category") String category, @PathVariable("brand") String brand) {
		System.out.println("OrderController-checkOut-Before");
		List<Product> productList = orderService.checkOut(category, brand);
System.out.println("OrderController-checkOut-After");
		return productList;
	}

	@GetMapping("/newPlace-order/{streetName}/{city}")
	public Order placeOrder(@PathVariable("streetName") String streetName, @PathVariable("city") String city) {
		return orderService.placeOrder(streetName, city);

	}

}
