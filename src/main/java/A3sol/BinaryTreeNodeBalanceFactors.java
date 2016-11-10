package A3sol;

import java.util.Scanner;

public class BinaryTreeNodeBalanceFactors {

	public static class LinkedBinaryTree<T> {
		public Node<T> root;

		/**
		 * Use a complete binary tree to create the Linked Binary Tree. The null
		 * node and the subtree of the "null" node will not be built in the
		 * Linked Binary Tree.
		 * 
		 * @param bfsAarray
		 *            the complete binary tree in array.
		 * @precondition it must have 2^n - 1 elements in this tree.
		 */
		public LinkedBinaryTree(T[] bfsAarray) {
			this.root = bfsArrayToTree(bfsAarray, 0);
		}

		/**
		 * Helper method for LinkedBinaryTree().
		 * 
		 * @param array
		 *            the complete binary tree in array.
		 * @param current
		 *            current node that need to handle in the array
		 * @return the node of the linked BinaryTree
		 */
		private Node<T> bfsArrayToTree(T[] array, int current) {
			if (current < array.length && array[current] != null) {
				return new Node<T>(array[current], this.bfsArrayToTree(array, 2 * current + 1),
						this.bfsArrayToTree(array, 2 * current + 2));
			} else {
				return null;
			}
		}

		// --------------------------- IMPORTANT ---------------------------
		/**
		 * This (helper) method will print out the balance factor of the binary
		 * tree. It is just a very simple Euler tour procedure.
		 * 
		 * @param current
		 *            the node that the program is handling
		 * @return the height of the subtree of current.
		 */
		private int printBalanceFactor(Node<T> current) {
			// EULER TOUR
			if (current == null) {
				return 0;
			} else {
				// get the height of the left and right subtree
				int leftHeight = this.printBalanceFactor(current.getLeft());
				int rightHeight = this.printBalanceFactor(current.getRight());

				// Print out the balance factor
				System.out.printf("(%s,%d)\n", current.getElement(), rightHeight - leftHeight);

				// Return height of the (sub)tree (the root is the current node)
				return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
			}
		}

		/**
		 * Start the recursion for printing the balance factor
		 */
		public void printBalanceFactor() {
			this.printBalanceFactor(this.root);
		}

	}

	// ================================================
	// Node inner class - BEGIN
	// ------------------------------------------------
	public static class Node<T> {
		private T element;
		private Node<T> left;
		private Node<T> right;

		public Node(T element, Node<T> left, Node<T> right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}

		/**
		 * Get the data in this node
		 * 
		 * @return the element
		 */
		public T getElement() {
			return element;
		}

		/**
		 * Get the left child
		 * 
		 * @return the left
		 */
		public Node<T> getLeft() {
			return left;
		}

		/**
		 * Get the right child
		 * 
		 * @return the right
		 */
		public Node<T> getRight() {
			return right;
		}

		/**
		 * Set the data of this node
		 * 
		 * @param element
		 *            the element to set
		 */
		public void setElement(T element) {
			this.element = element;
		}

		/**
		 * Set the left child
		 * 
		 * @param left
		 *            the left to set
		 */
		public void setLeft(Node<T> left) {
			this.left = left;
		}

		/**
		 * Set the right child
		 * 
		 * @param right
		 *            the right to set
		 */
		public void setRight(Node<T> right) {
			this.right = right;
		}

		/**
		 * Print the data (we do not care about the left and right child and
		 * other data right now)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return this.element.toString();
		}
	}
	// ------------------------------------------------
	// Node inner class - END
	// ================================================

	

	// ================================================
	// The printTree() method are quite long.
	//
	// I just want to visualize the tree that we given
	// in a "good" style, so that we can test it easily.
	// ------------------------------------------------

	public static void printTree(String[] bfsArray) {
		//
		// But I just want to show you the complete tree.
		StringBuilder sb = new StringBuilder();
		// Do some necessary calculation first

		int indent = (bfsArray.length + 1) / 2;
		int currentStage = 2;
		boolean newLine = false;
		sb.append(generateSpace(6 * (indent - 1)));
		for (int i = 0; i < bfsArray.length; i++) {
			// Start a new line
			if (newLine) {
				newLine = false;
				currentStage *= 2;
				sb.append("\n");
				indent /= 2;
				sb.append(generateSpace(6 * (indent - 1) + 1));
			}
			// Print element
			if (bfsArray[i] == null) {
				sb.append("[null]");
			} else {
				sb.append(String.format("[%-4s]", bfsArray[i].toString()));
			}
			// If not a new line then print the blank between elements
			if ((currentStage - 2) == i) {
				newLine = true;
			} else {
				sb.append(generateSpace(6 * (indent * 2 - 1)));
			}
		}
		System.out.printf("%s\n", sb.toString());
	}

	private static String generateSpace(int count) {
		return String.format("%" + count + "s", "");
	}

	// ------------------------------------------------
	// FINISH printTree() module
	// ================================================

	
	/**
	 * main method for testing
	 * @param args
	 */
	public static void main(String[] args) {
		// You must provide a complete binary tree, but the "null" node and its
		// subtree will not be built in the data structure

		Scanner in = new Scanner(System.in);

		LinkedBinaryTree<String> tree;

		// You must provide a complete binary tree as array.
		// The null node and the subtree of the null node (if exists), will not
		// be built in the tree.
		String[] bfsTree1 = { "A", "B", "C", "D", "E", "F", null, "H", null, "J", "K", "L", null, null, null };
		String[] bfsTree2 = { "B", "A", "D", null, null, "C", "E" };

		tree = new LinkedBinaryTree<String>(bfsTree1);
		printTree(bfsTree1);
		tree.printBalanceFactor();
		System.out.println("-------------------------");

		tree = new LinkedBinaryTree<String>(bfsTree2);
		printTree(bfsTree2);
		tree.printBalanceFactor();
		System.out.println("-------------------------");

		in.close();
	}

}
