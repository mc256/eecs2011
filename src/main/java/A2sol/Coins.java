/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 2, Problem 1: Coins.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/
package A2sol;

import java.util.Scanner;

public class Coins {

	// Attributes
	public int index = 0;

	public int[] result;
	public int[] unit;
	public String[] singularName;
	public String[] pluralName;

	// Constructor
	public Coins(int[] unit, String[] singularName, String[] pluralName) {
		// Initialize the output array
		this.result = new int[unit.length];
		for (int i = 0; i < unit.length; i++) {
			this.result[i] = 0;
		}

		// Copy all the setting values
		this.unit = new int[unit.length];
		this.singularName = new String[singularName.length];
		this.pluralName = new String[pluralName.length];
		System.arraycopy(unit, 0, this.unit, 0, unit.length);
		System.arraycopy(singularName, 0, this.singularName, 0, singularName.length);
		System.arraycopy(pluralName, 0, this.pluralName, 0, pluralName.length);
	}

	// Output
	public void printTitle() {
		System.out.println("This amount can be changed in the following ways:");
	}

	public void printError() {
		System.out.println("The entered amount should be a positive integer.");
	}

	public void printResult() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("\t%d)", ++index));
		for (int i = this.unit.length - 1; i >= 0; i--) {
			if (this.result[i] != 0) {
				sb.append(String.format(" %d %s,", this.result[i], (this.result[i] == 1 ? this.singularName[i] : this.pluralName[i])));
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}

	// Recursions
	private void ways(int balance, int currentUnit) {
		// If the smallest
		if (currentUnit >= 0) {
			if (balance == 0) {
				// If balance equals to 0, that means we have get the correct
				// combination. We need to print out the result
				this.printResult();
			} else {
				//We can have one more same value coin
				if (balance >= this.unit[currentUnit]) {
					this.result[currentUnit]++;
					this.ways(balance - this.unit[currentUnit], currentUnit);
					this.result[currentUnit]--;
				}
				//Or we can have smaller value coin
				this.ways(balance, currentUnit - 1);
			}
		}
	}

	public void ways(int money) {
		// If an invalid input for money is provided.
		if (money <= 0) {
			this.printError();
			return;
		}

		// Go for recursion
		this.printTitle();
		this.index = 0;

		// Call recursion helper method
		this.ways(money, this.unit.length - 1);
	}

	// Test main()
	public static void main(String[] args) {
		//Get the test value1
		System.out.println("Enter an amount in cents:");
		Scanner in = new Scanner(System.in);
		int amount = in.nextInt();
		in.close();

		//Initial coin denominations
		int[] unit = { 1, 5, 10, 25 };
		String[] singularName = { "penny", "nickel", "dime", "quarter" };
		String[] pluralName = { "pennies", "nickels", "dimes", "quarters" };

		//Create coin system
		Coins c = new Coins(unit, singularName, pluralName);
		//Run the result
		c.ways(amount);

	}

}
