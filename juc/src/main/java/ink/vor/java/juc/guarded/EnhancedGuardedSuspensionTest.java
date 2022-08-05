package ink.vor.java.juc.guarded;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @author muquanrui
 * @date 2022/7/31 09:59
 */
public class EnhancedGuardedSuspensionTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i  = 0; i < 3; i++) {
            new People().start();
        }
        Thread.sleep(3000);
        for (Integer id : MailBoxes.getIds()) {
            new Postman(id, "内容" + id).start();
        }
    }
}

@Slf4j(topic = "juc.People")
class People extends Thread {
    @Override
    public void run() {
        EnhancedGuardedObject go = MailBoxes.createGuardedObject();
        System.out.println("哈哈");
        log.debug("收信 id:{}", go.getId());
        Object mail = go.get(5000);
        log.debug("收到信 id:{}, 内容:{}", go.getId(), mail);
    }
}

@Slf4j(topic = "juc.Postman")
class Postman extends Thread {
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        EnhancedGuardedObject go = MailBoxes.getGuardObject(id);
        log.debug("送信 id:{}, 内容:{}", go.getId(), mail);
        go.complete(mail);
    }
}

class MailBoxes {
    private static Map<Integer, EnhancedGuardedObject> boxes = new Hashtable<>();

    private static int id = 1;

    private static synchronized int generateId() {
        return id++;
    }

    public static EnhancedGuardedObject getGuardObject(int id) {
        return boxes.remove(id);
    }

    public static EnhancedGuardedObject createGuardedObject() {
        EnhancedGuardedObject go = new EnhancedGuardedObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}

class EnhancedGuardedObject {
    private int id;

    public EnhancedGuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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
