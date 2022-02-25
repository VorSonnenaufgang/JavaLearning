package ink.vor.java;

/**
 * @author muquanrui
 * @date 24/02/2022 11:45
 */

/**
 * Thread类常用方法：
 * 1. start()：启动当前线程；调用当前线程run()
 * 2. run()：需要重写，将线程要执行的操作放入其中
 * 3. currentThread()：静态方法，返回当前代码执行的线程
 * 4. getName()：获取当前线程的名字
 * 5. setName()：设置当前线程的名字
 * 6. yield()：释放当前CPU的执行权
 * 7. join()：在线程A调用线程B的join()，线程A进入阻塞状态，直到线程B执行完线程A才结束阻塞状态
 * 8. stop()：deprecated，执行时强制结束当前线程
 * 9. sleep()：让当前线程"睡眠"指定millitime毫秒，在此时间段内，当前线程阻塞状态。
 * 10. isAlive()：判断当前线程是否存活
 */

/**
 * MAX_PRIORITY = 10
 * MIN_PRIORITY = 1
 * NORM_PRIORITY = 5
 * 11. getPriority()：获取线程优先级
 * 12. setPriority()：设置线程优先级
 * 高优先级线程抢占低优先级的cpu执行权，但这只是概率问题，即高优先级有更高概率抢占，但不是100%。
 */

public class ThreadMethodTest {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("Main Thread");
        MyNewThread myThread = new MyNewThread();
        myThread.setName("MyThread 1");
        myThread.setPriority(Thread.MAX_PRIORITY);
        myThread.start();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            // if (i % 20 == 0) {
            //     myThread.join();
            // }
        }
        System.out.println(myThread.isAlive());
    }
}

class MyNewThread extends Thread {
    @Override
    public void run() {
        System.out.println("Priority: " + getPriority());
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                // try {
                //     sleep(10);
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            // if (i % 20 == 0) {
            //     yield();
            // }
        }
    }
}


