package ink.vor.jvm.reference;

/**
 * @author muquanrui
 * @date 2022/10/3 16:36
 */

public class StrongTest {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        o1 = null;
        System.gc();
        System.out.println(o2);
    }
}
