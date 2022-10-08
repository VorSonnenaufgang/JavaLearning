package ink.vor.jvm;

import java.util.concurrent.TimeUnit;

/**
 * @author muquanrui
 * @date 2022/10/3 13:22
 */

public class HelloGC {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("====Hello GC!");
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
