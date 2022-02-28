package ink.vor.java;

/**
 * @author muquanrui
 * @date 28/02/2022 13:59
 */

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
    @Test
    public void testProperties() throws IOException {
        Properties pros = new Properties();
        FileInputStream fis = new FileInputStream("jdbc.properties");
        pros.load(fis);
        String name = pros.getProperty("name");
        String password = pros.getProperty("password");
        System.out.println(name + "--" + password);
    }
}
