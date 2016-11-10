package A3sol;

public class PrioritySearchTree {

	private Point[] nodes;
	private int height;

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
		this.height = this.log2(leaves.length) + 1;
	}

	// --------------------------- IMPORTANT -----------------------------------
	/**
	 * Doing priority search for the tree
	 */
	public void prioritySearch() {

		int lowerLimit = this.pow2(this.height - 2);
		int upperLimit = lowerLimit * 2;
		int searchCount = this.height - 1;

		// Adjustment to the array from index 0
		lowerLimit--;
		upperLimit--;

		// These loops iterate through all the inner nodes (included root). It
		// is similar to the bubble sort that move the correct element one level
		// up per loop.
		for (int i = 0; i < searchCount; i++) {
			for (int j = lowerLimit; j < upperLimit; j++) {
				if (this.nodes[j] == null) {
					// Fetch necessary data
					Point left = this.nodes[2 * j + 1];
					Point right = this.nodes[2 * j + 2];

					Integer leftY = (left == null || left.isUsed()) ? null : left.getY();
					Integer rightY = (right == null || right.isUsed()) ? null : right.getY();

					boolean moveLeft = false;
					boolean moveRight = false;

					// Compare
					if (leftY != null && rightY != null) {
						if (leftY > rightY) {
							moveLeft = true;
						} else {
							moveRight = true;
						}
					} else {
						if (leftY == null && rightY != null) {
							moveRight = true;
						} else if (leftY != null && rightY == null) {
							moveLeft = true;
						}
					}

					// Move the node
					if (moveLeft) {
						this.nodes[j] = new Point(left);
						// We cannot not move the leaves
						if (left.isLeaf()) {
							left.setUsed(true);
						} else {
							this.nodes[2 * j + 1] = null;
						}
					}
					if (moveRight) {
						this.nodes[j] = new Point(right);
						// We cannot not move the leaves
						if (right.isLeaf()) {
							right.setUsed(true);
						} else {
							this.nodes[2 * j + 2] = null;
						}
					}
				}
			}

			lowerLimit /= 2;
		}
	}

	/**
	 * find the number = log(n)
	 * 
	 * @param n
	 *            need to calculate
	 * @precondition n must equals to 2^n
	 * @return log(n) on base 2
	 */
	private int log2(int n) {
		int result = 0;
		while ((n /= 2) != 0) {
			result++;
		}
		return result;
	}

	/**
	 * find the number = 2^n
	 * 
	 * @param n
	 *            need to calculate
	 * @return 2^n
	 */
	private int pow2(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= 2;
		}
		return result;
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

		@Override
		public String toString() {
			if (this.leaf) {
				return String.format("[%s=(%2d,%2d)]", this.name, this.x, this.y);
			}
			return String.format("[%-10s]", this.name);

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
		//
		// But I just want to show you the complete tree.
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
		Point[] S1 = { new Point("p1", 1, 8), new Point("p2", 2, 7), new Point("p3", 3, 6), new Point("p4", 4, 5),
				new Point("p5", 5, 4), new Point("p6", 6, 3), new Point("p7", 7, 2), new Point("p8", 8, 1) };
		Point[] S2 = { new Point("p1", -8, 3), new Point("p2", -7, 1), new Point("p3", -1, 6), new Point("p4", 2, 4),
				new Point("p5", 4, 8), new Point("p6", 5, 9), new Point("p7", 7, 1), new Point("p8", 9, 7) };
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
