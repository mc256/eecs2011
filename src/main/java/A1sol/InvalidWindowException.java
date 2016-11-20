/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 1, Problem 3: InvalidWindowException.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/

package A1sol;

public class InvalidWindowException extends Exception {

	/**
	 * serialVersionUID 
	 * Some random digits, but this one is My Student ID + course ID + assignment ID...
	 */
	private static final long serialVersionUID = 2145331110002011013L;

	public InvalidWindowException(){
		super();
	}
	
	public InvalidWindowException(String msg){
		super(msg);
	}
}
