package ink.vor.java.juc.container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author muquanrui
 * @date 2022/9/30 19:49
 */

public class ContainerNotSafeTest {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        Map<String, String> map = new ConcurrentHashMap();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        // java.util.ConcurrentModificationException

    }
}
