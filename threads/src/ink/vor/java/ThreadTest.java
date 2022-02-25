package ink.vor.java;

import org.junit.Test;

/**
 * @author muquanrui
 * @date 24/02/2022 11:32
 */

/**
 * 新建线程的方法——继承Thread类：
 * 1. 继承Thread类
 * 2. 重写run方法
 * 3. 新建对象
 * 4. 执行start方法
 */

public class ThreadTest {
    @Test
    public void testThread() {
        MyThread myThread1 = new MyThread();
        // Q1：启动线程一定是start()，如果调run()，就不是新建线程，而是直接在主线程中调方法
        myThread1.start();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i + "-----------Main");
            }
        }
        // Q2：再启动一个线程，不能用之前已经start的对象再调start，要新建对象
        // myThread1.start();
        new MyThread().start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
