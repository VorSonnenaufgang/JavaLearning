package ink.vor.java;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author muquanrui
 * @date 28/02/2022 19:03
 */

/**
 * 泛型的使用
 * 1.jdk 5.0新增的特性
 *
 * 2.在集合中使用泛型：
 *  总结：
 *  ① 集合接口或集合类在jdk5.0时都修改为带泛型的结构。
 *  ② 在实例化集合类时，可以指明具体的泛型类型
 *  ③ 指明完以后，在集合类或接口中凡是定义类或接口时，内部结构（比如：方法、构造器、属性等）使用到类的泛型的位置，都指定为实例化的泛型类型。
 *    比如：add(E e)  --->实例化以后：add(Integer e)
 *  ④ 注意点：泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置，拿包装类替换
 *  ⑤ 如果实例化时，没有指明泛型的类型。默认类型为java.lang.Object类型。
 *
 * 3.如何自定义泛型结构：泛型类、泛型接口；泛型方法。
 *
 * 泛型类，泛型接口：
 *      如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为Object类型
 *      要求：如果定义了类是带泛型的，建议在实例化时要指明类的泛型。
 * 注意：
 *      1. 由于子类在继承带泛型的父类时，指明了泛型类型。则实例化子类对象时，不再需要指明泛型。
 *      2. 泛型不同的引用不能相互赋值。
 *      3. 静态方法不能够使用类的范型。
 *      4. 异常类不能声明为泛型类。
 *      5. 创建泛型数组：T[] arr = (T[]) new Object[10];
 */
public class GenericTest {

    @Test
    public void testGeneric() {
        Person<String> person = new Person<>("Tom", 12, "Dog");
        Student student = new Student();
        Teacher<Integer> teacher = new Teacher<>();
    }

    @Test
    public void test3(){

        ArrayList<String> list1 = null;
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        // 泛型不同的引用不能相互赋值。
        // list1 = list2;
        Person p1 = null;
        Person p2 = null;
        p1 = p2;
    }
}

class Person<T> {
    String name;
    Integer age;
    T pet;

    public Person() {
    }

    public Person(String name, Integer age, T pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
    }

    public void creatArr() {
        // 编译不通过
        // T [] arr = new T[10];
        // 编译通过
        T[] arr = (T[]) new Object[10];
    }

    // public static void show(T pet){
    //     System.out.println(pet);
    // }
}

class Student extends Person<String> {

}

class Teacher<T> extends Person<T> {

}

// class MyException<T> extends Exception{
// }