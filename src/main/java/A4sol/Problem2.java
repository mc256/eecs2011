/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 4, Problem 2: Problem2.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/
package A4sol;

public class Problem2 {

	// I only implements the parts that are needed in this assignment
	public static class SimpleBinarySearchTree<T extends Comparable<? super T>> {
		Node<T> root;

		// Count Range
		public int countRange(T lower, T upper) {
			// Starting from the root.
			// The root can be any number k from -infinity < k < +infinity
			// And we want to find the nodes in lower <= k <= upper
			// By default the node is not in the range, therefore, pass false to
			// the helper method
			return this.countRangeNode(this.root, new Range<T>(null, null), new Range<T>(lower, upper), false);
		}

		/**
		 * Recursively find the range of the This method will take O(2h) which
		 * is O(h), and h is the height of the tree
		 * 
		 * @param n
		 *            the node we are handling
		 * @param current
		 *            the possible range for node n
		 * @param target
		 *            the range we desire
		 * @param previousStatus
		 *            Is the previous node in the range, true if yes, false if
		 *            no
		 * @return the possible number of nodes in the desire range
		 */
		private int countRangeNode(Node<T> n, Range<T> current, Range<T> target, boolean previousStatus) {
			int result = 0;
			// if this node is empty, we don't need to do anythings
			if (n != null) {
				boolean currentStatus = target.contains(n.data);

				// if the recursive call went through the target range boundary,
				// we have to add/deduct it's size. Further details, please see
				// the PDF
				if (currentStatus != previousStatus) {
					previousStatus = currentStatus;
					if (currentStatus) {
						result += n.size;
					} else {
						result -= n.size;
					}
				}

				// Calculate the possible range of two children.
				// We don't need to check it's exact value yet.
				Range<T> leftRange = new Range<T>(current.lower, n.data);
				Range<T> rightRange = new Range<T>(n.data, current.upper);

				// If there is possible node which is in the range, go deeper
				// and check it's exact value
				if (target.overlap(leftRange)) {
					if (!target.contains(leftRange)){
						result += this.countRangeNode(n.left, leftRange, target, currentStatus);
					}
				}
				if (target.overlap(rightRange)) {
					if (!target.contains(rightRange)){
						result += this.countRangeNode(n.right, rightRange, target, currentStatus);
					}
				}
			}
			return result;
		}

		// Insert
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

		// Remove
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

		// Utility
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
			if (this.lower != null && other.upper != null && this.lower.compareTo(other.upper) > 0) {
				return false;
			}
			if (this.upper != null && other.lower != null && this.upper.compareTo(other.lower) < 0) {
				return false;
			}
			return true;
		}

		public boolean contains(T element) {
			if (this.lower != null && this.lower.compareTo(element) > 0) {
				return false;
			}
			if (this.upper != null && this.upper.compareTo(element) < 0) {
				return false;
			}
			//System.out.printf("==>%s\n",element.toString());
			return true;
		}
		
		public boolean contains(Range<T> other){
			if (this.lower != null && other.lower != null && this.lower.compareTo(other.lower) <= 0 && this.upper != null && other.upper != null && this.upper.compareTo(other.upper) >= 0){
				return true;
			}
			return false;
		}
	}

	public static void main(String[] args) {
		// Page 466
		// Page 527
		
		SimpleBinarySearchTree<Integer> map = new SimpleBinarySearchTree<Integer>();
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
		System.out.printf("in range [%d - %d] => %d\n", 30, 80, map.countRange(30, 80));
		System.out.printf("in range [%d - %d] => %d\n", 30, 76, map.countRange(30, 76));
		System.out.printf("in range [%d - %d] => %d\n", 92, 100, map.countRange(92, 100));
		System.out.printf("in range [%d - %d] => %d\n", 110, 120, map.countRange(110, 120));
		System.out.printf("in range [%d - %d] => %d\n", 0, 8, map.countRange(0, 8));

		System.out.println("-----------------------------------------------------");
		map = new SimpleBinarySearchTree<Integer>();
		map.insert(1);
		map.insert(2);
		map.insert(3);
		map.insert(4);
		map.insert(5);
		map.insert(6);
		map.insert(7);
		map.insert(8);
		map.insert(9);
		map.insert(10);
		map.insert(11);
		map.insert(12);
		map.insert(13);
		map.insert(14);
		map.insert(15);
		System.out.println(map);
		System.out.printf("in range [%d - %d] => %d\n", 3, 8, map.countRange(3, 8));
		System.out.printf("in range [%d - %d] => %d\n", 3, 7, map.countRange(3, 7));
		System.out.printf("in range [%d - %d] => %d\n", 9, 10, map.countRange(9, 10));
		System.out.printf("in range [%d - %d] => %d\n", 11, 122, map.countRange(11, 122));
		System.out.printf("in range [%d - %d] => %d\n", 0, 8, map.countRange(0, 8));
		
		

		System.out.println("-----------------------------------------------------");
		map = new SimpleBinarySearchTree<Integer>();
		map.insert(1);
		map.insert(2);
		map.insert(7);
		map.insert(8);
		map.insert(6);
		map.insert(12);
		map.insert(3);
		map.insert(9);
		map.insert(10);
		map.insert(11);
		map.insert(56);
		map.insert(15);
		map.insert(4);
		map.insert(5);
		map.insert(13);
		map.insert(14);
		System.out.println(map);
		System.out.printf("in range [%d - %d] => %d\n", 3, 8, map.countRange(3, 8));
		System.out.printf("in range [%d - %d] => %d\n", 3, 7, map.countRange(3, 7));
		System.out.printf("in range [%d - %d] => %d\n", 9, 10, map.countRange(9, 10));
		System.out.printf("in range [%d - %d] => %d\n", 11, 122, map.countRange(11, 122));
		System.out.printf("in range [%d - %d] => %d\n", 0, 8, map.countRange(0, 8));
		

	}

}
