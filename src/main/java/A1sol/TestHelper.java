/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 1, Problem 1,2,3,... : TestHelper.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/

package A1sol;

/**
 * The purpose of this class is to provide a converter from an array of ints to an equivalent string
 * representation, and a shorthand for writing and testing invariants in any program.
 * 
 * @author andy
 * 
 */
public class TestHelper {

	/**
	 * stringInts() converts an array of ints to a String.
	 * 
	 * @return a String representation of input int array ints[0..n-1] formatted
	 *         as "[ ints[0] , ints[1] , ... , ints[n-1] ]". in O(n) time.
	 * 
	 * @param ints
	 *            the input array.
	 */

	public static String stringInts(int[] ints) {

		/*
		 * To ensure O(n) time, we can use StringBuilder with repeated appends.
		 * If we use String with repeated concatenations, it will take O(n^2)
		 * time, and it will wastefully create O(n) distinct intermediate
		 * strings. This is because String is immutable. But the mutable
		 * StringBuilder is internally implemented as a dynamically expandable
		 * array, representing a character sequence. See the Java API. We prefer
		 * StringBuilder over the older version called StringBuffer.
		 */

		StringBuilder s = new StringBuilder("[");
		for (int i = 0; i < ints.length; i++) {
			s.append(" " + ints[i] + " ,");
		}
		int j = s.lastIndexOf(","); // replace the last "," with "]"
		s.deleteCharAt(j);
		s.append("]");
		return s.toString(); // String representation of character sequence s
	}

	/**
	 * verify() checks an invariant and prints an error message if it fails. If invariant is true,
	 * this method does nothing. If invariant is false, the message is printed, followed by a dump
	 * of the program stack.
	 * 
	 * @param invariant
	 *            the condition to be verified
	 * @param message
	 *            the error message to be printed if the invariant fails to hold true.
	 */

	public static void verify(boolean invariant, String message) {
		if (!invariant) {
			System.out.println("*** ERROR:  " + message);
			Thread.dumpStack();
			System.out.println("\n");
		}
  }

}

