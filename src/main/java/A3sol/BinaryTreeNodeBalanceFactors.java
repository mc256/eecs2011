package A3sol;

public class BinaryTreeNodeBalanceFactors {

	public static class LinkedBinaryTree<T> {
		public Node<T> root;

		public LinkedBinaryTree(T[] bfsAarray) {
			this.root = bfsArrayToTree(bfsAarray, 0);
		}

		private Node<T> bfsArrayToTree(T[] array, int current) {
			if (current < array.length && array[current] != null) {
				return new Node<T>(array[current], this.bfsArrayToTree(array, 2 * current + 1), this.bfsArrayToTree(array, 2 * current + 2));
			} else {
				return null;
			}
		}

		private int printBalanceFactor(Node<T> current) {
			// Euler tour
			if (current == null) {
				// Visit External
				return 0;
			} else {
				// Visit left
				int leftHeight = this.printBalanceFactor(current.getLeft());
				// Visit below
				int rightHeight = this.printBalanceFactor(current.getRight());
				// Visit right
				System.out.printf("(%s,%d)\n", current.getElement(), rightHeight - leftHeight);
				// Return result
				return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
			}
		}

		public void printBalanceFactor() {
			this.printBalanceFactor(this.root);
		}

	}

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
		 * @return the element
		 */
		public T getElement() {
			return element;
		}

		/**
		 * @return the left
		 */
		public Node<T> getLeft() {
			return left;
		}

		/**
		 * @return the right
		 */
		public Node<T> getRight() {
			return right;
		}

		/**
		 * @param element
		 *            the element to set
		 */
		public void setElement(T element) {
			this.element = element;
		}

		/**
		 * @param left
		 *            the left to set
		 */
		public void setLeft(Node<T> left) {
			this.left = left;
		}

		/**
		 * @param right
		 *            the right to set
		 */
		public void setRight(Node<T> right) {
			this.right = right;
		}

		@Override
		public String toString() {
			return this.element.toString();
		}
	}

	public static void printBFSTree(Object[] bfsTree) {
		StringBuilder sb = new StringBuilder();
		// Do some necessary calculation first
		int indent = 1;
		int remain = bfsTree.length;
		while ((remain /= 2) != 0) {
			indent *= 2;
		}
		int currentStage = 2;
		boolean newLine = false;
		sb.append(String.format("%" + (6 * (indent - 1)) + "s", ""));
		for (int i = 0; i < bfsTree.length; i++) {
			// Start a new line
			if (newLine) {
				newLine = false;
				currentStage *= 2;
				sb.append("\n");
				indent /= 2;
				sb.append(String.format("%" + (6 * (indent - 1) + 1) + "s", ""));
			}
			// Print element
			if (bfsTree[i] == null) {
				sb.append("[null]");
			} else {
				sb.append(String.format("[%-4s]", bfsTree[i]));
			}
			// If not a new line then print the blank between elements
			if ((currentStage - 2) == i) {
				newLine = true;
			} else {
				sb.append(String.format("%" + (6 * (indent * 2 - 1)) + "s", ""));
			}
		}
		System.out.printf("%s\n", sb.toString());
	}

	public static void main(String[] args) {
		// You must provide a complete binary tree, but the "null" node and its
		// subtree will not be built in the data structure

		LinkedBinaryTree<String> tree;
		String[] bfsTree1 = { "A", "B", "C", "D", "E", "F", null, "H", null, "J", "K", "L", null, null, null };
		String[] bfsTree2 = { "B", "A", "D", null, null, "C", "E" };

		printBFSTree(bfsTree1);
		tree = new LinkedBinaryTree<String>(bfsTree1);
		tree.printBalanceFactor();
		System.out.println("-------------------------");

		printBFSTree(bfsTree2);
		tree = new LinkedBinaryTree<String>(bfsTree2);
		tree.printBalanceFactor();
		System.out.println("-------------------------");

	}

}
