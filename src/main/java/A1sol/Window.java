/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 1, Problem 3: Window.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/

package A1sol;

/**
 * This class is built for windows. And check if windows are overlapping or enclosing.
 * @author chen256
 *
 */
public class Window {

	// Attributes
	protected double left;
	protected double right;
	protected double bottom;
	protected double top;

	// Constructor
	/**
	 * Constructor accept four variables. These fields should satisfy the
	 * 
	 * invariant: left < right and bottom < top
	 * 
	 * @param left
	 *            offset from y axes to the left side of the window
	 * @param right
	 *            offset from y axes to the right side of the window
	 * @param bottom
	 *            offset from x axes to the bottom side of the window
	 * @param top
	 *            offset from x axes to the top side of the window
	 * @throws InvalidWindowException
	 *             throw if do not satisfy: left < right and bottom < top
	 */
	public Window(double left, double right, double bottom, double top) throws InvalidWindowException {
		if (left < right && bottom < top) {
			this.left = left;
			this.right = right;
			this.bottom = bottom;
			this.top = top;
		} else {
			throw new InvalidWindowException();
		}
	}

	// Getters and Setters
	/**
	 * getLeft() simply returns the offset from y axes to the left side of the
	 * window
	 * 
	 * @return offset from y axes to the left side of the window
	 */
	public double getLeft() {
		return left;
	}

	/**
	 * getRight() simply returns the offset from y axes to the right side of the
	 * window
	 * 
	 * @return offset from y axes to the right side of the window
	 */
	public double getRight() {
		return right;
	}

	/**
	 * getBottom() simply returns the offset from x axes to the bottom side of
	 * the window
	 * 
	 * @return offset from x axes to the bottom side of the window
	 */
	public double getBottom() {
		return bottom;
	}

	/**
	 * getTop() simply returns the offset from x axes to the top side of the
	 * window
	 * 
	 * @return offset from x axes to the top side of the window
	 */
	public double getTop() {
		return top;
	}

	/**
	 * setLeft() sets a new offset from y axes to the left side of the window
	 * 
	 * @param left
	 *            offset from y axes to the left side of the window
	 * @throws InvalidWindowException
	 *             throw if the parameter does not satisfy the invariant
	 */
	public void setLeft(double left) throws InvalidWindowException {
		if (left < this.right) {
			this.left = left;
		} else {
			throw new InvalidWindowException();
		}
	}

	/**
	 * setRight() sets a new offset from y axes to the right side of the window
	 * 
	 * @param right
	 *            offset from y axes to the right side of the window
	 * @throws InvalidWindowException
	 *             throw if the parameter does not satisfy the invariant
	 */
	public void setRight(double right) throws InvalidWindowException {
		if (this.left < right) {
			this.right = right;
		} else {
			throw new InvalidWindowException();
		}
	}

	/**
	 * setBottom() sets a new offset from x axes to the bottom side of the
	 * window
	 * 
	 * @param bottom
	 *            offset from x axes to the bottom side of the window
	 * @throws InvalidWindowException
	 *             throw if the parameter does not satisfy the invariant
	 */
	public void setBottom(double bottom) throws InvalidWindowException {
		if (bottom < this.top) {
			this.bottom = bottom;
		} else {
			throw new InvalidWindowException();
		}
	}

	/**
	 * setTop() sets a new offset from x axes to the top side of the window
	 * 
	 * @param top
	 *            offset from x axes to the top side of the window
	 * @throws InvalidWindowException
	 *             throw if the parameter does not satisfy the invariant
	 */
	public void setTop(double top) throws InvalidWindowException {
		if (this.bottom < top) {
			this.top = top;
		} else {
			throw new InvalidWindowException();
		}
	}

	// Non-static Methods
	/**
	 * encloses() takes a window object, check if this object is enclosed by the
	 * current object.
	 * 
	 * We say w0 encloses w1 if no part of w1 is outside w0. This relationship
	 * is NOT symmetric.
	 * 
	 * @param w
	 *            another window
	 * @return true if the this window encloses the parameter w, otherwise false
	 */
	public boolean encloses(Window w) {
		// All the line should be inside the window
		if ((w.getRight() <= this.right) && (w.getLeft() >= this.left) && (w.getTop() <= this.top) && (w.getBottom() >= this.bottom)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * overlaps() takes a window object, check if this object and the current
	 * object overlap.
	 * 
	 * We say two windows overlap if they have a common interior point. This
	 * relationship is symmetric. That means "A overlaps B" <=> "B overlaps A"
	 * 
	 * @param w
	 *            another window
	 * @return true if the this window overlaps the parameter w (this
	 *         relationship is symmetric), otherwise false
	 */
	public boolean overlaps(Window w) {
		// If one of the condition is true, then two window do not overlap.
		// Just touching boundaries is not enough, therefore,
		// use smaller/greater or equal than
		if ((w.getRight() <= this.left) || (w.getLeft() >= this.right) || (w.getTop() <= this.bottom) || (w.getBottom() >= this.top)) {
			return false;
		} else {
			return true;
		}
	}

	// Static Methods

	/**
	 * enclosureCount() counts how many windows are enclosure in the array given. 
	 * 
	 * @param windows
	 *            array of windows
	 * @return the number of (ordered) enclosing pairs of windows in the input
	 *         array windows.
	 */
	public static int enclosureCount(Window[] windows) {
		int count = 0;

		// should go through 0 to length-1
		// I don't want to create extra variable and the loop will not do
		// anything if it reach the end
		for (int i = 0; i < windows.length; i++) {
			for (int j = i + 1; j < windows.length; j++) {
				// Ordered: check two ways
				if (windows[i].encloses(windows[j])) {
					count++;
				}
				if (windows[j].encloses(windows[i])) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * overlapCount() counts how many windows are overlapping in the array given.
	 * 
	 * @param windows
	 *            array of windows
	 * @return the number of (unordered) overlapping pairs of windows in the
	 *         input array windows.
	 */
	public static int overlapCount(Window[] windows) {
		int count = 0;

		// should go through 0 to length-1
		// I don't want to create extra variable and the loop will not do
		// anything if it reach the end
		for (int i = 0; i < windows.length; i++) {
			for (int j = i + 1; j < windows.length; j++) {
				// Unordered: check one way
				if (windows[i].overlaps(windows[j])) {
					count++;
				}
			}
		}
		return count;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Window [left=%s, right=%s, bottom=%s, top=%s]", left, right, bottom, top);
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bottom);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(left);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(right);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(top);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
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
		Window other = (Window) obj;
		if (Double.doubleToLongBits(bottom) != Double.doubleToLongBits(other.bottom))
			return false;
		if (Double.doubleToLongBits(left) != Double.doubleToLongBits(other.left))
			return false;
		if (Double.doubleToLongBits(right) != Double.doubleToLongBits(other.right))
			return false;
		if (Double.doubleToLongBits(top) != Double.doubleToLongBits(other.top))
			return false;
		return true;
	}

	/**
	 * main() runs test cases on your all the methods in this class. Prints
	 * summary information on basic operations and halts with an error (and a
	 * stack trace) if any of the tests fail.
	 */
	public static void main(String[] args) {
		try {
			Window w1 = new Window(1, 5, 3, 4);
			System.out.printf("w1 => %s%n", w1);
			Window w2 = new Window(0, 8, 2, 6);
			System.out.printf("w2 => %s%n", w2);
			Window w3 = new Window(3, 6, 1, 7);
			System.out.printf("w3 => %s%n", w3);
			Window w4 = new Window(5, 8, 8, 9);
			System.out.printf("w4 => %s%n", w4);
			Window[] array = { w1, w2, w3, w4 };
			
			boolean t1 = w1.overlaps(w2);
			System.out.printf("w1.overlaps(w2) => %b%n", t1);
			TestHelper.verify(t1 == true, "ERROR!");
			
			boolean t2 = w2.overlaps(w1);
			System.out.printf("w2.overlaps(w1) => %b%n", t2);
			TestHelper.verify(t2 == true, "ERROR!");
			
			boolean t3 = w1.encloses(w2);
			System.out.printf("w1.encloses(w2) => %b%n", t3);
			TestHelper.verify(t3 == false, "ERROR!");
			
			boolean t4 = w2.encloses(w1);
			System.out.printf("w2.encloses(w1) => %b%n", t4);
			TestHelper.verify(t4 == true, "ERROR!");
			
			boolean t5 = w1.encloses(w4);
			System.out.printf("w1.encloses(w4) => %b%n", t5);
			TestHelper.verify(t5 == false, "ERROR!");
			
			boolean t6 = w4.encloses(w1);
			System.out.printf("w4.encloses(w1) => %b%n", t6);
			TestHelper.verify(t6 == false, "ERROR!");
			
			boolean t7 = w3.overlaps(w2);
			System.out.printf("w3.overlaps(w2) => %b%n", t7);
			TestHelper.verify(t7 == true, "ERROR!");
			
			boolean t8 = w3.encloses(w4);
			System.out.printf("w3.encloses(w4) => %b%n", t8);
			TestHelper.verify(t8 == false, "ERROR!");
			
			boolean t9 = w3.encloses(w1);
			System.out.printf("w3.encloses(w1) => %b%n", t9);
			TestHelper.verify(t9 == false, "ERROR!");
			
			int t10 = Window.enclosureCount(array);
			System.out.printf("Window.enclosureCount(array) => %d%n", t10);
			TestHelper.verify(t10 == 1, "ERROR!");
			
			int t11 = Window.overlapCount(array);
			System.out.printf("Window.overlapCount(array) => %d%n", t11);
			TestHelper.verify(t11 == 3, "ERROR!");
			
			System.out.println("This final test will give an exception because I set the window's left larger than its right.");
			System.out.printf("w1.getRight() => %f%n", w1.getRight());
			System.out.printf("w1.setLeft(100);%n");
			
			w1.setLeft(100);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
