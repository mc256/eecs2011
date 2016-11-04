package A3sol;

import java.util.Scanner;

public class CardShuffle {

	public static class LinkedList<T> {

		public Node<T> begin = null;
		public Node<T> end = null;

		public void add(T data) {
			if (this.end == null) {
				this.begin = this.end = new Node<T>(data);
			} else {
				this.end.next = new Node<T>(data);
				this.end = this.end.next;
			}
		}

		public void CardShuffle(Node<T> middle) {
			Node<T> pointer1 = begin;
			Node<T> pointer2 = middle.next;
			middle.next = null;
			
			while(pointer2 != null){
				Node<T> temp1 = pointer1.next;
				Node<T> temp2 = pointer2.next;
				
				pointer1.next = pointer2;
				pointer1 = temp1;
				
				pointer2.next = pointer1;
				pointer2 = temp2;
			}
		}

		public Node<T> getLast() {
			return end;
		}

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

	public static class Node<T> {
		public T data;
		public Node<T> next;

		public Node(T data) {
			this.data = data;
		}
	}

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
			System.out.println("\n\n------------------------------------------------------------------------------------\n");
		}
		in.close();
	}

}
