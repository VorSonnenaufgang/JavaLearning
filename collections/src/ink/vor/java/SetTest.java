package ink.vor.java;

/**
 * @author muquanrui
 * @date 26/02/2022 17:21
 */

import org.junit.Test;

import java.util.*;

/**
 * Collection接口：一个一个的数据
 *      Set接口：无序的，不可重复的数据
 *          - HashSet：主要实现类，线程不安全，可以存储null。
 *              - LinkedHashSet：HashSet子类，遍历其内部数据时可以按照添加的顺序遍历。
 *          - TreeSet：SortedSet接口的实现类，可以按照添加元素的指定属性进行排序。
 *
 * Set接口中没有额外定义新的方法。
 *
 * 理解Set的无序性与不可重复性：
 * 1. 无序：不等于随机性，指的是存储的数据在底层数组中的顺序并非按照数组索引的添加顺序，而是根据数据的哈希值决定的。
 * 2. 不可重复：保证添加的元素按照equals()判断时，不能返回true，即相同的元素只能添加一个。
 *
 * 添加元素的过程：见iPad图。
 *
 * HashSet底层是数组+链表的结构。
 *
 * 在向Set添加的数据：
 * 1. equals()和hashCode()必须要重写。
 * 2. equals()和hashCode()必须要一致，相同的对象一定要有相同的hashCode。
 *
 * LinkedHashSet：作为HashSet的子类，在添加数据的同时，每个数据还维护了两个引用，记录此数据前一个数据和后一个数据。
 * 优点：对于频繁的遍历操作，LinkedHashSet效率高于HashSet。
 *
 * TreeSet的使用：
 * 1.向TreeSet中添加的数据，要求是相同类的对象。
 * 2.两种排序方式：自然排序（实现Comparable接口） 和 定制排序（Comparator）。
 * 3.自然排序中，比较两个对象是否相同的标准为：compareTo()返回0，不再是equals()。
 * 4.定制排序中，比较两个对象是否相同的标准为：compare()返回0，不再是equals()。
 */
public class SetTest {
    @Test
    public void testHashSet() {
        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(129);
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testLinkedHashSet() {
        Set set2 = new LinkedHashSet();
        set2.add(456);
        set2.add(123);
        set2.add(123);
        set2.add("AA");
        set2.add("CC");
        set2.add(129);

        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()){
            System.out.println(iterator2.next());
        }
    }

    @Test
    public void testTreeSet() {
        TreeSet set = new TreeSet();
        // set.add(123);
        // set.add(456);
        // set.add(12);
        // 不能添加不同类的元素
        // set.add("Tom");
        set.add(new Person("Tom", 12));
        set.add(new Person("Jerry", 15));
        set.add(new Person("Jack", 16));
        // 按name比，compareTo返回0，被认为是同样的元素，无法添加
        set.add(new Person("Jack", 26));
        set.add(new Person("Steph", 17));

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        TreeSet set2 = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Person && o2 instanceof Person) {
                    Person person1 = (Person) o1;
                    Person person2 = (Person) o2;
                    return Integer.compare(person1.age, person2.age);
                } else {
                    throw new RuntimeException();
                }
            }
        });

        set2.add(new Person("Tom", 12));
        set2.add(new Person("Jerry", 15));
        set2.add(new Person("Jack", 16));
        // 按age比，compare不返回0，不被认为是同样的元素，可以添加
        set2.add(new Person("Jack", 26));
        set2.add(new Person("Steph", 17));
    }
}

class Person implements Comparable{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Person) {
            Person person = (Person) o;
            return this.name.compareTo(person.name);
        } else {
            throw new RuntimeException();
        }
    }
}
