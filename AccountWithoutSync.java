package test.haha;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountWithoutSync {
	
	private static Account account = new Account();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newCachedThreadPool();
		
		
		  for (int i = 0; i < 100; i++) { executor.execute(new AddAPennyTask()); }
		  
		  executor.shutdown();
		  
		  while (!executor.isTerminated()) {}
		  
		  System.out.print("What is balcance?" + account.getBalance());
		 
	}

	private static class AddAPennyTask implements Runnable {
		public void run () {
			account.deposit(1);
		}
	}

	private static class Account {
		private int balance = 0;
		
		public int getBalance () {
			return balance;
		}
		
		public  void deposit (int amount) {
			synchronized (this) {
				int newBalance = balance + amount;
				
				try {
					Thread.sleep(5);
				} catch (InterruptedException ex) {}
				
				balance = newBalance;
			}
			
					
		}
	}
	
}

