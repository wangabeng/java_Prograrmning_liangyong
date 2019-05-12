package test.haha;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TestQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> q = new LinkedList<Integer>();
		
		Random r = new Random();
		
		for (int i = 0; i < 10; i++) {
			q.add(r.nextInt(100));
		}
		System.out.println(q);
	}

}
