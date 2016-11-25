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
		// Declare precondition :
		// a.length == b.length
		// 1 <= k <= 2n
		// two arrays are equal length
		int aLeft = 0;
		int aRight = a.length - 1;
		int bLeft = 0;
		int bRight = b.length - 1;
		k--;

		// First find the corresponding position and confirm the starting point
		while (true) {
			System.out.println("--------------------------------------");

			int aMid = (aLeft + aRight) / 2;
			int bMid = (bLeft + bRight) / 2;

			System.out.printf("A - {%3d,%3d}  - %3d[%3d]\n", aLeft, aRight, aMid, a[aMid]);
			System.out.printf("B - {%3d,%3d}  - %3d[%3d]\n", bLeft, bRight, bMid, b[bMid]);

			if (a[aMid] < b[bMid]) {
				// handle A
				int aLower = aMid;
				int aUpper = aMid + bMid - 1;
				int bLower = aUpper + 1;
				int bUpper = a.length + bMid;
				System.out.printf("A - {%3d,%3d}\n", aLower, aUpper);
				if (k < aLower) {
					aRight = aMid - 1;
				} else if (k > aUpper) {
					aLeft = aMid + 1;
				} else {
					aRight = aMid;
				}
				if (k < bLower){
					bRight = bMid - 1;
				}else if (k > bUpper){
					bLeft = bMid + 1;
				}else{
					bLeft = bMid;
				}
			} else {
				// handle B
				int bLower = bMid;
				int bUpper = bMid + aMid - 1;
				int aLower = bUpper + 1;
				int aUpper = b.length + aMid;
				System.out.printf("B - {%3d,%3d}\n", bLower, bUpper);
				if (k < bLower) {
					bRight = bMid - 1;
				} else if (k > bUpper) {
					bLeft = bMid + 1;
				} else {
					bRight = bMid;
				}
				if (k < aLower){
					aRight = aMid - 1;
				}else if (k > aUpper){
					aLeft = aMid + 1;
				}else{
					aLeft = aMid;
				}
			}

			if (aLeft == aRight) {
				System.out.printf("A:index%d - element%d\n", aLeft, a[aLeft]);
				return;
			}
			if (bLeft == bRight) {
				System.out.printf("B:index%d - element%d\n", bLeft, b[bLeft]);
				return;
			}
		}

	}

	public static void main(String[] args) {

		// int s[] = new int[] { 3, 12, 18, 20, 28, 37, 41, 44, 45, 48 };
		// int t[] = new int[] { 4, 5, 6, 7, 8, 13, 15, 21, 23, 27 };
		int s[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		int t[] = new int[] { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

		// generateSortedArray(s);
		// generateSortedArray(t);

		System.out.printf("S = %s\n", arrayToString(s));
		System.out.printf("T = %s\n", arrayToString(t));

		findLargest(s, t, 1);
		findLargest(s, t, 2);
		findLargest(s, t, 3);
		findLargest(s, t, 4);
		findLargest(s, t, 5);
	}

}
