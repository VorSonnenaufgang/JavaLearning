package ink.vor.java;

/**
 * @author muquanrui
 * @date 01/03/2022 13:13
 */

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 一、流的分类：
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类         节点流（或文件流）                               缓冲流（处理流的一种）
 * InputStream     FileInputStream   (read(byte[] buffer))        BufferedInputStream (read(byte[] buffer))
 * OutputStream    FileOutputStream  (write(byte[] buffer,0,len)  BufferedOutputStream (write(byte[] buffer,0,len) / flush()
 * Reader          FileReader (read(char[] cbuf))                 BufferedReader (read(char[] cbuf) / readLine())
 * Writer          FileWriter (write(char[] cbuf,0,len)           BufferedWriter (write(char[] cbuf,0,len) / flush()
 */
public class FileReaderWriterTest {
    /**
     * FileReader:
     * 1.实例化File类的对象，指明要操作的文件
     * 2.提供具体的流
     * 3.数据的读入
     * 4.流的关闭操作
     *
     * 说明点：
     * 1. read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
     * 2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
     * 3. 读入的文件一定要存在，否则就会报FileNotFoundException。
     *
     * read(char[] cbuf):返回每次读入cbuf数组中的字符的个数。如果达到文件末尾，返回-1
     * 注意输出时的下标一定要用读到的具体字符数！（末尾多余输出问题）
     */
    @Test
    public void testFileReader() {
        FileReader fr = null;
        try {
            File file = new File("hello.txt");
            fr = new FileReader(file);
            // int data;
            // while ((data = fr.read()) != -1) {
            //     System.out.print((char)data);
            // }
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                System.out.print(new String(cbuf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * FileWriter：
     * 1.提供File类的对象，指明写出到的文件
     * 2.提供FileWriter的对象，用于数据的写出
     * 3.写出的操作
     * 4.流资源的关闭
     *
     * 说明：
     * 1. 输出操作，对应的File可以不存在的。并不会报异常
     * 2. File对应的硬盘中的文件如果不存在：在输出的过程中，会自动创建此文件。
     *    File对应的硬盘中的文件如果存在：
     *          如果流使用的构造器是：FileWriter(file,false) / FileWriter(file):对原有文件的覆盖
     *          如果流使用的构造器是：FileWriter(file,true):不会对原有文件覆盖，而是在原有文件基础上追加内容
     */
    @Test
    public void testFileWriter() {
        FileWriter fw = null;
        try {
            File file = new File("hello1.txt");
            fw = new FileWriter(file, true);
            fw.write("I have a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testCopy() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            File src = new File("hello.txt");
            File dest = new File("hello1.txt");
            fr = new FileReader(src);
            fw = new FileWriter(dest);
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                fw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}