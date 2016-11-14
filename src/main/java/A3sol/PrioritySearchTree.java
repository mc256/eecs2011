/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 3, Problem 3: PrioritySearchTree.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/
package A3sol;

public class PrioritySearchTree {

	private Point[] nodes;
	private int leavesCount;

	/**
	 * Constructor for this priority search tree. build the complete binary from
	 * the leaves
	 * 
	 * @param leaves
	 *            array of Point class, precondition: the length of the array
	 *            should be 2^n (n is and integer and n > 0)
	 */
	public PrioritySearchTree(Point[] leaves) {
		// Say prerequisite
		this.nodes = new Point[leaves.length * 2 - 1];
		for (int i = 0; i < leaves.length; i++) {
			this.nodes[i + leaves.length - 1] = leaves[i];
		}
		this.leavesCount = leaves.length;
	}

	// --------------------------- IMPORTANT -----------------------------------
	/**
	 * Bottom-Up heapify for the tree. This method takes O(n) time.
	 */
	public void prioritySearch() {
		// Calculate the largest inner node index and also adjust to the
		// array from index 0
		int upperLimit = this.leavesCount - 2;

		// These loops iterate through all the inner nodes (included root).
		for (int i = upperLimit; i >= 0; i--) {
			swap(i);
		}
	}

	/**
	 * Recursive swap method, takes O(log(n)) time. But a lower average.
	 * 
	 * @param current
	 */
	private void swap(int current) {
		// Get the positions
		int left = Point.leftChild(current);
		int right = Point.rightChild(current);

		// Compare two children
		int compare = Point.max(this.nodes[left], this.nodes[right]);
		if (compare == -1) {
			// Left child should be moved up
			this.nodes[current] = new Point(this.nodes[left]);
			// We cannot not move the leaves, so mark it used
			if (this.nodes[left].isLeaf()) {
				this.nodes[left].setUsed(true);
			} else {
				this.nodes[left] = null;
				// recursively swap child node
				swap(left);
			}
		} else if (compare == 1) {
			// Right child should be moved up
			this.nodes[current] = new Point(this.nodes[right]);
			// We cannot not move the leaves, so mark it used
			if (this.nodes[right].isLeaf()) {
				this.nodes[right].setUsed(true);
			} else {
				this.nodes[right] = null;
				// recursively swap child node
				swap(right);
			}
		}
	}

	// ================================================
	// Point inner class - BEGIN
	// ------------------------------------------------
	public static class Point {
		private boolean used;
		private boolean leaf;
		private int x;
		private int y;
		private String name;

		/**
		 * Constructor. designed for the leaves
		 * 
		 * @param name
		 * @param x
		 * @param y
		 */
		public Point(String name, int x, int y) {
			this.leaf = true;
			this.used = false;
			this.x = x;
			this.y = y;
			this.name = name;
		}

		/**
		 * Constructor. designed for copying the point
		 * 
		 * @param o
		 */
		public Point(Point o) {
			this.leaf = false;
			this.used = false;
			this.x = o.getX();
			this.y = o.getY();
			this.name = o.getName();
		}

		/**
		 * @return the used
		 */
		public boolean isUsed() {
			return used;
		}

		/**
		 * @return the leaf
		 */
		public boolean isLeaf() {
			return leaf;
		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param used
		 *            the used to set
		 */
		public void setUsed(boolean used) {
			this.used = used;
		}

		/**
		 * @param leaf
		 *            the leaf to set
		 */
		public void setLeaf(boolean leaf) {
			this.leaf = leaf;
		}

		/**
		 * @param x
		 *            the x to set
		 */
		public void setX(int x) {
			this.x = x;
		}

		/**
		 * @param y
		 *            the y to set
		 */
		public void setY(int y) {
			this.y = y;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * Print the pointer.
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			if (this.leaf) {
				return String.format("[%s=(%2d,%2d)]", this.name, this.x, this.y);
			}
			return String.format("[%-10s]", this.name);
		}

		/**
		 * Return the available point with the largest Y value
		 * 
		 * @param a
		 *            one point
		 * @param b
		 *            another point
		 * @return -1 if a is the max, 1 if b is the max, 0 neither
		 */
		public static int max(Point a, Point b) {
			boolean isA = false;
			boolean isB = false;

			Integer aY = (a == null || a.isUsed()) ? null : a.getY();
			Integer bY = (b == null || b.isUsed()) ? null : b.getY();

			// Compare
			if (aY != null && bY != null) {
				if (aY > bY) {
					isA = true;
				} else {
					isB = true;
				}
			} else {
				if (aY == null && bY != null) {
					isB = true;
				} else if (aY != null && bY == null) {
					isA = true;
				}
			}
			if (isA) {
				return -1;
			} else if (isB) {
				return 1;
			}
			return 0;
		}

		/**
		 * Calculate the index of the left child for a node in given index
		 * 
		 * @param index
		 *            the parent index
		 * @return the index of the left child
		 */
		public static int leftChild(int index) {
			return 2 * index + 1;
		}

		/**
		 * Calculate the index of the right child for a node in given index
		 * 
		 * @param index
		 *            the parent index
		 * @return the index of the right child
		 */
		public static int rightChild(int index) {
			return 2 * index + 2;
		}
	}

	// ------------------------------------------------
	// Point inner class - END
	// ================================================

	// ================================================
	// The printTree() method are quite long.
	//
	// I just want to visualize the tree that we given
	// in a "good" style, so that we can test it easily.
	// ------------------------------------------------
	public void printTree() {
		StringBuilder sb = new StringBuilder();
		// Do some necessary calculation first

		int indent = (this.nodes.length + 1) / 2;
		int currentStage = 2;
		boolean newLine = false;
		sb.append(generateSpace(12 * (indent - 1)));
		for (int i = 0; i < this.nodes.length; i++) {
			// Start a new line
			if (newLine) {
				newLine = false;
				currentStage *= 2;
				sb.append("\n");
				indent /= 2;
				sb.append(generateSpace(12 * (indent - 1) + 1));
			}
			// Print element
			if (this.nodes[i] == null) {
				sb.append("[   null   ]");
			} else {
				sb.append(String.format("%-12s", this.nodes[i].toString()));
			}
			// If not a new line then print the blank between elements
			if ((currentStage - 2) == i) {
				newLine = true;
			} else {
				sb.append(generateSpace(12 * (indent * 2 - 1)));
			}
		}
		System.out.printf("%s\n", sb.toString());
	}

	private String generateSpace(int count) {
		return String.format("%" + count + "s", "");
	}
	// ------------------------------------------------
	// FINISH printTree() module
	// ================================================

	/**
	 * main method for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PrioritySearchTree T;

		// You must provide a complete binary tree as array in order of BFS.
		// The null node and the subtree of the null node (if exists), will not
		// be built in the tree.
		Point[] S1 = { new Point("p1", 1, 8), new Point("p2", 2, 7), new Point("p3", 3, 6), new Point("p4", 4, 5), new Point("p5", 5, 4), new Point("p6", 6, 3), new Point("p7", 7, 2), new Point("p8", 8, 1) };
		Point[] S2 = { new Point("p1", -8, 3), new Point("p2", -7, 1), new Point("p3", -1, 6), new Point("p4", 2, 4), new Point("p5", 4, 8), new Point("p6", 5, 9), new Point("p7", 7, 1), new Point("p8", 9, 7) };
		Point[] S3 = { new Point("p1", -8, 3), new Point("p2", -7, 1) };

		System.out.println("\n\n\n\n==========================================================================================\nBEFORE:\n");
		T = new PrioritySearchTree(S1);
		T.printTree();
		System.out.println("\n\n------------------------------------------------------------------------------------------\nAFTER:\n");
		T.prioritySearch();
		T.printTree();

		System.out.println("\n\n\n\n==========================================================================================\nBEFORE:\n");
		T = new PrioritySearchTree(S2);
		T.printTree();
		System.out.println("\n\n------------------------------------------------------------------------------------------\nAFTER:\n");
		T.prioritySearch();
		T.printTree();

		System.out.println("\n\n\n\n==========================================================================================\nBEFORE:\n");
		T = new PrioritySearchTree(S3);
		T.printTree();
		System.out.println("\n\n------------------------------------------------------------------------------------------\nAFTER:\n");
		T.prioritySearch();
		T.printTree();
	}

}
