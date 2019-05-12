package test.haha;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.modelmbean.DescriptorSupport;

public class ThreadCooperation {
	// ����account
	private static Account account = new Account();

	public static void main(String[] args) {
		// create thread pool width two threads
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new DepositTask());
		executor.execute(new WithdrawTask());
		executor.shutdown();
		
	}
	
	// ����߳���
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
	
	// ȡ���߳�
	public static class WithdrawTask implements Runnable {
		@Override
		public void run () {
			while (true) {
				account.widthdraw((int)(Math.random() * 10 + 1));
			}
		}
	}
	
	
	// account��
	private static class Account {
		// Create new Lock
		private static Lock lock = new ReentrantLock();

		// create a condition
		private static Condition newDeposit = lock.newCondition();

		private int balance = 0; // ���

		public int getBalance() {
			return balance;
		}

		// ȡ��
		public void widthdraw(int amount) {
			lock.lock(); // acquire the lock

			// ����
			try {
				while (balance < amount) {
					System.out.println("widthdraw���� ���Ϊ�� " + getBalance() + "������Ҫȡ��" + amount);
					System.out.println("widthdraw���� �ȴ����");
					newDeposit.await();
				}
				balance -= amount;
				System.out.println("widthdraw�˴�ȡ����Ϊ��" + amount);
				System.out.println("widthdraw��ȡ����Ϊ��" + getBalance());
				System.out.println("widthdrawȡ�����--------------------------");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

		}

		// ���
		public void deposit (int amount) {
			lock.lock();
			
			
			try {
				balance += amount;
				System.out.println("deposit�˴δ�" + amount);
				System.out.println("deposit�˴δ���ǰ�ܽ��Ϊ��" + getBalance());
				
				// �������еȴ�ȡ���߳�
				newDeposit.signalAll();
			} finally {
				lock.unlock();
			}
			
		}
	}

}
