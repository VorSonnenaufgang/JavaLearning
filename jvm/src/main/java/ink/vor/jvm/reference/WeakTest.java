package ink.vor.jvm.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author muquanrui
 * @date 2022/10/3 16:43
 */

public class WeakTest {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("========================");

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
