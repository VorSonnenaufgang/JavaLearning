package ink.vor.jvm.error;

/**
 * @author muquanrui
 * @date 2022/10/3 20:14
 */

public class StackOverflowTest {
    public static void main(String[] args) {
        stackOverflow();
    }

    private static void stackOverflow() {
        stackOverflow();
    }
}

