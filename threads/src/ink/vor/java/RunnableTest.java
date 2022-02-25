package ink.vor.java;

/**
 * @author muquanrui
 * @date 24/02/2022 16:05
 */

/**
 * 创建多线程的方式二——实现Runnable接口
 * 1. 创建一个实现了Runnable接口的类
 * 2. 实现run()
 * 3. 创建实现类的对象
 * 4. 将此对象作为参数传入Thread构造器新建Thread类对象
 * 5. 通过Thread对象调用start()
 */

/**
 * 比较两种方式
 * 开发中优先选择实现Runnable方式，因为：
 * 1. 没有单继承的局限性
 * 2. 实现的方式更利于共享数据的处理，不需要static
 * Thread类本身也实现了Runnable接口，两种方式都需要重写run()方法。
 */

public class RunnableTest {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread newThread = new Thread(myRunnable);
        newThread.start();
        Thread renewThread = new Thread(myRunnable);
        renewThread.start();
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}
