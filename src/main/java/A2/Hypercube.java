package A2;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Hypercube {

	public static final int MAX_DIMENSION = 1000;
	public int dimension;

	static class Corner {
		boolean[] coordinates = new boolean[MAX_DIMENSION];
		int dimension;

		public Corner(int dimension) {
			for (int i = 0; i < dimension; i++) {
				this.coordinates[i] = false;
			}
			this.dimension = dimension;
		}

		public Corner(int dimension, boolean[] coordinates) {
			this(dimension);
			for (int i = 0; i < dimension; i++) {
				this.coordinates[i] = coordinates[i];
			}
		}

		public Corner(int dimension, boolean[] coordinates, int offset) {
			this(dimension, coordinates);
			this.coordinates[offset] = !this.coordinates[offset];
		}

		/*
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Corner other = (Corner) obj;
			if (this.dimension != other.dimension) {
				return false;
			}
			for (int i = 0; i < this.dimension; i++) {
				if (this.coordinates[i] != other.coordinates[i]) {
					return false;
				}
			}
			return true;
		}
		
		/*
		 * @see java.lang.Object#toString()
		 */
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

	}

	public Hypercube(int dimension) {
		this.dimension = dimension;
	}

	private void recursiveWalk(final Corner currentPosition, final ArrayList<Corner> result) {
		boolean noWay = true;
		result.add(currentPosition);
		for (int i = 0; i < this.dimension; i++) {
			Corner c = new Corner(this.dimension, currentPosition.coordinates, i);
			if (!result.contains(c)) {
				this.recursiveWalk(c, result);
				noWay = false;
			}
		}
		if (noWay) {
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i).toString());
			}
		}
	}

	public void recursiveWalk() {
		ArrayList<Corner> result = new ArrayList<Corner>();
		this.recursiveWalk(new Corner(this.dimension), result);
	}

	public void iterativeWalk() {
		Queue<Corner> result = new LinkedBlockingQueue<Corner>();
		Corner currentPosition = new Corner(this.dimension);
		while (true){
			boolean noWay = true;
			result.add(currentPosition);
			for (int i = 0; i < this.dimension; i++) {
				Corner c = new Corner(this.dimension, currentPosition.coordinates, i);
				if (!result.contains(c)) {
					noWay = false;
					currentPosition = c;
					break;
				}
			}
			if (noWay) {
				Corner o;
				while ((o = result.poll()) != null){
					System.out.println(o.toString());
				}
				break;
			}
		}
	}

	public static void main(String[] args) {
		Hypercube hy = new Hypercube(4);
		hy.recursiveWalk();
		hy.iterativeWalk();
	}
	
}
