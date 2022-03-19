package ink.vor.java;

import org.junit.Test;

/**
 * @author muquanrui
 * @date 19/03/2022 22:17
 */
public class JunitThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread());
        thread.setName("Child Thread");
        thread.start();
    }

    @Test
    public void test() {
        Thread thread = new Thread(new MyThread());
        thread.setName("Child Thread");
        thread.start();
    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

