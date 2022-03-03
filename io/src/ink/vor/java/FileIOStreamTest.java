package ink.vor.java;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author muquanrui
 * @date 01/03/2022 13:57
 */

/**
 * 使用字节流FileInputStream处理文本文件，可能出现乱码。使用字符流FileReader处理非文本文件，也会乱码。
 * 结论：
 * 1. 对于文本文件(.txt,.java,.c,.cpp)，使用字符流处理
 * 2. 对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字节流处理
 */
public class FileIOStreamTest {
    @Test
    public void testFileInputStream() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File file = new File("爱情与友情.jpg");
            fis = new FileInputStream(file);
            fos = new FileOutputStream(new File("爱情与友情copy.jpg"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
