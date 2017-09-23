package com.yz.javaweb.test;

public class ThreadLocalTest2 implements Runnable {
	private ThreadLocal<String> tl = new ThreadLocal<>();

	int i = 0;

	@Override
	public void run() {
		for (; i < 10; i++) {
			tl.set(Thread.currentThread().getName());

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + ":" + tl.get());
		}
	}

	public static void main(String[] args) {

		ThreadLocalTest2 threadLocalTest2 = new ThreadLocalTest2();

		new Thread(threadLocalTest2, "AAA").start();
		new Thread(threadLocalTest2, "BBB").start();

	}
}
