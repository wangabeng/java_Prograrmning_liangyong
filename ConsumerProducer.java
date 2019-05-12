package test.haha;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {
	private static Buffer buffer = new Buffer();

	public static void main(String[] args) {
		// ����2���̳߳�
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		executor.isShutdown();

	}
	
	// �������߳�
	private static class ProducerTask implements Runnable {
		public void run () {
			try {
				int i = 0;
				while(true) {
					System.out.println("������д�룺" + i);
					buffer.write(i++); 
					Thread.sleep((int)(Math.random() * 10000));
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	// �������߳�
		private static class ConsumerTask implements Runnable {
			public void run () {
				try {
					while(true) {
						System.out.println("�����߶��룺" + buffer.read());
						
						Thread.sleep((int)(Math.random() * 10000));
					}
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	
	// ��������������
	private static class Buffer {
		private static final int CAPACITY = 1; // buffer size
		
		LinkedList<Integer> queue = new LinkedList<>();
		
		// created lock
		private static Lock lock = new ReentrantLock();
		
		// create two conditions
		private static Condition notEmpty = lock.newCondition();
		private static Condition notFull = lock.newCondition();
		
		// д
		public void write (int value) {
			lock.lock();
			try {
				while (queue.size() == CAPACITY) { // ����
					System.out.println("Wait for notFull condition");
					notFull.await();
				}
				// ���δ�� �пռ�
				queue.offer(value);
				// ��ʱ�������� �ͻ���ɾ���ȴ��߳�
				notEmpty.signal();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		
		// ��
		public int read () {
			int value = 0;
			lock.lock();
			
			try {
				while (queue.isEmpty()) { // ������Ϊ��
					System.out.println("������Ϊ�� wait for notEmpty condition");
					notEmpty.await();
					
				}
				// �����Ϊ��
				value = queue.remove();
				notFull.signal();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
				return value;
			}
		}
	}

}
