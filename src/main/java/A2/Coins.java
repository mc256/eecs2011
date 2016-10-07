package A2;

import java.util.Scanner;

public class Coins {

	public void printTitle() {
		System.out.println("This amount can be changed in the following ways:");
	}

	public void printResult(int index, int dime, int nickel, int penny) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("\t%d)", index));
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

	public void ways(int money) {
		this.printTitle();
		int count = 1;
		int maxDime = money / 10;
		for (int i = maxDime; i >= 0; i--){
			int maxNickel = (money - (i * 10)) / 5;
			for (int j = maxNickel; j >= 0; j--){
				int maxPenny = (money - (i * 10) - (j * 5));
				this.printResult(count++, i, j, maxPenny);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Enter an amount in cents:");
		Scanner in = new Scanner(System.in);
		int amount = in.nextInt();
		in.close();

		Coins c = new Coins();
		c.ways(amount);

	}

}
