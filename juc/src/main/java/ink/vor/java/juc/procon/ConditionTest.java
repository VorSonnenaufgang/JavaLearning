package ink.vor.java.juc.procon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author muquanrui
 * @date 2022/10/1 17:42
 */

public class ConditionTest {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            shareResource.print(5);
        }, "AA").start();

        new Thread(() -> {
            shareResource.print(10);
        }, "BB").start();

        new Thread(() -> {
            shareResource.print(15);
        }, "CC").start();
    }
}

class ShareResource {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void print(int count) {
        lock.lock();
        try {
            if (count == 5) {
                while (number != 1) {
                    conditionA.await();
                }
                printTimes(5);
                number = 2;
                conditionB.signal();
            }
            if (count == 10) {
                while (number != 2) {
                    conditionB.await();
                }
                printTimes(10);
                number = 3;
                conditionC.signal();
            }

            if (count == 15) {
                while (number != 3) {
                    conditionC.await();
                }
                printTimes(15);
                number = 4;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void printTimes(int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
    }
}
