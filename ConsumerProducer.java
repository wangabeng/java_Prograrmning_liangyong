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
		// 创建2个线程池
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		executor.isShutdown();

	}
	
	// 生产者线程
	private static class ProducerTask implements Runnable {
		public void run () {
			try {
				int i = 0;
				while(true) {
					System.out.println("生产者写入：" + i);
					buffer.write(i++); 
					Thread.sleep((int)(Math.random() * 10000));
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	// 消费者线程
		private static class ConsumerTask implements Runnable {
			public void run () {
				try {
					while(true) {
						System.out.println("消费者读入：" + buffer.read());
						
						Thread.sleep((int)(Math.random() * 10000));
					}
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	
	// 生产者消费者类
	private static class Buffer {
		private static final int CAPACITY = 1; // buffer size
		
		LinkedList<Integer> queue = new LinkedList<>();
		
		// created lock
		private static Lock lock = new ReentrantLock();
		
		// create two conditions
		private static Condition notEmpty = lock.newCondition();
		private static Condition notFull = lock.newCondition();
		
		// 写
		public void write (int value) {
			lock.lock();
			try {
				while (queue.size() == CAPACITY) { // 满了
					System.out.println("Wait for notFull condition");
					notFull.await();
				}
				// 如果未满 有空间
				queue.offer(value);
				// 此时有内容了 就唤醒删除等待线程
				notEmpty.signal();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		
		// 读
		public int read () {
			int value = 0;
			lock.lock();
			
			try {
				while (queue.isEmpty()) { // 缓冲区为空
					System.out.println("缓冲区为空 wait for notEmpty condition");
					notEmpty.await();
					
				}
				// 如果不为空
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
