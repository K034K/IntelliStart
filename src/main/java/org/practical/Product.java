package org.practical;

import java.util.Scanner;

public class Product {
	private Integer id;
	private String name;
	private Integer price;

	public Product(Integer id, String name, Integer price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public static Product createProduct() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter product name: ");
			String name = scanner.nextLine();
			if (name.isEmpty()) {
				throw new Exception("Name is empty.");
			}
			System.out.println("Enter product price: ");
			Integer price = scanner.nextInt();
			if (price < 0) {
				throw new Exception("Price is negative.");
			}
			return new Product((int)(Math.random()*1000), name, price);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
