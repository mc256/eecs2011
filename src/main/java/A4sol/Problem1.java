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

			boolean aTakeLeft, aTakeMid, aTakeRight;
			boolean bTakeLeft, bTakeMid, bTakeRight;
			aTakeLeft = aTakeMid = aTakeRight = false;
			bTakeLeft = bTakeMid = bTakeRight = false;

			if (a[aMid] < b[bMid]) {
				// Possible Range
				int aLower = aMid;
				int aUpper = aMid + bMid;
				int bLower = aUpper + 1;
				int bUpper = a.length + bMid;

				// Choose Partitions
				if (k >= aLower && k <= aUpper) {
					aTakeMid = true;
				}
				if (k < aUpper) {
					aTakeLeft = true;
				}
				if (k > aLower) {
					aTakeRight = true;
				}

				if (k >= bLower && k <= bUpper) {
					bTakeMid = true;
				}
				if (k < bUpper) {
					bTakeLeft = true;
				}
				if (k > bLower) {
					bTakeRight = true;
				}
				System.out.printf("A %s%s%s - {%3d,%3d}\n", (aTakeLeft ? "o" : "x"), (aTakeMid ? "o" : "x"), (aTakeRight ? "o" : "x"), aLower, aUpper);
				System.out.printf("B %s%s%s - {%3d,%3d}\n", (bTakeLeft ? "o" : "x"), (bTakeMid ? "o" : "x"), (bTakeRight ? "o" : "x"), bLower, bUpper);

				// Adjust Range
				if (!aTakeRight) {
					aRight = aMid;
					if (!aTakeMid) {
						aRight = aMid - 1;
					}
				}
				if (!aTakeLeft) {
					aLeft = aMid;
					if (!aTakeMid) {
						aLeft = aMid + 1;
					}
				}

				if (!bTakeRight) {
					bRight = bMid;
					if (!bTakeMid) {
						bRight = bMid - 1;
					}
				}
				if (!bTakeLeft) {
					bLeft = bMid;
					if (!bTakeMid) {
						bLeft = bMid + 1;
					}
				}

			} else {
				// Possible Range
				int bLower = bMid;
				int bUpper = bMid + aMid;
				int aLower = bUpper + 1;
				int aUpper = b.length + aMid;

				// Choose Partitions
				if (k >= aLower && k <= aUpper) {
					aTakeMid = true;
				}
				if (k < aUpper) {
					aTakeLeft = true;
				}
				if (k > aLower) {
					aTakeRight = true;
				}

				if (k >= bLower && k <= bUpper) {
					bTakeMid = true;
				}
				if (k < bUpper) {
					bTakeLeft = true;
				}
				if (k > bLower) {
					bTakeRight = true;
				}
				System.out.printf("B %s%s%s - {%3d,%3d}\n", (bTakeLeft ? "o" : "x"), (bTakeMid ? "o" : "x"), (bTakeRight ? "o" : "x"), bLower, bUpper);
				System.out.printf("A %s%s%s - {%3d,%3d}\n", (aTakeLeft ? "o" : "x"), (aTakeMid ? "o" : "x"), (aTakeRight ? "o" : "x"), aLower, aUpper);

				// adjust Range
				if (!bTakeRight) {
					bRight = bMid;
					if (!bTakeMid) {
						bRight = bMid - 1;
					}
				}
				if (!bTakeLeft) {
					bLeft = bMid;
					if (!bTakeMid) {
						bLeft = bMid + 1;
					}
				}

				if (!aTakeRight) {
					aRight = aMid;
					if (!aTakeMid) {
						aRight = aMid - 1;
					}
				}
				if (!aTakeLeft) {
					aLeft = aMid;
					if (!aTakeMid) {
						aLeft = aMid + 1;
					}
				}
			}

			if (aLeft == aRight) {
				if (aTakeMid) {
					System.out.printf("A:index[%d] - element[%d]\n", aLeft, a[aLeft]);
					return;
				}
				if (bTakeMid) {
					System.out.printf("B:index[%d] - element[%d]\n", bLeft, b[bLeft]);
					return;
				}

			}
			if (bLeft == bRight) {
				if (aTakeMid) {
					System.out.printf("A:index[%d] - element[%d]\n", aLeft, a[aLeft]);
					return;
				}
				if (bTakeMid) {
					System.out.printf("B:index[%d] - element[%d]\n", bLeft, b[bLeft]);
					return;
				}
			}
		}

	}

	public static void main(String[] args) {

		//int s[] = new int[] { 3, 12, 18, 20, 28, 37, 41, 44, 45, 48 };
		//int t[] = new int[] { 4, 5, 6, 7, 8, 13, 15, 21, 23, 27 };

		int s[] = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int t[] = new int[] { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

		// generateSortedArray(s);
		// generateSortedArray(t);

		System.out.printf("S = %s\n", arrayToString(s));
		System.out.printf("T = %s\n", arrayToString(t));

		findLargest(s, t, 5);
	}

}
