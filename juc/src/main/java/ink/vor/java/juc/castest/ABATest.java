package ink.vor.java.juc.castest;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author muquanrui
 * @date 2022/9/30 19:30
 */

public class ABATest {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
    public static void main(String[] args) {
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1");

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        }, "t2").start();

        System.out.println("=================解决===============");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1) + "\t" + atomicReference.get());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1) + "\t" + atomicReference.get());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1) + "\t" + atomicReference.get());
        }, "t4").start();
    }
}
