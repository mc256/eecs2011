package A2sol;

import java.util.Stack;

public class AugmentedStack<T extends Comparable<? super T>> {

	private Stack<T> data = new Stack<T>();
	private Stack<T> min = new Stack<T>();
	
	public void push(T x){
		data.push(x);
		if (min.isEmpty()) {
			min.push(x);
		}else{
			if (min.peek().compareTo(x) >= 0){
				min.push(x);
			}
		}
	}
	
	public T pop(){
		if (data.isEmpty()){
			return null;
		}else{
			T result = data.pop();
			if (min.peek().equals(result)) {
				min.pop();
			}
			return result;
		}
	}
	
	public T getMin(){
		if (min.isEmpty()){
			return null;
		}else{
			return min.peek();
		}
	}
	
	public boolean isEmpty(){
		return data.isEmpty();
	}
	
	public T top(){
		if (min.isEmpty()){
			return null;
		}else{
			return data.peek();
		}
	}
	
	public static void main(String[] args) {
		AugmentedStack<Integer> s = new AugmentedStack<Integer>();
		s.push(5);
		System.out.println(s.top());
		System.out.println(s.getMin());
		s.push(3);
		System.out.println(s.top());
		System.out.println(s.getMin());

		s.push(3);
		System.out.println(s.top());
		System.out.println(s.getMin());
		s.push(1);
		System.out.println(s.top());
		System.out.println(s.getMin());
		
		s.pop();
		s.pop();
		s.pop();
		System.out.println(s.getMin());
		
	}

}
