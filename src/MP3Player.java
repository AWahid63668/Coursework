package sample;

import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class MP3Player
{
    private final ArrayList<MediaPlayer> queue = new ArrayList<>();
    private Boolean playing = false;
    private int songIndex = 0;
    private final String ROOT_DIR = "U:\\Coputer Science\\Coursework\\Songs";
    public Boolean isPlaying() { return playing; }

    public MP3Player(Playlist playlist)
    {
        final DBConn dbConn = new DBConn();
        for (final Song song : dbConn.songsFromPlaylist(playlist)) {  }
    }
}
