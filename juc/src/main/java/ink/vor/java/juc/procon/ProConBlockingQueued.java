package ink.vor.java.juc.procon;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author muquanrui
 * @date 2022/10/1 17:52
 */

@Slf4j
public class ProConBlockingQueued {
    public static void main(String[] args) {
        Product product = new Product(new SynchronousQueue<>());
        new Thread(() -> {
            product.produce();
        }, "Producer").start();
        new Thread(() -> {
            product.consume();
        }, "Consumer").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        product.stop();
        log.info(Thread.currentThread().getName() + "\t stopped.R");
    }
}

@Slf4j
class Product {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public Product(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        log.info(blockingQueue.getClass().getName());
    }

    public void produce() {
        String data = null;
        Boolean isOffered = false;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            try {
                isOffered = blockingQueue.offer(data, 1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isOffered) {
                log.info(Thread.currentThread().getName() + "\t produced " + data);
            } else {
                log.info(Thread.currentThread().getName() + "\t couldn't produce " + data);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume() {
        String data = null;
        while (FLAG) {
            try {
                data = blockingQueue.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (data != null) {
                log.info(Thread.currentThread().getName() + "\t consumed " + data);
            } else {
                log.info(Thread.currentThread().getName() + "\t couldn't consume " + data);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        FLAG = false;
    }
}
