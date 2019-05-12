package test.haha;


import java.util.ArrayList;
import java.util.Date;

public class Haha {

	public static void main(String[] args) {
		/*
		 * ArrayList<Date> dates = new ArrayList<>(); dates.add(new Date());
		 * System.out.println(dates);
		 */
		
		//
		GenericStack<String> stringStack = new GenericStack<>();
		stringStack.push("122");
		stringStack.push("333");
		System.out.println(stringStack.get(0));

	}
}

class GenericStack<E> {
	private ArrayList<E> lists = new ArrayList<>();
	public int getSize () {
		return lists.size();
	}
	public void push (E o) {
		lists.add(o);
	}
	public E get (int index) {
		return lists.get(index);
	}
}
