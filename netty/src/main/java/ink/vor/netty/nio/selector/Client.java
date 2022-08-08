package ink.vor.netty.nio.selector;

import java.io.IOException;
import java.net.Socket;

/**
 * @author muquanrui
 * @date 2022/8/8 11:27
 */
public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            System.out.println(socket);
            socket.getOutputStream().write("world".getBytes());
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
