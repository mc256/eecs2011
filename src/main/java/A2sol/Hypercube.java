package A2sol;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Hypercube {

	static class Corner {
		boolean[] coordinates;
		int dimension;

		public Corner(int dimension) {
			this.coordinates = new boolean[dimension];
			for (int i = 0; i < dimension; i++) {
				this.coordinates[i] = false;
			}
			this.dimension = dimension;
		}

		public Corner(int dimension, int position) {
			this(dimension);
			for (int i = 0; i < dimension; i++) {
				int bit = position & 1;
				position >>= 1;
				if (bit == 1) {
					this.coordinates[dimension - i - 1] = true;
				} else {
					this.coordinates[dimension - i - 1] = false;
				}
			}
		}

		public void move(int direction) {
			this.coordinates[direction] = !this.coordinates[direction];
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < this.dimension; i++) {
				if (this.coordinates[i]) {
					sb.append(1);
				} else {
					sb.append(0);
				}
			}
			return sb.toString();
		}

		// Check the difference between two corner
		public static int delta(Corner c1, Corner c2) {
			int difference = 0;
			for (int i = 0; i < c1.dimension; i++) {
				if (c1.coordinates[i] != c2.coordinates[i]) {
					difference++;
				}
			}
			return difference;
		}

	}

	/*
	 * ----------------------------------------------- Recursive Version
	 * -----------------------------------------------
	 */

	private void recursiveWalk(int dimension, Corner current) {
		// get next level of the tree
		dimension--;

		if (dimension >= 0) {
			// left child of the node
			this.recursiveWalk(dimension, current);

			// node itself
			current.move(dimension);
			System.out.println(current.toString());

			// right child of the node
			this.recursiveWalk(dimension, current);
		}
	}

	public void recursiveWalk(int dimension) {
		// Initialize the starting point, and print
		Corner startPoint = new Corner(dimension);
		System.out.println(startPoint.toString());

		// Launch the recursion
		this.recursiveWalk(dimension, startPoint);
	}

	/*
	 * ----------------------------------------------- Iterative Version
	 * -----------------------------------------------
	 */

	public void iterativeWalk(int dimension) {
		// Initialize the starting point, and print
		Queue<Corner> points = new LinkedBlockingQueue<Corner>();

		// Initialize the pool
		int power = 1;
		for (int i = 0; i < dimension; i++) {
			power *= 2;
		}
		for (int i = 0; i < power; i++) {
			points.add(new Corner(dimension, i));
		}
		System.out.println(points);

		// Print out starting point
		Corner previous = points.poll();
		Corner current;
		System.out.println(previous);
		while ((current = points.poll()) != null) {
			int delta = Corner.delta(previous, current);
			if (delta == 1) {
				System.out.println(current);
				previous = current;
			} else {
				points.add(current);
			}
		}

	}

	public static void main(String[] args) {
		Hypercube hy = new Hypercube();
		int n = 4;
		hy.recursiveWalk(n);
		System.out.println("-------------");
		hy.iterativeWalk(n);
		System.out.println("-------------");
	}

}
