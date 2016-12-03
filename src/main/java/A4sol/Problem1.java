package A4sol;

import java.util.Random;

public class Problem1 {

	public static void generateSortedArray(int[] array) {
		Random rn = new Random();

		int number = rn.nextInt(10);
		for (int i = 0; i < array.length; i++) {
			array[i] = number;
			number += 1 + rn.nextInt(10);
		}
	}

	public static String arrayToString(int[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < array.length; i++) {
			sb.append(String.format("%5d", array[i]));
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

	public static void findLargest(int[] a, int[] b, int k) {
		findSmallest(a, b, a.length + b.length + 1 - k);
	}
	
	public static void findSmallest(int[] a, int[] b, int k) {
		// Declare precondition :
		// a.length == b.length
		// 1 <= k <= 2n
		// two arrays are equal length
		k--;
		int length = a.length;

		// Initial Search Objects
		Range aRange = new Range(length);
		Range bRange = new Range(length);

		Boundary aBound, bBound;
		while (true) {
			// System.out.printf("----------------------------------------------\n");
			// System.out.printf("A %s \t\t B %s\n", aRange, bRange);
			// System.out.printf("A %d[%d] \t\t B
			// %d[%d]\n",a[aRange.getMiddle()],aRange.getMiddle(),
			// b[bRange.getMiddle()],bRange.getMiddle());
			int aMid = aRange.getMiddle();
			int bMid = bRange.getMiddle();
			if (a[aMid] < b[bMid]) {
				aBound = new Boundary(aMid, bMid + aMid);
				bBound = new Boundary(aBound.getUpper() + 1, length + bMid);
			} else {
				bBound = new Boundary(bMid, aMid + bMid);
				aBound = new Boundary(bBound.getUpper() + 1, length + aMid);
			}

			// System.out.printf("A %s \t\t B %s\n", aBound, bBound);
			// Adjust Range
			if (aBound.leftBounded(k)) {
				aRange.cutLeft(aMid, !aBound.inBound(k));
			}
			if (aBound.rightBounded(k)) {
				aRange.cutRight(aMid, !aBound.inBound(k));
			}

			if (bBound.leftBounded(k)) {
				bRange.cutLeft(bMid, !bBound.inBound(k));
			}
			if (bBound.rightBounded(k)) {
				bRange.cutRight(bMid, !bBound.inBound(k));
			}

			// If founded
			if (aRange.isEmpty()) {
				System.out.printf("The result is B->%2d[%2d]\n", b[bMid], bMid);
				return;
			}
			if (bRange.isEmpty()) {
				System.out.printf("The result is A->%2d[%2d]\n", a[aMid], aMid);
				return;
			}

		}

	}

	public static class Range {
		private int left;
		private int right;

		public Range(int length) {
			this.left = 0;
			this.right = length - 1;
		}

		public int getLength() {
			int temp = right - left + 1;
			if (temp > 0) {
				return temp;
			} else {
				return 0;
			}
		}

		public boolean isEmpty() {
			if (this.getLength() == 0) {
				return true;
			} else {
				return false;
			}
		}

		public int getMiddle() {
			return (right + left) / 2;
		}

		public int getLeft() {
			return left;
		}

		public void cutLeft(int middle, boolean removeMiddle) {
			if (removeMiddle) {
				this.left = middle + 1;
			} else {
				this.left = middle;
			}
		}

		public void cutRight(int middle, boolean removeMiddle) {
			if (removeMiddle) {
				this.right = middle - 1;
			} else {
				this.right = middle;
			}
		}

		@Override
		public String toString() {
			return String.format("Range[%2d,%2d]", this.left, this.right);
		}
	}

	public static class Boundary {
		private int lower;
		private int upper;

		public Boundary(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}

		public boolean inBound(int rank) {
			if (rank >= this.lower && rank <= this.upper) {
				return true;
			} else {
				return false;
			}
		}

		public boolean rightBounded(int rank) {
			if (rank < this.upper) {
				return true;
			} else {
				return false;
			}
		}

		public boolean leftBounded(int rank) {
			if (rank > this.lower) {
				return true;
			} else {
				return false;
			}
		}

		public int getUpper() {
			return this.upper;
		}

		public int getLower() {
			return this.lower;
		}

		public String toString() {
			return String.format("Bound[%2d,%2d]", this.lower, this.upper);
		}

	}

	public static void main(String[] args) {

		// int s[] = new int[] { 3, 12, 18, 20, 28, 37, 41, 44, 45, 48 };
		// int t[] = new int[] { 4, 5, 6, 7, 8, 13, 15, 21, 23, 27 };

		// int s[] = new int[] { 0, 1, 2, 3, 4, 5 };
		// int t[] = new int[] { 6, 7, 8, 9, 10, 11 };

		int s[] = new int[] { 3, 5, 9, 15, 27, 33, 35, 41, 57, 65 };
		int t[] = new int[] { 2, 16, 18, 42, 44, 46, 48, 50, 52, 54 };

		// generateSortedArray(s);
		// generateSortedArray(t);

		System.out.printf("S = %s\n", arrayToString(s));
		System.out.printf("T = %s\n", arrayToString(t));

		findSmallest(s, t, 1);
		findSmallest(s, t, 6);
		findSmallest(s, t, 10);
	}

}

