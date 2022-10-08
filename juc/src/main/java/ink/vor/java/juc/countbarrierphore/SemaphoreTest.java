package ink.vor.java.juc.countbarrierphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author muquanrui
 * @date 2022/10/1 14:57
 */

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    int seconds = new Random().nextInt(3);
                    TimeUnit.SECONDS.sleep(seconds);
                    System.out.println(Thread.currentThread().getName() + "\t停车"  + seconds + "s后离开车位");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
