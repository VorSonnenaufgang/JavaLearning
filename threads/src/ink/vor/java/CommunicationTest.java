package ink.vor.java;

/**
 * @author muquanrui
 * @date 25/02/2022 16:17
 */

/**
 * 线程通信的例子：两个线程交替打印
 * wati()：一旦执行，当前线程进入阻塞状态并释放同步监视器。
 * notify()：一旦执行，就会唤醒一个wait的线程，如果有多个线程wait，就唤醒优先级高的那个。
 * notifyAll()：一旦执行，所有wait的线程都被唤醒。
 *
 * 说明：
 * 1. 这三个方法必须使用在同步代码块或同步方法中。
 * 2. 这三个方法的调用者必须是同步代码块或方法中的同步监视器，否则会出现IllegalMonitorSateException。
 * 3. 这三个方法是定义在Object类中。
 *
 * 面试题：sleep()和wait()的异同？
 * 相同点：一旦执行方法，都可以使当前线程进入阻塞状态。
 * 不同点：1) 定义的位置不一样，sleep是Thread的静态方法，wait是Object的方法。
 *        2) 调用的范围不一样，sleep可以在任意位置调用，wait只能在同步代码块或方法中。
 *        3) 关于是否释放同步监视器，sleep不会释放锁，wait会释放锁。
 */
public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread thread1 = new Thread(number);
        Thread thread2 = new Thread(number);
        thread1.setName("Thread1");
        thread2.setName("Thread2");
        thread1.start();
        thread2.start();
    }
}

class Number implements Runnable {
    private int number = 1;
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ": " + number++);
                    try {
                        wait(); // 调用wait()的线程进入阻塞状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}