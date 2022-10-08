package ink.vor.jvm.stringtable;

/**
 * @author muquanrui
 * @date 2022/10/4 20:59
 */

public class StringTableTest {
    public static void main(String[] args) {
        String str1 = new StringBuilder("re").append("dis").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        System.out.println("=============================");

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());
    }
}
