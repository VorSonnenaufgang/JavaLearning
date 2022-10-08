package ink.vor.jvm.error;

import java.nio.ByteBuffer;

/**
 * @author muquanrui
 * @date 2022/10/3 20:30
 */

// -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails
public class DirectBufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(8 * 1024 * 1024);
    }
}
