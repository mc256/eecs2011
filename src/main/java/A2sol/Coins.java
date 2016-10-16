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
	
	/* ************* Attributes ************* */
	public int index = 0;

	public int[] result;
	public int[] unit;
	public String[] singularName;
	public String[] pluralName;

	/* ************* Constructor ************* */
	/**
	 * Initialize all the settings. Construct the fixed length array.
	 * 
	 * @param unit
	 *            the denominations of the coin (from small to large)
	 * @param singularName
	 *            name of the coin (singular)
	 * @param pluralName
	 *            name of the coin (plural)
	 */
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

	/* ************* Output ************* */
	public void printTitle() {
		System.out.println("This amount can be changed in the following ways:");
	}

	public void printError() {
		System.out.println("The entered amount should be a positive integer.");
	}

	/**
	 * Print out the result for a coin. The arrays are fixed length. Therefore,
	 * this method takes constant time.
	 */
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

	/* ************* Recursion ************* */
	/**
	 * The helper method for the recursion.
	 * 
	 * @param balance
	 *            remaining balance
	 * @param currentUnit
	 *            the index of the coin denomination array
	 */
	private void ways(int balance, int currentUnit) {
		// If the smallest
		if (currentUnit >= 0) {
			if (balance == 0) {
				// If balance equals to 0, that means we have get the correct
				// combination. We need to print out the result
				this.printResult();
			} else {
				// We can have one more same value coin
				if (balance >= this.unit[currentUnit]) {
					this.result[currentUnit]++;
					this.ways(balance - this.unit[currentUnit], currentUnit);
					this.result[currentUnit]--;
				}
				// Or we can have smaller value coin
				this.ways(balance, currentUnit - 1);
			}
		}
	}

	/**
	 * A pure recursive approach for the problem. This method takes O(n^k) time,
	 * where n the amount and the k is types of the coins
	 * 
	 * @param money
	 *            the amount that given in cents
	 */
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

	/* ************* Main Method ************* */
	// Test main()
	public static void main(String[] args) {
		// Get the test value1
		System.out.println("Enter an amount in cents:");
		Scanner in = new Scanner(System.in);
		int amount = in.nextInt();
		in.close();

		// Initial coin denominations
		int[] unit = { 1, 5, 10, 25 };
		String[] singularName = { "penny", "nickel", "dime", "quarter" };
		String[] pluralName = { "pennies", "nickels", "dimes", "quarters" };

		// Create coin system
		Coins c = new Coins(unit, singularName, pluralName);
		// Run the result
		c.ways(amount);

	}

}
