package ink.vor.java;

/**
 * @author muquanrui
 * @date 26/02/2022 13:49
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 一、集合框架的概述
 * 1. 集合、数组都是对多个数据进行存储操作的结构，简称Java容器。
 * 说明：这里的存储不涉及持久化存储，是内存层面的存储。
 * 2.1 数组在存储多个数据方面的特点：
 *      > 一旦初始化，长度确定。
 *      > 数组一旦定义好，其元素的类型也就确定了。
 * 2.2 数组的缺点：
 *      > 长度不可更改。
 *      > 提供的方法有限，效率不高。
 *      > 数组对一些存储需求，比如不能重复，无序等无法做到。
 *
 * 二、集合框架
 * Collection接口：一个一个的数据
 *      List接口：有序的，可重复的数据
 *          ArrayList，LinkedList，Vector
 *      Set接口：无序的，不可重复的数据
 *          HashSet，LinkedHashSet，TreeSet
 * Map接口：一对一对的数据
 *      HashMap，LinkedHashMap，TreeMap，Hashtable，Properties
 *
 * 三、Collection接口中的API
 *      add()
 *      size()
 *      addAll()
 *      isEmpty()
 *      contains()：判断时会调用对象的equals方法
 *      containsAll()
 *      remove()：判断是否存在时会调用对象的equals方法
 *      removeAll()：移除当前集合中两个collection的交集部分
 *      retainAll()：保留当前集合中两个collection的交集部分
 *      equals()：两个集合内容完全一样返回true
 *      hashCode()
 *      toArray() -- Arrays.asList()
 *      iterator() -> IteratorTest
 */
public class CollectionTest {
    @Test
    public void testCollection() {
        Collection coll = new ArrayList();
        coll.add(1);
        coll.add(2);
        coll.add(new String("Tom"));

        System.out.println(coll.size());

        Collection<Integer> coll1 = new ArrayList<>();
        coll1.addAll(coll);

        System.out.println(coll1.isEmpty());

        System.out.println(coll1.contains(1));
        System.out.println(coll1.contains(new String("Tom")));
        System.out.println(coll1.containsAll(coll));

        coll.remove(1);
        coll.removeAll(coll1);

        coll.add(1);
        coll.add(2);
        coll.retainAll(coll1);

        System.out.println(coll.hashCode());

        Object[] objects = coll.toArray();
        System.out.println(Arrays.toString(objects));

        System.out.println(Arrays.asList(1, 2, 3, 4));
        List<int[]> arr1 = Arrays.asList(new int[]{1, 2, 3, 4});
        List<Integer> arr2 = Arrays.asList(new Integer[]{1, 2, 3, 4});
        System.out.println(arr1);
    }

}
