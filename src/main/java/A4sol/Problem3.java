package A4sol;

public class Problem3 {

	public static class Election {
		public void vote(int candidateId){
			
			
		}
	}
	
	public static void main(String[] args) {
		int[] tickets = new int[] { 1, 1, 1, 2, 3, 5, 555, 555, 666, 555, 555, 888, 888, 888, 888, 888, 888, 888, 555, 1, 2, 333, 333, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
		Election e = new Election();
		for (int i = 0; i < tickets.length; i++){
			e.vote(tickets[i]);
		}
	}

}
