package ink.vor.java.juc.volatiletest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author muquanrui
 * @date 2022/9/30 14:01
 */

public class VolatileTest {
    public static void main(String[] args) {

    }

    public static void atomicTest() {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPP();
                    myData.addPPAtomic();
                }
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.n);
        System.out.println(Thread.currentThread().getName() + "\t finally Atomic Integer value: " + myData.i);
    }

    public static void visibleTest() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in.");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + " add myData to 60");
        }).start();
        while (myData.n == 0);
        System.out.println(Thread.currentThread().getName() + " is over.");
    }
}

class MyData {
    volatile int n = 0;

    public void addTo60() {
        n = 60;
    }

    public void addPP() {
        n++;
    }

    AtomicInteger i = new AtomicInteger();

    public void addPPAtomic() {
        i.getAndIncrement();
    }
}
