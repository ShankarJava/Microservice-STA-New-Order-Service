package org.sfw.micro.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
private int orderID;
private List<Product> productList;
private double total;
private String streetName;
private String city;
}
