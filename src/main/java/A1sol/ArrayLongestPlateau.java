/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 1, Problem 2: ArrayLongestPlateau.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/

package A1sol;

/**
 * The purpose of this class is to find the longest plateau of an array of ints.
 * 
 * The main method runs some tests.
 * 
 * @author andy
 * 
 */

public class ArrayLongestPlateau {

	/**
	 * longestPlateau() returns the longest plateau of an array of ints.
	 * 
	 * @return an array int[3] of the form {value, start, len} representing the
	 *         longest plateau of ints[] as a length len contiguous subarray
	 *         starting at index start with common element values value.
	 * 
	 *         For example, on the input array [2, 3, 3, 3, 3, 6, 6, 1, 1, 1],
	 *         it returns [6, 5, 2], indicating the longest plateau of this
	 *         array is the subarray [6, 6]; it starts at index 5 and has length
	 *         2.
	 * 
	 * @param ints
	 *            the input array. precondition: this array is not empty
	 */

	public static int[] longestPlateau(int[] ints) {
		// Similar to the previous question
		// 1. find the non-repeat list (Squeeze), record that number and
		// 2. find the largest number(s) and also the

		// Two types of array:
		// 1. If the array has only ONE object
		// 2. An array has more than one element.
		if (ints.length == 1) {
			return new int[] { ints[0], 0, 1 };
		} else {
			// Copy the array to a temporary variable,
			// avoid any changes to the origin array
			int[] temp = new int[ints.length];
			System.arraycopy(ints, 0, temp, 0, temp.length);

			// Initialize the height and position array
			int[] width = new int[temp.length];
			int[] position = new int[temp.length];
			width[0] = 1;
			position[0] = 0;

			/////////////////////////////////////////////////////
			// Squeeze the array

			// For the first element, we don't need to touch it.
			// Set the pointer to the location 1
			// The previous number is the number in location 0
			int previous = temp[0];
			int pointer = 0;

			// Traverse the elements from Location 1 to the end,
			// If the element doesn't not equal to the previous one:
			// 1. Move the pointer to the next location
			// 2. Copy this element to the location pointed in temporary array 
			// 3. Store this element to previous
			// If the element equals to the previous one:
			// 1. Increase the width
			for (int i = 1; i < temp.length; i++) {
				if (temp[i] != previous) {
					pointer++;
					previous = temp[pointer] = temp[i];
					width[pointer] = 1;
					position[pointer] = i;
				} else {
					width[pointer]++;
				}
			}

			/////////////////////////////////////////////////////
			// Find the Longest Plateau
			int height = 0;
			int widest = 0;
			int tag = 0;

			// Check the first element
			if (temp[0] > temp[1]) {
				widest = width[0];
				height = temp[0];
			}

			// Check the elements in the middle (index (1) to (length -1))
			for (int j = 1; j < pointer; j++) {
				if ((temp[j] > temp[j - 1]) && (temp[j] > temp[j + 1]) && (width[j] > widest)) {
					widest = width[j];
					height = temp[j];
					tag = j;
				}
			}

			// Check the last element
			if ((temp[pointer] > temp[pointer - 1]) && (width[pointer] >= widest)) {
				widest = width[pointer];
				height = temp[pointer];
				tag = pointer;
			}

			/////////////////////////////////////////////////////
			return new int[] { height, position[tag], widest };
		}
	}

	/**
	 * main() runs test cases on your longestPlateau() method. Prints summary
	 * information on basic operations and halts with an error (and a stack
	 * trace) if any of the tests fail.
	 */

	public static void main(String[] args) {
		String result;

		System.out.println("Let's find longest plateaus of arrays!\n");

		int[] test1 = { 4, 1, 1, 6, 6, 6, 6, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test1) + ":");
		result = TestHelper.stringInts(longestPlateau(test1));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 3 , 4 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test2 = { 3, 3, 1, 2, 4, 2, 1, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test2) + ":");
		result = TestHelper.stringInts(longestPlateau(test2));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 3 , 0 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test3 = { 3, 3, 1, 2, 4, 0, 1, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test3) + ":");
		result = TestHelper.stringInts(longestPlateau(test3));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 1 , 6 , 4 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test4 = { 3, 3, 3, 4, 1, 2, 4, 4, 0, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test4) + ":");
		result = TestHelper.stringInts(longestPlateau(test4));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 6 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test5 = { 7, 7, 7, 7, 9, 8, 2, 5, 5, 5, 0, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test5) + ":");
		result = TestHelper.stringInts(longestPlateau(test5));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 7 , 3 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test6 = { 4 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test6) + ":");
		result = TestHelper.stringInts(longestPlateau(test6));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 0 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test7 = { 4, 4, 4, 5, 5, 5, 6, 6 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test7) + ":");
		result = TestHelper.stringInts(longestPlateau(test7));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 6 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test8 = { 4, 5 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test8) + ":");
		result = TestHelper.stringInts(longestPlateau(test8));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 1 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test9 = { -1, -2 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test9) + ":");
		result = TestHelper.stringInts(longestPlateau(test9));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ -1 , 0 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test10 = { -2, -1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test10) + ":");
		result = TestHelper.stringInts(longestPlateau(test10));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ -1 , 1 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		System.out.println("\nAdditional tests done by the student or TA:\n");

		// Insert your additional test cases here.
	}
}
