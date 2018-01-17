package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConn
{
    private String pathToDatabase;
    private Boolean isOpen = true;
    private Connection connection;

    public DBConn(String dbName) throws SQLException, ClassNotFoundException
    {
        this.pathToDatabase = dbName;
        connection = DriverManager.getConnection("jdbc:sqlite:" + pathToDatabase);
    }

    public ResultSet resultSetFromQuery(String query) throws SQLException { return connection.createStatement().executeQuery(query); }
    public void executeChangeQuery(String query) throws SQLException { connection.createStatement().executeUpdate(query); }
    public void close() throws SQLException
    {
        connection.close();
        isOpen = false;
    }

    public ArrayList<Song> songsFromPlaylist(String playlistName) throws Exception
    {
        if (!isOpen) throw new Exception("Isn't open");
        final ResultSet resultSet = resultSetFromQuery("SELECT * FROM Songs JOIN Playlist_Songs ON Songs.songID = Playlist_Songs,songID WHERE Playlist_Songs.playlistName = '" + playlistName + "'");
        final ArrayList<Song> songs = new ArrayList<Song>();
        while (resultSet.next())
        {
            songs.add(new Song(
                    resultSet.getInt("songID"),
                    resultSet.getString("title"),
                    resultSet.getNString("artist"),
                    resultSet.getNString("album"),
                    resultSet.getString("filepath")
            ));
        }
        return songs;
    }
}
