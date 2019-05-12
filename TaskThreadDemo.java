package test.haha;

public class TaskThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable printA = new PrintChar('a', 100);
		Runnable printB = new PrintChar('b', 100);
		Runnable print100 = new PrintNum(100);
		
		Thread thread1 = new Thread(printA);
		Thread thread2 = new Thread(printB);
		Thread thread3 = new Thread(print100);
		
		thread1.start();
		thread2.start();
		thread3.start();
	}

}

class PrintChar implements Runnable {
	private char charToPrint;
	private int times;
	
	public PrintChar (char c, int t) {
		charToPrint = c;
		times = t;
	}
	@Override
	public void run () {
		for (int i = 0; i < times; i++) {
			System.out.print(charToPrint);
		}
	}
}

class PrintNum implements Runnable {
	private int lastNum;
	
	public PrintNum (int n) {
		lastNum = n;
	}
	
	@Override
	public void run () {
		/*
		 * for (int i = 0; i <= lastNum; i++) { System.out.print(" " + i); }
		 */
		/*
		 * Thread thread4 = new Thread(new PrintChar('c', 2000)); thread4.start();
		 * 
		 * try { thread4.join(); for (int i = 0; i <= lastNum; i++) {
		 * System.out.print(" " + i); if(i==30000) { System.out.print("2000¿ªÊ¼");
		 * thread4.join(); }
		 * 
		 * 
		 * } } catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		for (int i = 0; i <= lastNum; i++) {
			System.out.print(" " + i);
		}
		
	}
}
