package ink.vor.java;

/**
 * @author muquanrui
 * @date 25/02/2022 18:22
 */

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建线程的方式四：使用线程池
 * 1. 提供指定线程数量的线程池。
 * 2. 执行指定的现成的操作，需要提供实现Runnable接口(execute())或Callable接口(submit())的实现类对象。
 * 3. 关闭线程池。
 *
 * 好处：
 * 1. 提高响应速度，减少了创建新线程的时间。
 * 2. 降低资源消耗，重复利用线程池中线程，不用每次都创建。
 * 3. 便于线程管理。
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor)service;
        service1.setCorePoolSize(15);
        service1.execute(new NumberThread());
        service1.shutdown();
    }
}

class NumberThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}