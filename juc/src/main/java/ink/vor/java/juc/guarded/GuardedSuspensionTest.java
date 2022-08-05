package ink.vor.java.juc.guarded;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muquanrui
 * @date 2022/7/31 08:59
 */

@Slf4j(topic = "juc.GuardedSuspensionTest")
public class GuardedSuspensionTest {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            log.debug("等待结果...");
            List<String> list = (List<String>) guardedObject.get(2000);
            log.debug("结果大小：{}", list.size());
        }, "t1").start();
        new Thread(() -> {
            log.debug("执行下载...");
            try {
                List<String> list = Downloader.download();
                guardedObject.complete(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class GuardedObject {
    private Object response;

    public Object get(long timeout) {
        synchronized (this) {
            long begin = System.currentTimeMillis();
            long passed = 0;
            while (response == null) {
                long waitTime = timeout - passed;
                try {
                    if (waitTime <= 0) {
                        break;
                    }
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passed = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}

class Downloader {
    public static List<String> download() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL("https://www.baidu.com/").openConnection();
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
