
package test.haha;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import test.haha.PrintChar;
import test.haha.PrintNum;

public class ExecutorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ExecutorService executor = Executors.newFixedThreadPool(5);
		ExecutorService executor = Executors.newCachedThreadPool();
		
		executor.execute(new PrintChar('a', 100));
		executor.execute(new PrintChar('b', 100));
		executor.execute(new PrintNum(100));
		
		executor.shutdown();
	}

}

