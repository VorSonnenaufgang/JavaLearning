package ink.vor.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * @author muquanrui
 * @date 2022/10/3 16:37
 */

public class SoftTest {
    public static void main(String[] args) {
        enough();
        System.out.println("================================");
        notEnough();
    }

    public static void enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    // -Xms5m -Xmx5m -XX:+PrintGCDetails
    public static void notEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }
}
