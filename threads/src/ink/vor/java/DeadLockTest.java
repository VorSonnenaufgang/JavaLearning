package ink.vor.java;

import org.junit.experimental.theories.Theories;

/**
 * @author muquanrui
 * @date 24/02/2022 20:57
 */

/**
 * 1. 死锁的理解：不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃，形成死锁。
 * 2. 说明：
 *      1) 出现死锁后，不会出现异常，不会出现提示，只是所有线程处于阻塞状态，无法继续。
 *      2) 使用同步时，要避免出现死锁。
 * 3. 解决方法：
 *      1) 专门的算法和原则。
 *      2) 减少同步资源的定义。
 *      3) 尽量避免嵌套同步。
 */
public class DeadLockTest {
    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        new Thread() {
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("a");
                    s2.append("1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2) {
                    s1.append("c");
                    s2.append("3");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s1) {
                        s1.append("d");
                        s2.append("4");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();
    }
}
