package ink.vor.java;

/**
 * @author muquanrui
 * @date 2022/8/10 23:15
 */
public class SpotifyTest {
    public static void main(String[] args) {
        String url = "https://open.spotify.com/album/4kwxu2ivSzsZpxiPvhAoVx?si=Eri9pUqaTd241lalLqla9g";
        SpotifyUtil.getSpotifyAlbumCover(url);
    }
}
