/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 2, Problem 2: Hypercube.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/
package A2sol;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Hypercube {
	
	/* ********************* Nested Class ********************* */
	/**
	 * This structure records
	 */
	static class Corner {
		boolean[] coordinates;
		int dimension;

		/**
		 * Initialize the corner for a dimension.
		 * This method takes O(n) , where n = dimension
		 * @param dimension
		 */
		public Corner(int dimension) {
			this.coordinates = new boolean[dimension];
			for (int i = 0; i < dimension; i++) {
				this.coordinates[i] = false;
			}
			this.dimension = dimension;
		}

		/**
		 * Initial the corner for a dimension with coordinates
		 * This method takes O(n), where n = dimension
		 * @param dimension
		 * @param position Decimal type position for the coordinates, will be converted to binary
		 */
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

		/**
		 * Move one step in specific dimension
		 * This method takes O(1)
		 * @param direction
		 */
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

		public static Corner addDimension(Corner c, boolean position){
			Corner obj = new Corner(c.dimension + 1);
			for (int i = 0; i < c.dimension; i ++){
				obj.coordinates[i] = c.coordinates[i];
			}
			obj.coordinates[c.dimension] = position;
			return obj;
		}
		
	}
	
	/**
	 * A pure queue structure that only supports enqueue and dequeue.
	 */
	static class PureQueue<T> {
		private Queue<T> data = new LinkedBlockingQueue<T>();
		
		public void enqueue(T obj){
			data.add(obj);
		}
		
		public T dequeue(){
			return data.poll();
		}
	}

	/*
	 * Recursive Version
	 */

	/**
	 * Helper method for the recursion
	 * @param dimension
	 * @param current
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
	 * Iterative Version
	 */

	public void iterativeWalk(int dimension) {
		// Initialize the starting point, and print
		PureQueue<Corner> points = new PureQueue<Corner>();

		// Initialize the queue
		points.enqueue(new Corner(1,0));
		points.enqueue(new Corner(1,1));
		boolean order = true;		
		
		// Print out starting point
		Corner offer;
		while ((offer = points.dequeue()) != null){
			if (offer.dimension == dimension){
				System.out.println(offer.toString());
			}else{
				if (order) {
					points.enqueue(Corner.addDimension(offer, false));
					points.enqueue(Corner.addDimension(offer, true));
				}else{
					points.enqueue(Corner.addDimension(offer, true));
					points.enqueue(Corner.addDimension(offer, false));
				}
				order = !order;
			}
		}

		
	}

	public static void main(String[] args) {
		Hypercube hy = new Hypercube();
		int n = 20;
		hy.recursiveWalk(n);
		System.out.println("-------------");
		hy.iterativeWalk(n);
		System.out.println("-------------");
	}

}
