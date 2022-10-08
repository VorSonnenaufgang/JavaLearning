package ink.vor.java.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author muquanrui
 * @date 2022/10/4 21:32
 */

public class LockSupportTest {
    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t" + "-----wake up");
        }, "A");

        a.start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t" + "------notify");
        }, "B").start();
    }

    public static void AwaitSignalTest() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t" + "-----wake up");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "\t" + "------notify");
        }, "B").start();
    }

    public static void WaitNotifyTest() {
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "-----wake up");
            }
        }, "A").start();

        new Thread(() -> {
            objectLock.notify();
            System.out.println(Thread.currentThread().getName() + "\t" + "------notify");
        }, "B").start();
    }
}
