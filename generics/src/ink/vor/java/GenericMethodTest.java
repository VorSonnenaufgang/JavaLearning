package ink.vor.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author muquanrui
 * @date 28/02/2022 19:21
 */

/**
 * 泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。
 * 换句话说，泛型方法所属的类是不是泛型类都没有关系。
 * 泛型方法，可以声明为静态的。原因：泛型参数是在调用方法时确定的。并非在实例化类时确定。
 */

public class GenericMethodTest {
    @Test
    public void TestGeneric() {
        User user = new User();
        System.out.println(user.copyList(new Integer[]{1, 2, 3, 4}));
        System.out.println(User.copyList(new String[]{"we", "stand", "up"}));
    }
}

class User {
    String username;
    String password;

    public static <E> List<E> copyList(E[] arr) {
        List<E> list = new ArrayList<>(Arrays.asList(arr));
        return list;
    }
}
