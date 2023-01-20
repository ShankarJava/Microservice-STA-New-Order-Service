package org.sfw.micro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
	private Integer productID;
	private String name;
	private String brand;
	private String category;
	private double price;
}
