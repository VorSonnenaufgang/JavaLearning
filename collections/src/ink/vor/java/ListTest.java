package ink.vor.java;

/**
 * @author muquanrui
 * @date 26/02/2022 15:05
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Collection接口：一个一个的数据
 *      List接口：有序的，可重复的数据
 *          - ArrayList
 *          - LinkedList
 *          - Vector
 * 三个实现类的异同？
 * 同：都实现了List接口，存储都是有序的，可重复的数据。
 * ArrayList： List接口的主要实现类；线程不安全的，效率高；底层使用Object[] elementData。
 * LinkedList：对于频繁的插入、删除操作效率更高；底层使用双向链表存储。
 * Vector：古老实现类，线程安全的，效率低；底层使用Object[] elementData。
 *
 * 三个实现类的源码分析：见iPad图。
 *
 * List接口常用方法：
 * void add(int index, Object ele):在index位置插入ele元素
 * boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来
 * Object get(int index):获取指定index位置的元素
 * int indexOf(Object obj):返回obj在集合中首次出现的位置
 * int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
 * Object remove(int index):移除指定index位置的元素，并返回此元素
 * Object set(int index, Object ele):设置指定index位置的元素为ele
 * List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的子集合
 *
 * 总结：常用方法
 * 增：add(Object obj)
 * 删：remove(int index) / remove(Object obj)
 * 改：set(int index, Object ele)
 * 查：get(int index)
 * 插：add(int index, Object ele)
 * 长度：size()
 * 遍历：Iterator迭代器方式
 *      增强for循环
 *      普通的循环
 */
public class ListTest {
    @Test
    public void testList() {
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new String("Tom"));
        list.add(456);

        //void add(int index, Object ele):在index位置插入ele元素
        list.add(1,"BB");
        System.out.println(list);

        //boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
        // list.add(list1);
        System.out.println(list.size());//9

        //Object get(int index):获取指定index位置的元素
        System.out.println(list.get(0));

        //int indexOf(Object obj):返回obj在集合中首次出现的位置。如果不存在，返回-1.
        int index = list.indexOf(4567);
        System.out.println(index);

        //int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置。如果不存在，返回-1.
        System.out.println(list.lastIndexOf(456));

        //Object remove(int index):移除指定index位置的元素，并返回此元素
        Object obj = list.remove(0);
        System.out.println(obj);
        System.out.println(list);

        //Object set(int index, Object ele):设置指定index位置的元素为ele
        list.set(1,"CC");
        System.out.println(list);

        //List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的左闭右开区间的子集合
        List subList = list.subList(2, 4);
        System.out.println(subList);
        System.out.println(list);


    }
}
