package ink.vor.jvm.error;

/**
 * @author muquanrui
 * @date 2022/10/3 20:52
 */

// -XX:MetaspaceSize=5m -XX:+PrintGCDetails
public class Metaspace {
    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                i++;
            }
        } catch (Throwable e) {
            System.out.println("*******i: " + i);
            e.printStackTrace();
        } finally {

        }
    }

    static class OOMTest {

    }
}
