package ink.vor.java3;

/**
 * @author muquanrui
 * @date 04/03/2022 16:30
 */

/**
 * 静态代理举例
 * 特点：代理类和被代理类在编译期间，就确定下来了。
 */
interface ClothesFactory {
    void produceClothes();
}

// 代理类
class ProxyClothesFactory implements ClothesFactory {
    // 用被代理类对象进行实例化
    private ClothesFactory factory;

    public ProxyClothesFactory(ClothesFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceClothes() {
        System.out.println("代理工厂做一些准备工作");
        factory.produceClothes();
        System.out.println("代理工厂做一些后续的收尾工作");
    }
}

// 被代理类
class NikeClothesFactory implements ClothesFactory {

    @Override
    public void produceClothes() {
        System.out.println("Nike工厂生产一批运动服");
    }
}

public class StaticProxyTest {
    public static void main(String[] args) {
        ProxyClothesFactory proxyClothesFactory = new ProxyClothesFactory(new NikeClothesFactory());
        proxyClothesFactory.produceClothes();
    }
}
