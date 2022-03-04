package ink.vor.java3;

/**
 * @author muquanrui
 * @date 04/03/2022 16:42
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的举例
 */
interface HumanBehavior {
    String getBelief();
    void eat(String food);
}

// 被代理类
class SuperMan implements HumanBehavior {

    @Override
    public String getBelief() {
        return "I believe I can fly";
    }

    @Override
    public void eat(String food) {
        System.out.println("I like eating " + food);
    }
}

class HumanUtil {
    public void method1(){
        System.out.println("====================通用方法一====================");
    }

    public void method2(){
        System.out.println("====================通用方法二====================");
    }
}

/*
要想实现动态代理，需要解决的问题？
问题一：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象。
问题二：当通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。
 */

class ProxyFactory {
    // 调用此方法，返回一个代理类的对象，解决问题一
    public static Object getProxyFactory(Object obj) { // obj：被代理类对象
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {
    // 使用被代理类对象进行赋值
    private Object obj;

    public void bind(Object obj) {
        this.obj = obj;
    }

    // 当我们通过代理类的对象调用方法a时，就会自动调用下面的invoke()。
    // 将被代理类要执行的方法a的功能声明在invoke()中，解决问题2。
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanUtil util = new HumanUtil();
        util.method1();
        // method：即为代理类对象调用的方法，此方法就作为被代理类对象要调用的方法
        // obj：被代理类对象
        Object returnVal = method.invoke(obj, args);
        util.method2();
        // 上述方法的返回值就作为当前类中的invoke()的返回值。
        return returnVal;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        // proxyInstance: 代理类的对象
        HumanBehavior proxyInstance = (HumanBehavior) ProxyFactory.getProxyFactory(new SuperMan());
        // 当通过代理类对象调用方法时，会自动的调用被代理类中同名的方法
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("四川麻辣烫");

        ClothesFactory proxyInstance1 = (ClothesFactory) ProxyFactory.getProxyFactory(new NikeClothesFactory());
        proxyInstance1.produceClothes();
    }
}
