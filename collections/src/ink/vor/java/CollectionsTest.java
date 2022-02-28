package ink.vor.java;

/**
 * @author muquanrui
 * @date 28/02/2022 14:05
 */

import org.junit.Test;

import java.util.*;

/**
 * Collections: 操作Collection和Map的工具类
 *      reverse(List)：反转 List 中元素的顺序
 *      shuffle(List)：对 List 集合元素进行随机排序
 *      sort(List)：根据元素的自然顺序对指定 List 集合元素按升序排序
 *      sort(List，Comparator)：根据指定的 Comparator 产生的顺序对 List 集合元素进行排序
 *      swap(List，int， int)：将指定 list 集合中的 i 处元素和 j 处元素进行交换
 *
 *      Object max(Collection)：根据元素的自然顺序，返回给定集合中的最大元素
 *      Object max(Collection，Comparator)：根据 Comparator 指定的顺序，返回给定集合中的最大元素
 *      Object min(Collection)
 *      Object min(Collection，Comparator)
 *      int frequency(Collection，Object)：返回指定集合中指定元素的出现次数
 *      void copy(List dest,List src)：将src中的内容复制到dest中
 *      boolean replaceAll(List list，Object oldVal，Object newVal)：使用新值替换 List 对象的所有旧值
 *
 * Collections 类中提供了多个 synchronizedXxx()方法：
 * 该方法可使将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题
 */
public class CollectionsTest {
    @Test
    public void testCollections() {
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-97);
        list.add(0);

        System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println(list);

        Collections.sort(list, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(list);

        List<Integer> dest = new ArrayList<>(Arrays.asList(new Integer[list.size()]));
        Collections.copy(dest, list);
        dest.add(2222);
        System.out.println(dest);

        List syncList = Collections.synchronizedList(list);
    }
}
