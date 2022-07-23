package org.practical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

	private static String[] menuItems = {"Add user",
			"Add product",
			"Remove product",
			"Remove user",
			"Show products",
			"Show users",
			"List of users that bought product",
			"List of products that user bought",
			"Buy product",
			"Exit"};

	private static Integer[][] recipes = null;
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<Product> products = new ArrayList<Product>();
	private static Integer answer;

	public static void main(String[] args) {

		do {
			showMenu();
			getAnswer();
			switch (answer) {
				case 1:
					addUser();
					break;
				case 2:
					addProduct();
					break;
				case 3:
					removeProduct();
					break;
				case 4:
					removeUser();
					break;
				case 5:
					showAllProducts();
					break;
				case 6:
					showAllUsers();
					break;
				case 7:
					listOfUsersThatBoughtProduct();
					break;
				case 8:
					listOfProductsThatUserBought();
					break;
				case 9:
					buyProduct(findUserById(getUserId()), findProductById(getProductId()));
					break;
				case 10:
					System.out.println("Bye!");
					break;
				default:
					System.out.println("Wrong answer!");
					break;
			}

		} while (answer != menuItems.length);
	}

	//Function that returns all products with users that bought them
	private static void listOfUsersThatBoughtProduct() {
		for (Product product : products) {
			User users[] = generateUserRecipeArr(product);
			System.out.println("Product: " + product.getName() + " was bought by: ");
			for (User user : users) {
				System.out.println(user.getFirstName() + " " + user.getLastName());
			}
		}
	}

	private static User[] generateUserRecipeArr(Product product) {
		User[] users = new User[0];
		for (Integer[] recipe : recipes) {
			if (recipe[1] == product.getId()) {
				users = Arrays.copyOf(users, users.length + 1);
				users[users.length - 1] = findUserById(recipe[0]);
			}
		}
		return users;
	}

	//Function that returns all products that user bought

	private static void listOfProductsThatUserBought() {
		for (User user : users) {
			Product products[] = generateProductRecipeArr(user);
			System.out.println("User: " + user.getFirstName() + " " + user.getLastName() + " bought: ");
			for (Product product : products) {
				System.out.println(product.getName());
			}
		}
	}

	private static Product[] generateProductRecipeArr(User user) {
		Product[] products = new Product[0];
		for (Integer[] recipe : recipes) {
			if (recipe[0] == user.getId()) {
				products = Arrays.copyOf(products, products.length + 1);
				products[products.length - 1] = findProductById(recipe[1]);
			}
		}
		return products;
	}

	private static Integer getUserId() {
		showAllUsers();
		System.out.println("Enter user id: ");
		Scanner scanner = new Scanner(System.in);
		Integer id = scanner.nextInt();

		return id;
	}

	private static Integer getProductId() {
		showAllProducts();
		System.out.println("Enter product id: ");
		Scanner scanner = new Scanner(System.in);
		Integer id = scanner.nextInt();

		return id;
	}

	public static void getAnswer() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your answer: ");
		answer = scanner.nextInt();
		//
	}

	public static void addToRecipes(Integer userId, Integer productId) {
		try {

			if(recipes == null) {
				recipes = new Integer[][]{{userId, productId}};
			} else {
				recipes = Arrays.copyOf(recipes, recipes.length + 1);
				recipes[recipes.length - 1] = new Integer[]{userId, productId};
			}

//			Integer[][] newRecipes = Arrays.copyOf(recipes, recipes.length + 1);
//			newRecipes[newRecipes.length-1][0] = userId;
//			newRecipes[newRecipes.length-1][1] = productId;
//			recipes = newRecipes;

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void showMenu() {
		for (int i = 0; i < menuItems.length; i++) {
			System.out.println(i + 1 + " " + menuItems[i]);
		}
	}


	public static void addUser() {
		User user = User.createUser();
		if (user != null) {
			users.add(user);
		}
	}

	public static void addProduct() {
		Product product = Product.createProduct();
		if (product != null) {
			products.add(product);
		}
	}

	public static User findUserById(Integer id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public static Product findProductById(Integer id) {
		for (Product product : products) {
			if (product.getId().equals(id)) {
				return product;
			}
		}
		return null;
	}


	public static void removeProduct() {
		Menu.showAllProducts();
		System.out.println("Enter product id: ");
		Integer id = Integer.parseInt(new Scanner(System.in).nextLine());
		for (Integer recipe[] : recipes) {
			if (recipe[1] == id) {
				deleteRecipe(id);
			}
		}
		Product product = findProductById(id);
		if (product != null) {
			products.remove(product);
		}
	}

	private static void deleteRecipe(Integer id) {
		for (int i = 0; i < recipes.length; i++) {
			if (recipes[i][1] == id) {
				Integer newRecipes[][] = new Integer[recipes.length - 1][2];
				for (int j = 0; j < i; j++) {
					newRecipes[j] = recipes[j];
				}
				for (int j = i; j < recipes.length - 1; j++) {
					newRecipes[j] = recipes[j + 1];
				}
				recipes = newRecipes;

			}
		}
	}

	public static void removeUser() {
		Menu.showAllUsers();
		System.out.println("Enter user id: ");
		Integer id = Integer.parseInt(new Scanner(System.in).nextLine());
		User user = findUserById(id);
		if (user != null) {
			users.remove(user);
		}
	}

	public static void showAllUsers() {
		for (User user : users) {
			System.out.println(user.getId() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getMoney());
		}
	}

	public static void showAllProducts() {
		for (Product product : products) {
			System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice());
		}
	}

	public static void buyProduct(User user, Product product) {
		try {
			if (user.getMoney() >= product.getPrice()) {
				user.setMoney(user.getMoney() - product.getPrice());
				addToRecipes(user.getId(), product.getId());
				System.out.println("You have bought " + product.getName() + " for " + product.getPrice() + " coins.");
			} else {
				throw new Exception("You don't have enough money.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
