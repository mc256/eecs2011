/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 4, Problem 3: Problem3.java
 * Student Name: Jun Lin Chen
 * Student cse account: chen256
 * Student ID number: 214533111
 **********************************************************/
package A4sol;

import java.util.Random;

public class Problem3 {

	public static class Election extends SimpleAVLTree<Candidate> {

		Candidate winner = null;

		public void vote(Candidate data){
			this.insert(data);
		}
		
		// Insertion
		@Override
		public void insert(Candidate data) {
			this.root = insertNode(root, data);
		}

		@Override
		public Node<Candidate> insertNode(Node<Candidate> n, Candidate data) {
			if (n == null) {
				// new candidate
				// but we still need to check if he is the winner
				this.updateWinner(data);
				return new Node<Candidate>(data, null, null);
			} else {
				int compare = n.data.compareTo(data);
				if (compare == 0) {
					// if the candidate is existing in the AVL tree,
					// increase the number of vote by one
					n.data.votes ++;
					// check if he has the highest votes
					this.updateWinner(n.data);
				} else if (compare > 0) {
					n.left = insertNode(n.left, data);
				} else {
					n.right = insertNode(n.right, data);
				}

				// Update height
				Node.computeHeight(n);

				// Balance Tree
				rotate(n);

				return n;
			}
		}
		
		/**
		 * Compare with the current winner, see who has higher votes
		 * @param possibleWinner
		 */
		public void updateWinner(Candidate possibleWinner){
			if (winner == null){
				this.winner = possibleWinner;
			}else{
				if (this.winner.votes < possibleWinner.votes){
					this.winner = possibleWinner;
				}
			}
		}
		
		public Candidate getWinner(){
			return this.winner;
		}

	}

	public static class Candidate implements Comparable<Candidate> {

		public Integer id;
		public String candidateName;
		public Integer votes;

		public Candidate(Integer id, String name) {
			this.id = id;
			this.candidateName = name;
			this.votes = 1;
		}

		public Candidate(Integer id) {
			this(id, null);
		}

		/**
		 * The compareTo() method here compare the candidate ID
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Candidate o) {
			return this.id.compareTo(o.id);
		}
		
		@Override
		public String toString(){
			if (this.candidateName == null){
				return String.format("Candidate[ID=%10d, Votes=%10d]", this.id, this.votes);
			}
			return String.format("Candidate[ID=%10d, Votes=%10d, %s]", this.id, this.votes, this.candidateName);
		}
		
	}

	public static class SimpleAVLTree<T extends Comparable<? super T>> {
		Node<T> root;

		// Insertion
		public void insert(T data) {
			this.root = insertNode(root, data);
		}

		public Node<T> insertNode(Node<T> n, T data) {
			if (n == null) {
				return new Node<T>(data, null, null);
			} else {
				int compare = n.data.compareTo(data);
				if (compare > 0) {
					n.left = insertNode(n.left, data);
				} else {
					n.right = insertNode(n.right, data);
				}

				// Update height
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

		// Utility
		private String toStringInorder(Node<T> n) {
			StringBuilder sb = new StringBuilder();
			if (n != null) {
				if (n.left != null) {
					sb.append(toStringInorder(n.left));
					sb.append(",");
				}
				sb.append(n.data.toString());
				if (n.right != null) {
					sb.append(",");
					sb.append(toStringInorder(n.right));
				}
			}
			return sb.toString();
		}

		@Override
		public String toString() {
			return this.toStringInorder(this.root);
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

		// Height Calculation
		/**
		 * Get the height of the node
		 * @param n node, it can be null
		 * @return the height of the node, if the node is empty, return 0
		 */
		public static int getHeight(Node<?> n) {
			return (n == null ? 0 : n.aux);
		}

		/**
		 * Recompute the height of the node by using it's children
		 * @param n
		 */
		public static void computeHeight(Node<?> n) {
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
		Random rn = new Random();
		Election e = new Election();

		Candidate c1 = new Candidate(rn.nextInt(999999),"Donald Trump");
		Candidate c2 = new Candidate(rn.nextInt(999999),"Hillary Clinton");
		Candidate c3 = new Candidate(rn.nextInt(999999),"Johson");
		Candidate c4 = new Candidate(rn.nextInt(999999),"Stein");


		e.vote(c1);
		e.vote(c1);
		e.vote(c1);
		e.vote(c1);
		e.vote(c1);
		e.vote(c1);
		e.vote(c4);
		e.vote(c1);
		e.vote(c3);
		e.vote(c2);
		e.vote(c1);
		e.vote(c4);
		e.vote(c1);
		e.vote(c1);
		e.vote(c3);
		e.vote(c2);
		e.vote(c2);
		e.vote(c2);
		e.vote(c2);
		e.vote(c2);
		e.vote(c1);
		e.vote(c1);
		e.vote(c2);
		e.vote(c3);
		e.vote(c4);
		e.vote(c1);
		e.vote(c3);
		e.vote(c1);
		e.vote(c1);
		e.vote(c4);
		e.vote(c2);
		e.vote(c1);
		e.vote(c1);
		e.vote(c1);		
		e.vote(c1);
		e.vote(c1);
		
		System.out.println(e.getWinner());
		System.out.println(e);
	}

}
