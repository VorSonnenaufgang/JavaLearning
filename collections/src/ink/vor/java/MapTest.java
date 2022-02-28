package ink.vor.java;

/**
 * @author muquanrui
 * @date 26/02/2022 18:43
 */

import org.junit.Test;

import java.util.*;

/**
 * Map接口：一对一对的数据
 *      HashMap：主要实现类，线程不安全的，效率高。可以存储null的key或value。
 *          LinkedHashMap：添加了一对指针，指向前一个和后一个元素，保证遍历时是按照添加的顺序。对于频繁的遍历，执行效率高于HashMap。
 *      TreeMap：保证按照添加的key-value进行排序，实现按照排序的顺序遍历。底层使用红黑树。
 *      Hashtable：古老实现类，线程安全的，效率低。不能存储null。
 *          Properties：常用来处理配置文件。key和value都是String类型。
 *
 * Map结构的理解：
 * 1. key是无序的，不可重复的，使用Set存储所有的key。 -> 对于HashMap，key所在的类要重写equals和hashCode。
 * 2. value是无序的，可重复的，使用Collection存储所有的value。-> value所在的类要重写equals（containsKey()使用）
 * 3. key-value构成一个Entry对象，entry也是无序的，不可重复的。
 *
 * HashMap的底层：见iPad图。
 *
 * LinkedHashMap底层
 *
 * Map接口常用方法
 * 添加、删除、修改操作：
 *      Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
 *      void putAll(Map m):将m中的所有key-value对存放到当前map中
 *      Object remove(Object key)：移除指定key的key-value对，并返回value
 *      void clear()：清空当前map中的所有数据
 * 元素查询的操作：
 *      Object get(Object key)：获取指定key对应的value
 *      boolean containsKey(Object key)：是否包含指定的key
 *      boolean containsValue(Object value)：是否包含指定的value
 *      int size()：返回map中key-value对的个数
 *      boolean isEmpty()：判断当前map是否为空
 *      boolean equals(Object obj)：判断当前map和参数对象obj是否相等
 * 元视图操作的方法：
 *      Set keySet()：返回所有key构成的Set集合
 *      Collection values()：返回所有value构成的Collection集合
 *      Set entrySet()：返回所有key-value对构成的Set集合
 *
 * 总结：
 * 添加：put(Object key,Object value)
 * 删除：remove(Object key)
 * 修改：put(Object key,Object value)
 * 查询：get(Object key)
 * 长度：size()
 * 遍历：keySet() / values() / entrySet()
 *
 * TreeMap的添加方式：按照key进行排序 -> 自然排序，定制排序
 * 要求：key必须是同一个类创建的对象
 */
public class MapTest {
    @Test
    public void testTreeMap() {
        TreeMap<Person, Integer> map = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });
        map.put(new Person("Tom", 23), 12);
        map.put(new Person("Jerry", 32), 13);
        map.put(new Person("Jack", 18), 15);
        map.put(new Person("Rose", 21), 18);

        Iterator<Map.Entry<Person, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test5(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,1234);
        map.put("BB",56);

        //遍历所有的key集：keySet()
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println();
        //遍历所有的value集：values()
        Collection values = map.values();
        for(Object obj : values){
            System.out.println(obj);
        }
        System.out.println();
        //遍历所有的key-value
        //方式一：entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---->" + entry.getValue());

        }
        System.out.println();
        //方式二：
        Set keySet = map.keySet();
        Iterator iterator2 = keySet.iterator();
        while(iterator2.hasNext()){
            Object key = iterator2.next();
            Object value = map.get(key);
            System.out.println(key + "=====" + value);
        }
    }

    @Test
    public void test4(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        // Object get(Object key)
        System.out.println(map.get(45));
        //containsKey(Object key)
        boolean isExist = map.containsKey("BB");
        System.out.println(isExist);

        isExist = map.containsValue(123);
        System.out.println(isExist);

        map.clear();

        System.out.println(map.isEmpty());

    }

    @Test
    public void test3(){
        Map map = new HashMap();
        //添加
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        //修改
        map.put("AA",87);

        System.out.println(map);

        Map map1 = new HashMap();
        map1.put("CC",123);
        map1.put("DD",123);

        map.putAll(map1);

        System.out.println(map);

        //remove(Object key)
        Object value = map.remove("CC");
        System.out.println(value);
        System.out.println(map);

        //clear()
        map.clear();//与map = null操作不同
        System.out.println(map.size());
        System.out.println(map);
    }

    @Test
    public void test2(){
        Map map = new HashMap();
        map = new LinkedHashMap();
        map.put(123,"AA");
        map.put(345,"BB");
        map.put(12,"CC");

        System.out.println(map);
    }


    @Test
    public void test1(){
        Map map = new HashMap();
//        map = new Hashtable();
        map.put(null,123);

    }
}
