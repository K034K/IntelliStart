package org.practical;

import java.util.Scanner;

public class User {
private Integer id;
private String firstName;
private String lastName;
private Integer money;

	public User(Integer id, String firstName, String lastName, Integer money) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.money = money;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}

	public static User createUser(){
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your first name: ");
			String firstName = scanner.nextLine();
			if(firstName.isEmpty()){
				throw new Exception("First name is empty.");
			}
			System.out.println("Enter your last name: ");
			String lastName = scanner.nextLine();
			if(lastName.isEmpty()){
				throw new Exception("Last name is empty.");
			}
			System.out.println("Enter your money: ");
			Integer money = scanner.nextInt();
			if(money < 0){
				throw new Exception("Money is negative.");
			}

			return new User((int) (Math.random()*1000), firstName, lastName, money);
		}   catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
