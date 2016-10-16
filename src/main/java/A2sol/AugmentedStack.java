/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 2, Problem 3: AugmentedStack.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/
package A2sol;

import java.util.Stack;

public class AugmentedStack<T extends Comparable<? super T>> {

	/* ************* Attribute ************* */
	private Stack<T> data = new Stack<T>();
	private Stack<T> min = new Stack<T>();

	/* ************* Method ************* */
	/**
	 * Push an object to stack.
	 * 
	 * Running time O(1)
	 * 
	 * @param x
	 */
	public void push(T x) {
		data.push(x);
		if (min.isEmpty()) {
			min.push(x);
		} else {
			if (min.peek().compareTo(x) >= 0) {
				min.push(x);
			}
		}
	}

	/**
	 * Pop an object from stack.
	 * 
	 * Running time O(1)
	 * 
	 * @return
	 */
	public T pop() {
		if (data.isEmpty()) {
			return null;
		} else {
			T result = data.pop();
			if (min.peek().equals(result)) {
				min.pop();
			}
			return result;
		}
	}

	/**
	 * Get the minimal object from stack.
	 * 
	 * Running time O(1)
	 * 
	 * @return
	 */
	public T getMin() {
		if (min.isEmpty()) {
			return null;
		} else {
			return min.peek();
		}
	}

	/**
	 * Check if the stack is empty.
	 * 
	 * Running time O(1)
	 * 
	 * @return true if it is empty, false if it is not empty.
	 */
	public boolean isEmpty() {
		return data.isEmpty();
	}

	/**
	 * Get the top first element in the stack without popping out.
	 * 
	 * Running time O(1)
	 * 
	 * @return
	 */
	public T top() {
		if (min.isEmpty()) {
			return null;
		} else {
			return data.peek();
		}
	}

	/* ************* Test cases ************* */
	public static void main(String[] args) {
		AugmentedStack<Integer> stack = new AugmentedStack<Integer>();

		System.out.printf("stack.isEmpty() => %b%n", stack.isEmpty());

		stack.push(750);
		System.out.printf("stack.push(750) %n");

		System.out.printf("stack.getMin() => %d%n", stack.getMin());

		stack.push(22);
		System.out.printf("stack.push(22) %n");

		System.out.printf("stack.getMin() => %d%n", stack.getMin());

		stack.push(33);
		System.out.printf("stack.push(33) %n");

		System.out.printf("stack.top() => %d%n", stack.top());
		System.out.printf("stack.getMin() => %d%n", stack.getMin());

		System.out.printf("stack.pop() => %d%n", stack.pop());
		System.out.printf("stack.pop() => %d%n", stack.pop());
		System.out.printf("stack.getMin() => %d%n", stack.getMin());
		System.out.printf("stack.pop() => %d%n", stack.pop());
		System.out.printf("stack.pop() => %d%n", stack.pop());
		System.out.printf("stack.top() => %d%n", stack.top());
		System.out.printf("stack.getMin() => %d%n", stack.getMin());

	}

}
