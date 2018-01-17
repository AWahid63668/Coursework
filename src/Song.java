package sample;

public class Song
{
    private int songID;
    private String title;
    private String artist;
    private String album;
    private String filepath;

    public int getSongID() {
        return songID;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getFilepath() {
        return filepath;
    }

    public Song(int songID, String title, String artist, String album, String filepath)
    {
        this.songID = songID;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.filepath = filepath;
    }
}

