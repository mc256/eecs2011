package A4sol;

public class Problem2 {

	// I only implements the parts that are needed in this assignment
	public static class SimpleBinarySearchTreeMap<T extends Comparable<? super T>> {
		Node<T> root;

		public int countRange(T lower, T upper) {
			return this.countRangeNode(this.root, new Range<T>(null, null), new Range<T>(lower, upper), false);
		}

		private int countRangeNode(Node<T> n, Range<T> current, Range<T> target, boolean previousStatus) {
			int result = 0;
			if (n != null) {
				boolean currentStatus = target.contains(n.data);

				if (currentStatus != previousStatus) {
					previousStatus = currentStatus;
					if (currentStatus) {
						result += n.size;
					} else {
						result -= n.size;
					}
				}

				Range<T> leftRange = new Range<T>(current.lower, n.data);
				Range<T> rightRange = new Range<T>(n.data, current.upper);

				if (target.overlap(leftRange)) {
					result += this.countRangeNode(n.left, leftRange, target, currentStatus);
				}
				if (target.overlap(rightRange)) {
					result += this.countRangeNode(n.right, rightRange, target, currentStatus);
				}
			}
			return result;
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

	public static class Node<T extends Comparable<? super T>> {
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

	public static class Range<T extends Comparable<? super T>> {
		// If lower/upper is null means infinity
		public T lower;
		public T upper;

		public Range(T lowerLimit, T upperLimit) {
			this.lower = lowerLimit;
			this.upper = upperLimit;
		}

		public boolean overlap(Range<T> other) {
			if (this.lower != null && other.upper != null && this.lower.compareTo(other.upper) >= 0) {
				return false;
			}
			if (this.upper != null && other.lower != null && this.upper.compareTo(other.lower) <= 0) {
				return false;
			}
			return true;
		}

		public boolean contains(T element) {
			if (this.lower != null && this.lower.compareTo(element) >= 0) {
				return false;
			}
			if (this.upper != null && this.upper.compareTo(element) <= 0) {
				return false;
			}
			return true;
		}
	}

	public static void main(String[] args) {
		// Page 466
		// Page 527
		SimpleBinarySearchTreeMap<Integer> map = new SimpleBinarySearchTreeMap<Integer>();
		map.insert(44);
		map.insert(17);
		map.insert(88);
		map.insert(8);
		map.insert(32);
		map.insert(65);
		map.insert(97);
		map.insert(28);
		map.insert(54);
		map.insert(82);
		map.insert(93);
		map.insert(21);
		map.insert(29);
		map.insert(76);
		map.insert(80);
		System.out.println(map);
		int count = map.countRange(82, 97);
		System.out.println(count);

	}

}
