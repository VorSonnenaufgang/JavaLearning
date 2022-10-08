package ink.vor.jvm.error;

import java.util.concurrent.TimeUnit;

/**
 * @author muquanrui
 * @date 2022/10/3 20:41
 */

public class UnableNativeThreadTest {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("***********i" + i);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
