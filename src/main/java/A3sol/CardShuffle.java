/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 3, Problem 1: CardShuffle.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/
package A3sol;

import java.util.Scanner;

public class CardShuffle {

	public static class LinkedList<T> {

		public Node<T> begin = null;
		public Node<T> end = null;

		/**
		 * Append data to the linked list.
		 * 
		 * @param data
		 *            element of the list.
		 */
		public void add(T data) {
			if (this.end == null) {
				this.begin = this.end = new Node<T>(data);
			} else {
				this.end = this.end.next = new Node<T>(data);
			}
		}

		// --------------------------- IMPORTANT ---------------------------
		/**
		 * Shuffle the list. This method takes O(1) memory. And it uses O(n) time. 
		 * 
		 * @param middle
		 *            the middle object of the list. If the list if 2n elements,
		 *            this element should be (n-1).
		 */
		public void CardShuffle(Node<T> middle) {
			// I have two pointers that they
			Node<T> pointer1 = begin;
			Node<T> pointer2 = middle.getNext();

			// Set the middle to be null
			middle.setNext(null);

			// Just connect them until it hit the end of the list
			while (pointer2 != null) {
				// We need to store the next element of the pointer, so that we
				// move the pointers to the next spot
				Node<T> temp1 = pointer1.getNext();
				Node<T> temp2 = pointer2.getNext();

				// Connect
				pointer1.setNext(pointer2);
				// Move
				pointer1 = temp1;

				// Connect
				pointer2.setNext(pointer1);
				// Move
				pointer2 = temp2;
			}
		}

		/**
		 * Get the last element of the linked list.
		 * 
		 * @return the last element
		 */
		public Node<T> getLast() {
			return this.end;
		}

		/**
		 * Covert the linked list to String for printing out.
		 * 
		 * @see java.lang.Object#toString()
		 * @return string of the linked list.
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			if (this.begin != null) {
				Node<T> pointer = this.begin;
				while (pointer != null) {
					sb.append(pointer.data.toString());
					sb.append(",");
					pointer = pointer.next;
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("]");
			return sb.toString();
		}

	}


	// ================================================
	// Node inner class - BEGIN
	// ------------------------------------------------
	public static class Node<T> {
		private T data;
		private Node<T> next;

		public Node(T data) {
			this.data = data;
		}

		/**
		 * get the data form the node.
		 * 
		 * @return the data
		 */
		public T getData() {
			return data;
		}

		/**
		 * get the next node of the this node
		 * 
		 * @return the next
		 */
		public Node<T> getNext() {
			return next;
		}

		/**
		 * set the data of the node
		 * 
		 * @param data
		 *            the data to set
		 */
		public void setData(T data) {
			this.data = data;
		}

		/**
		 * set the next node of this node
		 * 
		 * @param next
		 *            the next to set
		 */
		public void setNext(Node<T> next) {
			this.next = next;
		}

	}
	// ------------------------------------------------
	// Node inner class - END
	// ================================================

	/**
	 * main method for testing
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("Please enter the total amount k of the cards (k = 2n) or enter 0 to exit the program:\n");

			int k = in.nextInt();
			if (k == 0) {
				System.out.println("Bye!");
				break;
			} else if (k % 2 != 0) {
				System.out.println("The list should be 2n elements.");
			} else {
				int n = k / 2;
				System.out.printf("Please enter the {%d} cards:\n", k);
				LinkedList<Integer> list = new LinkedList<Integer>();

				for (int i = 0; i < n; i++) {
					list.add(in.nextInt());
				}
				// Get the ending of the first half of the list.
				Node<Integer> mark = list.getLast();
				for (int i = 0; i < n; i++) {
					list.add(in.nextInt());
				}
				System.out.printf("The cards you provided are: %s\n", list.toString());
				list.CardShuffle(mark);
				System.out.printf("After CardShuffle() method: %s\n", list.toString());
			}
			System.out.println(
					"\n\n------------------------------------------------------------------------------------\n");
		}
		in.close();
	}

}
