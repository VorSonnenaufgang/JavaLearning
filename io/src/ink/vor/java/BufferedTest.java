package ink.vor.java;

/**
 * @author muquanrui
 * @date 01/03/2022 14:21
 */

import org.junit.Test;

import java.io.*;

/**
 * 处理流之一：缓冲流的使用
 *
 * 1. 缓冲流：
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 *
 * 2. 作用：提供流的读取、写入的速度
 *      提高读写速度的原因：内部提供了一个缓冲区
 *
 * 3. 处理流，就是“套接”在已有的流的基础上。
 *
 * 资源关闭要求：先关闭外层的流，再关闭内层的流
 * 说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，我们可以省略
 */
public class BufferedTest {
    @Test
    public void testBufferedIOStream() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            FileInputStream fis = new FileInputStream(new File("爱情与友情.jpg"));
            FileOutputStream fos = new FileOutputStream(new File("爱情与友情copy2.jpg"));
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testBufferedReaderWriter() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            FileReader fr = new FileReader(new File("dbcp.txt"));
            FileWriter fw = new FileWriter(new File("dbcp1.txt"));
            br = new BufferedReader(fr);
            bw = new BufferedWriter(fw);
            // char[] cbuf = new char[1024];
            // int len;
            // while ((len = br.read(cbuf)) != -1) {
            //     bw.write(cbuf, 0, len);
            // }
            String data;
            while ((data = br.readLine()) != null) {
                // bw.write(data + "\n");
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
