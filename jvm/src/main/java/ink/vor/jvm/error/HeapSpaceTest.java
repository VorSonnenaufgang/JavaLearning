package ink.vor.jvm.error;

/**
 * @author muquanrui
 * @date 2022/10/3 20:19
 */

public class HeapSpaceTest {
    public static void main(String[] args) {
        byte[] bytes = new byte[30 * 1024 * 1024];
    }
}
