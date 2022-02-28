package ink.vor.java;

/**
 * @author muquanrui
 * @date 26/02/2022 14:37
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合元素的遍历：使用iterator方法
 * hasNext()搭配next()
 * remove()：如果还没调用next()直接调用或连续两次调用都会报错
 */
public class IteratorTest {
    @Test
    public void testIterator() {
        Collection coll = new ArrayList();
        coll.add(1);
        coll.add(2);
        coll.add(new String("Tom"));

        Iterator iterator = coll.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Iterator iterator1 = coll.iterator();
        while (iterator1.hasNext()) {
            Object obj = iterator1.next();
            if (obj.equals("Tom")) {
                iterator1.remove();
            }
        }

        Iterator iterator2 = coll.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }

        for (Object obj: coll) {
            System.out.println(obj);
        }
    }
}
