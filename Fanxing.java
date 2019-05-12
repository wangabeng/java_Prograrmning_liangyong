package test.haha;

import java.util.ArrayList;

public class Fanxing {

	public static void main(String[] args) {
		new Generic<String>("ABENG");
	}

}

class Generic<E> {
	private E key;
	public Generic (E value) {
		this.key = value;
		System.out.println(this.key);
	}
	public E getKey () {
		return key;
	} 
}
