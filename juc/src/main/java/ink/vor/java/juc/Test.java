package ink.vor.java.juc;

import java.util.concurrent.BlockingDeque;

/**
 * @author muquanrui
 * @date 2022/8/1 09:56
 */
public class Test {
    public static void main(String[] args) {
        int time = 255 + 165 + 120 + 150 + 15 + 270 + 90 + 90 + 180 + 45 + 60;
        System.out.println(time / 60);
        System.out.println(time % 60);
    }
}
