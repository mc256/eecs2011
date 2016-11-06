package A3sol;

public class BinaryTreeNodeBalanceFactors {

	public static class LinkedBinaryTree<T> {
		public Node<T> root;

		private int printBalanceFactor(Node<T> current) {
			if (current == null) {
				return 0;
			} else {
				int leftHeight = this.printBalanceFactor(current.getLeft());
				int rightHeight = this.printBalanceFactor(current.getRight());

				System.out.printf("(%s,%d:%d)\n", current.getElement(), leftHeight, rightHeight);

				return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
			}
		}

		public void printBalanceFactor() {
			this.printBalanceFactor(this.root);
		}

		private Node<T> bfsArrayToTree(T[] array, int current, Node<T> parent) {
			if (current < array.length && array[current] != null) {
				Node<T> node = new Node<T>(array[current]);
				node.setParent(parent);
				node.setLeft(this.bfsArrayToTree(array, 2 * current + 1, node));
				node.setRight(this.bfsArrayToTree(array, 2 * current + 2, node));
				return node;
			} else {
				return null;
			}
		}

		public LinkedBinaryTree(T[] bfsAarray) {
			this.root = bfsArrayToTree(bfsAarray, 0, null);
		}

	}

	public static class Node<T> {
		private T element;
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;

		public Node(T element) {
			this.element = element;
		}

		/**
		 * @return the element
		 */
		public T getElement() {
			return element;
		}

		/**
		 * @return the parent
		 */
		public Node<T> getParent() {
			return parent;
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
		 * @param parent
		 *            the parent to set
		 */
		public void setParent(Node<T> parent) {
			this.parent = parent;
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
		// TODO Auto-generated method stub
		String[] bfsTree = { "A", "B", "C", "D", "E", "F", null, "H", null, "J", "K", "L", null, null, null };
		printBFSTree(bfsTree);
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>(bfsTree);
		tree.printBalanceFactor();
	}

}
