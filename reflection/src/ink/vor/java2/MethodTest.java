package ink.vor.java2;

import ink.vor.java1.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author muquanrui
 * @date 04/03/2022 15:13
 */
public class MethodTest {
    @Test
    public void test1(){
        Class clazz = Person.class;
        // getMethods():获取当前运行时类及其所有父类中声明为public权限的方法
        Method[] methods = clazz.getMethods();
        // for (Method method: methods) {
        //     System.out.println(method);
        // }
        // getDeclaredMethods():获取当前运行时类中声明的所有方法。（不包含父类中声明的方法）
        Method[] methods1 = clazz.getDeclaredMethods();
        for (Method method: methods1) {
            System.out.println(method);
        }
    }
    /*
    @Xxxx
    权限修饰符  返回值类型  方法名(参数类型1 形参名1,...) throws XxxException{}
     */
    @Test
    public void test2(){
        Class clazz = Person.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method: methods) {
            // 1.获取方法声明的注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation: annotations) {
                System.out.println(annotation);
            }
            // 2.权限修饰符
            System.out.print(Modifier.toString(method.getModifiers()) + "\t");
            // 3.返回值类型
            System.out.print(method.getReturnType() + "\t");
            // 4.方法名
            System.out.print(method.getName());
            // 5.形参列表
            System.out.print("(");
            Class[] parameterTypes = method.getParameterTypes();
            if (!(parameterTypes == null || parameterTypes.length == 0)) {
                for (int i = 0; i < parameterTypes.length; i++) {
                    if(i == parameterTypes.length - 1){
                        System.out.print(parameterTypes[i].getName() + " args_" + i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName() + " args_" + i + ",");
                }
            }
            System.out.print(")");
            // 6.抛出的异常
            Class[] exceptionTypes = method.getExceptionTypes();
            if(exceptionTypes.length > 0){
                System.out.print(" throws ");
                for(int i = 0;i < exceptionTypes.length;i++){
                    if(i == exceptionTypes.length - 1){
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }
            System.out.println();
        }
    }
}
