package ink.vor.java2;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author muquanrui
 * @date 04/03/2022 15:02
 */

// 获取当前运行时类的属性结构
public class FieldTest {
    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = Class.forName("ink.vor.java1.Person");
        // 获取属性结构
        // getFields():获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] fields = clazz.getFields();
        System.out.println(Arrays.toString(fields));

        // getDeclaredFields():获取当前运行时类中声明的所有属性。（不包含父类中声明的属性）
        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));
    }

    // 权限修饰符 数据类型 变量名
    @Test
    public void test2() throws ClassNotFoundException {
        Class clazz = Class.forName("ink.vor.java1.Person");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field: declaredFields) {
            // 1.权限修饰符
            System.out.print(Modifier.toString(field.getModifiers()) + "\t");
            // 2.数据类型
            System.out.print(field.getType() + "\t");
            // 3.变量名
            System.out.print(field.getName() + "\n");
        }
    }
}
