package A4sol;

public class Problem2 {

	// I only implements the parts that are needed in this assignment
	public static class SimpleBinarySearchTreeMap<T extends Comparable<? super T>> {
		Node<T> root;

		public int countRange(T lower, T upper) {
			return countRangeNode(this.root, lower, upper);
		}

		private int countRangeNode(Node<T> n, T lower, T upper) {
			if (n != null) {
				if (n.data.compareTo(lower) < 0) {
					// If smaller than the lower bound find the value on the
					// right child
					return countRangeNode(n.right, lower, upper);
				} else {
					// if greater or equal than the lower bound find the
					// possible out of range element in the left child
					int leftCount = countRangeNode(n.left, lower, upper);
					// if the element is smaller than the upper bound that means
					// it is in the range
					if (n.data.compareTo(upper) < 0) {
						System.out.println(n.data);
						int rightCount = countRangeNode(n.right, lower, upper);
					}
					return 0;
				}
			}
			return 0;
		}

		public void insert(T data) {
			this.root = insertNode(root, data);
		}

		private Node<T> insertNode(Node<T> n, T data) {
			if (n == null) {
				return new Node<T>(data, null, null);
			} else {
				if (n.data.compareTo(data) > 0) {
					n.left = insertNode(n.left, data);
				} else {
					n.right = insertNode(n.right, data);
				}
				n.size++; // IMPORTANT !!!!!!
				return n;
			}
		}

		public void remove(T data) {
			this.root = removeNode(root, data);
		}

		private Node<T> removeNode(Node<T> n, T data) {
			if (n == null) {
				return null;
			} else {
				int compare = n.data.compareTo(data);
				if (compare == 0) {
					if (n.left == null && n.right == null) {
						return null;
					} else if (n.left != null && n.right != null) {
						n.data = this.largestValue(n.left);
						n.left = this.removeNode(n.left, n.data);
						n.size--; // IMPORTANT !!!!!!
						return n;
					} else {
						n.size--; // IMPORTANT !!!!!!
						if (n.left != null) {
							return n.left;
						} else {
							return n.right;
						}
					}
				} else if (compare > 0) {
					n.left = removeNode(n.left, data);
				} else {
					n.right = removeNode(n.right, data);
				}
				return n;
			}
		}

		public T largestValue() {
			return largestValue(this.root);
		}

		private T largestValue(Node<T> root) {
			if (root == null) {
				return null;
			} else if (root.right != null) {
				return largestValue(root.right);
			} else {
				return root.data;
			}
		}

		private String toStringInorder(Node<T> n) {
			StringBuilder sb = new StringBuilder();
			if (n != null) {
				if (n.left != null) {
					sb.append(toStringInorder(n.left));
					sb.append(",");
				}
				sb.append(n.data.toString());
				if (n.right != null) {
					sb.append(",");
					sb.append(toStringInorder(n.right));
				}
			}
			return sb.toString();
		}

		@Override
		public String toString() {
			return this.toStringInorder(this.root);
		}
	}

	public static class Node<T> {
		public T data;
		public int size;
		public Node<T> left;
		public Node<T> right;

		public Node(T data, Node<T> left, Node<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.size = 1;
		}

		@Override
		public String toString() {
			return this.data.toString();
		}

	}

	public static void main(String[] args) {
		// Page 466
		// Page 527
		SimpleBinarySearchTreeMap<Integer> map = new SimpleBinarySearchTreeMap<Integer>();
		map.insert(5);
		map.insert(3);
		map.insert(4);
		map.insert(6);
		map.insert(8);
		map.insert(9);
		map.insert(7);
		map.insert(1);
		System.out.println(map);
		int count = map.countRange(6, 7);
		System.out.println(count);
	}

}
