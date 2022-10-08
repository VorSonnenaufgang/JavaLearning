package ink.vor.java.juc.blockqueue;

import java.util.concurrent.*;

/**
 * @author muquanrui
 * @date 2022/10/1 15:09
 */

public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        BlockingQueue<String> blockingQueue1 = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                blockingQueue1.put("1");
                System.out.println(Thread.currentThread().getName() + "\t put 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                blockingQueue1.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                blockingQueue1.put("1");
                System.out.println(Thread.currentThread().getName() + "\t put 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Producer").start();

        new Thread(() -> {
            try {
                blockingQueue1.take();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t take 1");

            try {
                blockingQueue1.take();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t take 2");

            try {
                blockingQueue1.take();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t take 3");
        }, "Consumer").start();
    }
}
