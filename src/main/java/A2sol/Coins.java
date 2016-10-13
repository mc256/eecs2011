package A2sol;

import java.util.Scanner;

public class Coins {

	//Attributes
	public int index = 0;
	
	//Output
	public void printTitle() {
		System.out.println("This amount can be changed in the following ways:");
	}
	public void printError() {
		System.out.println("The entered amount should be a positive integer.");
	}

	public void printResult(int quarter, int dime, int nickel, int penny) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("\t%d)", ++ index));
		if (quarter != 0){
			sb.append(String.format((dime == 1 ? " %d quarter," : " %d quarters," ), dime));
		}
		if (dime != 0){
			sb.append(String.format((dime == 1 ? " %d dime," : " %d dimes," ), dime));
		}
		if (nickel != 0){
			sb.append(String.format((nickel == 1 ? " %d nickel," : " %d nickels," ), nickel));
		}
		if (penny != 0){
			sb.append(String.format((penny == 1 ? " %d penny," : " %d pennies," ), penny));
		}
		sb.deleteCharAt(sb.length() -1);
		System.out.println(sb.toString());
	}

	//Recursions
	private void countQuarter(int money){
		int max = money / 25;
		for (int i = max; i >= 0; i--){
			this.countDime(money - (i * 25), i);
		}
	}
	

	private void countDime(int money, int quarter){
		int max = money / 10;
		for (int i = max; i >= 0; i--){
			this.countNickel(money - (i * 10), quarter, i);
		}
	}
	
	private void countNickel(int money, int quarter, int dime){
		int max = money / 5;
		for (int i = max; i >= 0; i--){
			this.countPenny(money - (i * 5), quarter, dime, i);
		}
	}
	
	private void countPenny(int money, int quarter, int dime, int nickel){
		this.printResult(quarter, dime, nickel, money);
	}
	
	public void ways(int money) {
		//If an invalid input for money is provided.
		if (money <= 0){
			this.printError();
			return;
		}
		
		//Go for recursion
		this.printTitle();
		this.index = 0;
		
		//Call recursion helper method
		this.countQuarter(money);
	}

	//Test main()
	public static void main(String[] args) {
		System.out.println("Enter an amount in cents:");
		Scanner in = new Scanner(System.in);
		int amount = in.nextInt();
		in.close();

		Coins c = new Coins();
		c.ways(amount);

	}

}
