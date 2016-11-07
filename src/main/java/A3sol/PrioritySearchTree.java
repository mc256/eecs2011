package A3sol;

public class PrioritySearchTree {

	private Point[] nodes;
	private int height;

	public PrioritySearchTree(Point[] leaves) {
		// Say prerequisite
		this.nodes = new Point[leaves.length * 2 - 1];
		for (int i = 0; i < leaves.length; i++) {
			this.nodes[i + leaves.length - 1] = leaves[i];
		}
		this.height = this.log2(leaves.length) + 1;
	}

	public void prioritySearch() {

		int lowerLimit = this.pow2(this.height - 2);
		int upperLimit = lowerLimit * 2;
		int searchCount = this.height - 1;

		// Adjustment to the array from index 0
		lowerLimit--;
		upperLimit--;

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

					// Move
					if (moveLeft) {
						this.nodes[j] = new Point(left);
						if (left.isLeaf()) {
							left.setUsed(true);
						} else {
							this.nodes[2 * j + 1] = null;
						}
					}
					if (moveRight) {
						this.nodes[j] = new Point(right);
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

	private int log2(int number) {
		int result = 0;
		while ((number /= 2) != 0) {
			result++;
		}
		return result;
	}

	private int pow2(int number) {
		int result = 1;
		for (int i = 1; i <= number; i++) {
			result *= 2;
		}
		return result;
	}

	public void printTree() {
		StringBuilder sb = new StringBuilder();
		// Do some necessary calculation first
		
		int indent = (this.nodes.length + 1)/2;
		int currentStage = 2;
		boolean newLine = false;
		sb.append(String.format("%" + (12 * (indent - 1)) + "s", ""));
		for (int i = 0; i < this.nodes.length; i++) {
			// Start a new line
			if (newLine) {
				newLine = false;
				currentStage *= 2;
				sb.append("\n");
				indent /= 2;
				sb.append(String.format("%" + (12 * (indent - 1) + 1) + "s", ""));
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
				sb.append(String.format("%" + (12 * (indent * 2 - 1)) + "s", ""));
			}
		}
		System.out.printf("%s\n", sb.toString());
	}

	public static class Point {
		private boolean used;
		private boolean leaf;
		private int x;
		private int y;
		private String name;

		public Point(String name, int x, int y) {
			this.leaf = true;
			this.used = false;
			this.x = x;
			this.y = y;
			this.name = name;
		}

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
			if (this.leaf){
				return String.format("[%s=(%2d,%2d)]", this.name,this.x,this.y);
			}
			return String.format("[%-10s]", this.name);
			
		}
	}

	public static void main(String[] args) {
		PrioritySearchTree T;
		Point[] S1 = { new Point("p1", 1, 8), new Point("p2", 2, 7), new Point("p3", 3, 6), new Point("p4", 4, 5), new Point("p5", 5, 4), new Point("p6", 6, 3), new Point("p7", 7, 2), new Point("p8", 8, 1) };
		Point[] S2 = { new Point("p1", -8, 3), new Point("p2", -7, 1), new Point("p3", -1, 6), new Point("p4", 2, 4), new Point("p5", 4, 8), new Point("p6", 5, 9), new Point("p7", 7, 1), new Point("p8", 9, 7) };
		Point[] S3 = { new Point("p1", -8, 3), new Point("p2", -7, 1)};

		System.out.println();
		T = new PrioritySearchTree(S1);
		T.printTree();
		T.prioritySearch();
		T.printTree();

		System.out.println();
		T = new PrioritySearchTree(S2);
		T.printTree();
		T.prioritySearch();
		T.printTree();
		
		System.out.println();
		T = new PrioritySearchTree(S3);
		T.printTree();
		T.prioritySearch();
		T.printTree();

	}

}
