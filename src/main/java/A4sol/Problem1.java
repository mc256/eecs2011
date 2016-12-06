/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 4, Problem 1: Problem1.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/
package A4sol;

public class Problem1 {

	//Utility Method
	/**
	 * Convert Array to String
	 * @param array the array need to be converted
	 * @return String of the array
	 */
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

	// Function Methods
	/**
	 * Find the k-th largest element in array a and array b
	 * @param a a sorted array
	 * @param b a sorted array
	 * @param k the k-th largest
	 */
	public static void findLargest(int[] a, int[] b, int k) {
		findSmallest(a, b, a.length + b.length + 1 - k);
	}
	
	/**
	 * Find the median of two array, which is the n-th smallest element, n is the length of the array
	 * @param a a sorted array
	 * @param b a sorted array
	 */
	public static void findMedian(int[] a, int[] b){
		findSmallest(a, b, a.length);
	}
	
	
	//-------------- IMPORTANT ----------------------
	/**
	 * find the k-th smallest element in array a and array b, this algorithm takes O(log(n))
	 * @param a a sorted array
	 * @param b a sorted array
	 * @param k the k-th smallest
	 */
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
			/*
			 //For test purpose
			 System.out.printf("----------------------------------------------\n");
			 System.out.printf("A %s \t\t B %s\n", aRange, bRange);
			 System.out.printf("A %d[%d] \t\t B %d[%d]\n",a[aRange.getMiddle()],aRange.getMiddle(), b[bRange.getMiddle()],bRange.getMiddle());
			*/
			
			//Get the middle element of the array
			int aMid = aRange.getMiddle();
			int bMid = bRange.getMiddle();
			
			//Compare the element and calculate the rank boundary.
			if (a[aMid] < b[bMid]) {
				aBound = new Boundary(aMid, bMid + aMid);
				bBound = new Boundary(aBound.getUpper() + 1, length + bMid);
			} else {
				bBound = new Boundary(bMid, aMid + bMid);
				aBound = new Boundary(bBound.getUpper() + 1, length + aMid);
			}
			
			/*
			//For test purpose
			System.out.printf("A %s \t\t B %s\n", aBound, bBound);
			*/
			
			// Adjust Range, search in a smaller range
			boolean acutleft = true;
			boolean acutright = true;
			// if the range is not in the boundary
			if (aBound.leftBounded(k)) {
				acutright = false;
			}
			if (aBound.rightBounded(k)) {
				acutleft = false;
			}
			// then feel free to cut it off!!!
			if (acutleft){
				aRange.cutLeft(aMid, !aBound.inBound(k));
			}
			if (acutright){
				aRange.cutRight(aMid, !aBound.inBound(k));
			}

			// same here for the other array
			boolean bcutleft = true;
			boolean bcutright = true;
			if (bBound.leftBounded(k)) {
				bcutright = false;
			}
			if (bBound.rightBounded(k)) {
				bcutleft = false;
			}
			if (bcutleft){
				bRange.cutLeft(bMid, !bBound.inBound(k));				
			}
			if (bcutright){
				bRange.cutRight(bMid, !bBound.inBound(k));
			}

			// If one Range is empty, that means the k-th element is not in that array.
			// So it is in the other array, then we can print out the result
			if (aRange.isEmpty()) {
				System.out.printf("The result is %2d in array-B[%2d]\n", b[bMid], bMid);
				return;
			}
			if (bRange.isEmpty()) {
				System.out.printf("The result is %2d at array-A[%2d]\n", a[aMid], aMid);
				return;
			}

		}

	}

	/**
	 * The operation range as I mentioned in the PDF. the k-th smallest element can only be in this range.
	 */
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

		/**
		 * Check if the operation range is empty
		 * @return true if it is empty, false if it is not.
		 */
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

		/**
		 * a method to make the range smaller
		 * @param the index for the middle element in the operation range
		 * @param removeMiddle true if you want to remove the middle point
		 */
		public void cutLeft(int middle, boolean removeMiddle) {
			if (removeMiddle) {
				this.left = middle + 1;
			} else {
				this.left = middle;
			}
		}

		/**
		 * a method that makes the range smaller
		 * @param middle the index for the middle element in the operation range
		 * @param removeMiddle true if you want to remove the middle point
		 */
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

		/**
		 * Check if the variable rank is in (-infinity, upper)
		 * @param rank
		 * @return
		 */
		public boolean rightBounded(int rank) {
			if (rank < this.upper) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * Check if the variable rank is in (lower, +infinity)
		 * @param rank
		 * @return
		 */
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

	// Test main() method
	public static void main(String[] args) {

		int s[] = new int[] { 3, 5, 9, 15, 27, 33, 35, 41, 57, 65 };
		int t[] = new int[] { 2, 16, 18, 42, 44, 46, 48, 50, 52, 54 };
		
		System.out.printf("S = %s\n", arrayToString(s));
		System.out.printf("T = %s\n", arrayToString(t));
		
		System.out.println("Find the 6th smallest");
		findSmallest(s, t, 6);
		
		System.out.println("Find the 10th smallest");
		findSmallest(s, t, 10);
		
		System.out.println("Find Median");
		findMedian(s, t);
		
		System.out.println("Find the smallest");
		findSmallest(s, t, 1);
		
		
		
		System.out.println("\n\nFew more examples");
		s = new int[] { 3, 12, 18, 20, 28, 37, 41, 44, 45, 48 };
		t = new int[] { 4, 5, 6, 7, 8, 13, 15, 21, 23, 27 };
		System.out.printf("S = %s\n", arrayToString(s));
		System.out.printf("T = %s\n", arrayToString(t));

		System.out.println("Find the 7th smallest");
		findSmallest(s, t, 7);
		
		System.out.println("Find the 10th smallest");
		findSmallest(s, t, 10);

		System.out.println("Find the 14th smallest");
		findSmallest(s, t, 14);
		
		System.out.println("Find the 20th smallest");
		findSmallest(s, t, 20);
		
		

		System.out.println("\n\nFew more examples");
		s = new int[] { 1, 2, 3, 4, 5, 6 };
		t = new int[] { 7, 8, 9, 10, 11, 12 };
		System.out.printf("S = %s\n", arrayToString(s));
		System.out.printf("T = %s\n", arrayToString(t));

		System.out.println("Find the 7th smallest");
		findSmallest(s, t, 7);
		
		System.out.println("Find the 10th smallest");
		findSmallest(s, t, 10);
	}

}
