package ink.vor.java;

/**
 * @author muquanrui
 * @date 24/02/2022 17:06
 */

/**
 * 线程的安全问题
 * 例子：卖票过程中出现重票、错票。
 * 出现原因：某个线程进入卖票的过程中但未完成时，其他线程也加入进来卖票，共享数据出错。
 * 如何解决：当一个线程在卖票的过程中，其他线程不能被允许参与进来，直到该线程操作结束。即使该线程被阻塞，也不允许别的线程参与。
 * 在Java中，通过同步机制来解决线程的安全问题。
 */

/**
 * 方式一：同步代码块
 * synchronized(同步监视器) {
 *     需要被同步的代码
 * }
 * 1. 需要被同步的代码：操作共享数据的代码。 -> 不能包含多了或者包含少了
 * 2. 共享数据：多个线程共同操作的变量，比如卖票的ticket。
 * 3. 同步监视器：俗称锁。任何一个类的对象都可以充当锁。
 *      要求：多个线程必须共用一把锁。
 *      在实现Runnable的方式中，可以考虑使用this。
 *      在继承Thread类创建多线程的方式中，慎用this，可以使用当前类充当。
 *
 * 方式二：同步方法
 * 如果操作共享数据的代码完整声明在一个方法中，可以将方法声明为同步。
 * 1. 同步方法仍然是基于同步监视器，但是不需要显式声明。
 * 2. 非静态的同步方法，同步监视器是this。
 * 3. 静态的同步方法，同步监视器当前类。
 *
 * 同步的方式优缺点：解决了线程安全问题，但是同步代码块中只能一个线程参与，相当于又是单线程了，效率低。
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        Window2 window = new Window2();
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

class Window implements Runnable {
    private int ticket = 100;
    Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            // synchronized (obj) {
            synchronized (this) {
                if (ticket > 0) {
                    // try {
                    //     Thread.sleep(100);
                    // } catch (InterruptedException e) {
                    //     e.printStackTrace();
                    // }
                    System.out.println(Thread.currentThread().getName() + " --- Ticket No." + ticket-- + " sold.");
                } else {
                    break;
                }
            }
        }
    }
}

class Window2 implements Runnable {
    private int ticket = 100;
    Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            show();
            if (ticket < 1) {
                break;
            }
        }
    }

    // 同步监视器就是this
    // 如果是继承Thread，要把方法写成static，同步监视器是当前类(.class)
    private synchronized void show() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " --- Ticket No." + ticket-- + " sold.");
        }
    }
}
