package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConn
{
    private Boolean isOpen = true;
    private Connection connection;

    public DBConn()
    {
        try { connection = DriverManager.getConnection("jdbc:sqlite:" + "U://Coputer Science//Coursework//NikolaisVersion.db"); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public ResultSet resultSetFromQuery(String query) throws SQLException { return connection.createStatement().executeQuery(query); }
    public void executeChangeQuery(String query) throws SQLException { connection.createStatement().executeUpdate(query); }
    public void close() throws SQLException
    {
        connection.close();
        isOpen = false;
    }

    public ArrayList<Song> songsFromPlaylist(Playlist playlist)
    {
        ResultSet resultSet;
        try
        { resultSet = resultSetFromQuery("SELECT * FROM Songs " +
                "JOIN Playlists_Songs ON Songs.songID = Playlists_Songs.songID " +
                "WHERE Playlists_Songs.playlistName = '" + playlist.getPlaylistName() + "'");
        }
        catch (Exception e) { e.printStackTrace(); return null; }
        final ArrayList<Song> songs = new ArrayList<>();
        try
        {
            while (resultSet.next())
            {
                songs.add(new Song(
                        resultSet.getInt("songID"),
                        resultSet.getString("title"),
                        resultSet.getString("album"),
                        resultSet.getString("artist"),
                        resultSet.getInt("length")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); return null; }
        return songs;
    }

    public ArrayList<Playlist> allPlaylistsOwnedBy(User user)
    {
        ResultSet resultSet;
        try { resultSet = resultSetFromQuery("SELECT * FROM Playlists WHERE Playlists.username = '" + user.getUsername() + "';"); }
        catch (Exception e) { e.printStackTrace(); return null; }

        final ArrayList<Playlist> playlists = new ArrayList<>();
        try
        {
            while (resultSet.next())
            {
                playlists.add(new Playlist(
                        resultSet.getString("playlistName"),
                        resultSet.getString("username"),
                        resultSet.getBoolean("isUserEditable")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); return null; }
        return playlists;
    }
}
