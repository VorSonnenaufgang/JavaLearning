package ink.vor.netty.nio.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ink.vor.netty.nio.bytebuffer.ByteBufferUtil;

/**
 * @author muquanrui
 * @date 2022/8/8 11:16
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 1. 创建selector
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 2. 建立selector和channel的联系
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // sscKey只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("register key:{}", sscKey);

        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            // 3. select方法，没有事件发生线程阻塞，有事件线程恢复运行
            // 有未处理事件时，select不会阻塞，因此事件发生后要么处理，要么取消，不能置之不理
            selector.select();

            // 4. 处理事件，selectedKeys包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 一定要手动移除！
                iterator.remove();
                log.debug("key:{}", key);
                // 5. 区分事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}", sc);
                } else if (key.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        int read = channel.read(buffer);
                        if (read == -1) {
                            key.cancel();
                        } else {
                            buffer.flip();
                            ByteBufferUtil.debugRead(buffer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                    }
                }
                // key.cancel();
            }
        }
    }
}
