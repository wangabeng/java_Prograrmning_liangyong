package test.haha;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.modelmbean.DescriptorSupport;

public class ThreadCooperation {
	// 创建account
	private static Account account = new Account();

	public static void main(String[] args) {
		// create thread pool width two threads
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new DepositTask());
		executor.execute(new WithdrawTask());
		executor.shutdown();
		
	}
	
	// 存款线程类
	public static class DepositTask implements Runnable {
		@Override
		public void run () {
			try {
				while (true) {
					account.deposit((int)(Math.random() * 10 + 1));
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
	// 取款线程
	public static class WithdrawTask implements Runnable {
		@Override
		public void run () {
			while (true) {
				account.widthdraw((int)(Math.random() * 10 + 1));
			}
		}
	}
	
	
	// account类
	private static class Account {
		// Create new Lock
		private static Lock lock = new ReentrantLock();

		// create a condition
		private static Condition newDeposit = lock.newCondition();

		private int balance = 0; // 余额

		public int getBalance() {
			return balance;
		}

		// 取款
		public void widthdraw(int amount) {
			lock.lock(); // acquire the lock

			// 余额不足
			try {
				while (balance < amount) {
					System.out.println("widthdraw余额不足 余额为： " + getBalance() + "但是你要取：" + amount);
					System.out.println("widthdraw余额不足 等待存款");
					newDeposit.await();
				}
				balance -= amount;
				System.out.println("widthdraw此次取款金额为：" + amount);
				System.out.println("widthdraw可取款金额为：" + getBalance());
				System.out.println("widthdraw取款结束--------------------------");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

		}

		// 存款
		public void deposit (int amount) {
			lock.lock();
			
			
			try {
				balance += amount;
				System.out.println("deposit此次存款：" + amount);
				System.out.println("deposit此次存款后当前总金额为：" + getBalance());
				
				// 唤醒所有等待取款线程
				newDeposit.signalAll();
			} finally {
				lock.unlock();
			}
			
		}
	}

}
