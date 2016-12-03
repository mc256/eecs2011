package A4sol;

public class Problem3 {

	public static class Election {
		public void vote(int candidateId) {

		}
	}

	public static class SimpleAVLTree<T extends Comparable<? super T>> {
		Node<T> root;

		// Insertion
		public void insert(T data) {
			this.root = insertNode(root, data);
		}

		private Node<T> insertNode(Node<T> n, T data) {
			if (n == null) {
				return new Node<T>(data, null, null);
			} else {
				if (n.data.compareTo(data) > 0) {
					n.left = insertNode(n.left, data);
				} else {
					n.right = insertNode(n.right, data);
				}
				
				//Update height
				Node.computeHeight(n);
				
				// Balance Tree
				rotate(n);

				return n;
			}
		}

		// Rotation
		public void rotate(Node<T> n) {
			int leftHeight = Node.getHeight(n.left);
			int rightHeight = Node.getHeight(n.right);
			int balance = leftHeight - rightHeight;
			// Check if needed to rotate
			if (balance < -1 || balance > 1) {
				// Four situations
				if (balance > 0) {
					int sleftHeight = Node.getHeight(n.left.left);
					int srightHeight = Node.getHeight(n.left.right);
					if (sleftHeight > srightHeight) {
						// LL
						Node<T> y = n.left;
						Node<T> x = n.left.left;
						T v1 = n.data;
						T v2 = y.data;
						Node<T> subtreeC = y.right;
						Node<T> subtreeD = n.right;

						n.data = v2;
						y.data = v1;
						y.left = subtreeC;
						y.right = subtreeD;
						n.left = x;
						n.right = y;
						
						// Recompute the height
						Node.computeHeight(x);
						Node.computeHeight(y);
						Node.computeHeight(n);
					} else {
						// LR
						Node<T> y = n.left;
						Node<T> x = n.left.right;
						T v1 = n.data;
						T v3 = x.data;
						Node<T> subtreeB = x.left;
						Node<T> subtreeC = x.right;
						Node<T> subtreeD = n.right;

						n.data = v3;
						x.data = v1;
						y.right = subtreeB;
						x.left = subtreeC;
						x.right = subtreeD;
						n.right = x;
						
						// Recompute the height
						Node.computeHeight(x);
						Node.computeHeight(y);
						Node.computeHeight(n);
					}
				} else {
					int srightHeight = Node.getHeight(n.right.right);
					int sleftHeight = Node.getHeight(n.right.left);
					if (sleftHeight < srightHeight) {
						// RR
						Node<T> y = n.right;
						Node<T> x = n.right.right;
						T v1 = n.data;
						T v2 = y.data;
						Node<T> subtreeC = y.left;
						Node<T> subtreeD = n.left;

						n.data = v2;
						y.data = v1;
						y.right = subtreeC;
						y.left = subtreeD;
						n.right = x;
						n.left = y;

						// Recompute the height
						Node.computeHeight(x);
						Node.computeHeight(y);
						Node.computeHeight(n);
					} else {
						// RL
						Node<T> y = n.right;
						Node<T> x = n.right.left;
						T v1 = n.data;
						T v3 = x.data;
						Node<T> subtreeB = x.right;
						Node<T> subtreeC = x.left;
						Node<T> subtreeD = n.left;

						n.data = v3;
						x.data = v1;
						y.left = subtreeB;
						x.right = subtreeC;
						x.left = subtreeD;
						n.left = x;

						// Recompute the height
						Node.computeHeight(x);
						Node.computeHeight(y);
						Node.computeHeight(n);
					}
				}
			}
		}
	}

	public static class Node<T extends Comparable<? super T>> {
		public T data;
		public int aux;
		public Node<T> left;
		public Node<T> right;

		public Node(T data, Node<T> left, Node<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.aux = 1;
		}

		public static int getHeight(Node<?> n) {
			return (n == null ? 0 : n.aux);
		}

		public static void computeHeight(Node<?> n){
			int left = getHeight(n.left);
			int right = getHeight(n.right);
			n.aux = (left > right ? left : right) + 1;			
		}
		
		@Override
		public String toString() {
			return this.data.toString();
		}
	}

	public static void main(String[] args) {
		// Page 569
		SimpleAVLTree<Integer> tree = new SimpleAVLTree<Integer>();

		tree.insert(6);
		tree.insert(7);
		tree.insert(8);
		tree.insert(9);
		tree.insert(10);
		
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);

		
		
		
		int[] tickets = new int[] { 1, 1, 1, 2, 3, 5, 555, 555, 666, 555, 555, 888, 888, 888, 888, 888, 888, 888, 555, 1, 2, 333, 333, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
		Election e = new Election();
		for (int i = 0; i < tickets.length; i++) {
			e.vote(tickets[i]);
		}
	}

}
