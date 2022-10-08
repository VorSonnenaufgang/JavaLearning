package ink.vor.java;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author muquanrui
 * @date 2022/8/10 23:14
 */
public class SpotifyUtil {
    public static void getSpotifyAlbumCover(String urlString) {
        BufferedReader br = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        HttpURLConnection connection = null;
        try {
            // 获取cover地址
            URL url = new URL("https://open.spotify.com/oembed?url=" + urlString);
            connection = (HttpURLConnection) url.openConnection();
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str = "";
            String current;
            while((current = br.readLine()) != null) {
                str += current;
            }
            str = str.substring(1, str.length() - 1);
            String[] infos = str.split(",");
            Map<String, String> infoMap = new HashMap<>();
            for (String info : infos) {
                int dash = 0;
                for (; dash < info.length(); dash++) {
                    if (info.charAt(dash) == ':') {
                        break;
                    }
                }
                infoMap.put(info.substring(1, dash - 1), info.substring(dash + 2, info.length() - 1));
            }
            // 下载cover
            url = new URL(infoMap.get("thumbnail_url"));
            connection = (HttpURLConnection) url.openConnection();
            bis = new BufferedInputStream(connection.getInputStream());
            bos =  new BufferedOutputStream(new FileOutputStream("/Users/muquanrui/Desktop/cover.jpeg"));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            System.out.println("Download finished.");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
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
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
