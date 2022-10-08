package ink.vor.java;

/**
 * @author muquanrui
 * @date 2022/8/1 09:56
 */
public class Test {
    public static void main(String[] args) {
        B b = new B();
    }
}

class A {
    static {
        System.out.println("Static A");
    }
    public A() {
        System.out.println("A");
    }
}

class B extends A {
    static {
        System.out.println("Static B");
    }
    public B() {
        System.out.println("B");
    }
}