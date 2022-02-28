package ink.vor.java;

/**
 * @author muquanrui
 * @date 28/02/2022 23:05
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型在继承方面的体现：
 * 1. 虽然类A是类B的父类，但是G<A> 和G<B>二者不具备子父类关系，二者是并列关系。
 *      补充：类A是类B的父类，A<G> 是 B<G> 的父类
 * 2. 通配符的使用
 *      通配符：? -> 类A是类B的父类，G<A>和G<B>是没有关系的，二者共同的父类是：G<?>
 *      注意：内部不能写入数据，除了null；可以读取数据，读取到的是Object类。
 * 3. 有限制条件的通配符的使用。
 *      ? extends A -> G<? extends A> 可以作为G<A>和G<B>的父类，其中B是A的子类
 *      ? super A -> G<? super A> 可以作为G<A>和G<B>的父类，其中B是A的父类
 *      注意：<? extends A>不可以写入数据除了null，<? super A>可以写入A或A的子类；可以读取数据，<? extends A>读取到的可以是A类，<? super A>读取到的事Object类。
 */
public class GenericInheritanceTest {
    @Test
    public void testInheritance() {
        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;
        // 编译不通过
        // Date date = new Date();
        // str = date;
        List<Object> list1 = null;
        List<String> list2 = new ArrayList<String>();
        // 此时的list1和list2的类型不具有子父类关系
        // 编译不通过
        // list1 = list2;
    }

    @Test
    public void testWildcards() {
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        list = list1;
        list = list2;
        // 编译通过
        // print(list1);
        // print(list2);
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        // 添加(写入)：对于List<?>就不能向其内部添加数据。
        // 除了添加null之外。
        // list.add("DD");
        // list.add('?');
        list.add(null);
        //获取(读取)：允许读取数据，读取的数据类型为Object。
        Object o = list.get(0);
        System.out.println(o);
    }

    @Test
    public void testLimitedWildcards() {
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<Student>();
        List<Person> list4 = new ArrayList<Person>();
        List<Object> list5 = new ArrayList<Object>();

        list1 = list3;
        list1 = list4;
        // list1 = list5;
        // list2 = list3;
        list2 = list4;
        list2 = list5;

        //读取数据：
        list1 = list3;
        Person p = list1.get(0);
        // 编译不通过
        // Student s = list1.get(0);

        list2 = list4;
        Object obj = list2.get(0);
        // 编译不通过
        // Person obj = list2.get(0);

        // 写入数据：
        // 编译不通过
        // list1.add(new Student());

        // 编译通过
        list2.add(new Person());
        list2.add(new Student());
    }
}
