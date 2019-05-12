package test.haha;

public class Testthread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintNumber a = new PrintNumber('B', 100);
		PrintNumber2 a2 = new PrintNumber2('D', 1000);
		PrintNumber2 a3 = new PrintNumber2('F', 100);
		
		Thread thread1 = new Thread(a);
		Thread thread2 = new Thread(a2);
		Thread thread3 = new Thread(a3);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
	}

}

class PrintNumber implements Runnable {
	private char charToPrint;
	private int times;
	PrintNumber (char c, int t) {
		charToPrint = c;
		times = t;
	}
	@Override
	public void run () {
		for (int i = 0;i <= times; i++) {
			System.out.print(charToPrint);
		}
	}
}

class PrintNumber2 implements Runnable {
	private char charToPrint2;
	private int times2;
	PrintNumber2 (char c, int t) {
		charToPrint2 = c;
		times2 = t;
	}
	@Override
	public void run () {
		for (int i = 0;i <= times2; i++) {
			System.out.print(charToPrint2);
		}
	}
}

class PrintNumber3 implements Runnable {
	private char charToPrint3;
	private int times3;
	PrintNumber3 (char c, int t) {
		charToPrint3 = c;
		times3 = t;
	}
	@Override
	public void run () {
		for (int i = 0;i <= times3; i++) {
			System.out.print(charToPrint3);
		}
	}
}

