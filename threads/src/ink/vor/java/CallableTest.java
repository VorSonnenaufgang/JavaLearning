package ink.vor.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author muquanrui
 * @date 25/02/2022 17:53
 */

/**
 * 创建线程的方式三：实现Callable接口
 * 1. 创建一个实现Callable的实现类。
 * 2. 实现call方法，将此线程需要执行的操作声明在call方法。
 * 3. 创建Callable接口实现类的对象，将此对象作为参数传递到FutureTask的构造器中，创建FutureTask对象。
 * 4. 将此FutureTask对象作为参数传递到Thread的构造器中，创建Thread对象并调用start()。
 * 5. 如果需要call()的返回值，调用FutureTask对象的get()。
 *
 * 如何理解实现Callable接口的方式比实现Runnable接口的方式更强大？
 * 1. call()可以有返回值。
 * 2. call()可以抛出异常，被外面的操作捕获，获取异常的信息。
 * 3. Callable是支持范型的。
 */

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NumThread numThread = new NumThread();
        FutureTask<Integer> futureTask = new FutureTask(numThread);
        Thread thread = new Thread(futureTask);
        thread.start();
        Integer sum = futureTask.get(); // 返回值就是FutureTask的参数Callable实现类重写call方法的返回值
        System.out.println(sum);
    }
}

class NumThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
