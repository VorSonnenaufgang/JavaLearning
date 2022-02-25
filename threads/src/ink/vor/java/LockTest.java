package ink.vor.java;

/**
 * @author muquanrui
 * @date 24/02/2022 21:14
 */

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程的安全问题方式三：Lock锁
 * 1. 新建ReentrantLock对象。
 * 2. try块后调用lock()。
 * 3. finally后调用unlock()。
 *
 * synchronized和lock方式的异同：
 * 同：二者都用来解决线程安全问题。
 * 异：synchronized会在执行完同步代码块后自动释放同步监视器，lock需要手动启动和结束同步。
 */
public class LockTest {
    public static void main(String[] args) {
        Window3 window = new Window3();
        Thread window1 = new Thread(window);
        window1.setName("Window-1");
        Thread window2 = new Thread(window);
        window2.setName("Window-2");
        Thread window3 = new Thread(window);
        window3.setName("Window-3");
        window1.start();
        window2.start();
        window3.start();
    }
}

class Window3 implements Runnable {
    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock(true);  // 参数fair设置为true时代表锁是公平的，队列模式，false则随机抢占。
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (ticket > 0) {
                  try {
                      Thread.sleep(100);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  System.out.println(Thread.currentThread().getName() + " --- Ticket No." + ticket-- + " sold.");
              } else {
                  break;
              }
            } finally {
                lock.unlock();
            }
        }
    }
}