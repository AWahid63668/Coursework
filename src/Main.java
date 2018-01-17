package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.ProgressBar;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application
{

    private final BorderPane root = new BorderPane();
    private static final ListView mainListView = new ListView();
    private static final DBConn dbConn = new DBConn();
    private static User currentUser = new User("NICK", "is_a_god");
    private static Playlist currentPlaylist = new Playlist("All Songs", currentUser.getUsername(), false);

    @Override
    public void start(Stage stage) throws Exception
    {
        final BorderPane outsideBorderPane = new BorderPane();
        outsideBorderPane.setStyle("-fx-background-color: green;");

        final Scene scene = new Scene(outsideBorderPane, 1024, 768);
        stage.setTitle("Hello World");
        stage.setScene(scene);

        //BorderPane within Top
        final BorderPane topBorderPane = new BorderPane();
        topBorderPane.setPadding(new Insets(40));
        topBorderPane.setStyle("-fx-background-color: red;");
        outsideBorderPane.setTop(topBorderPane);

        // SEARCH BAR
        final TextField searchbar = new TextField();
        searchbar.setPromptText("Search album, song or album");
        searchbar.setPrefSize(100, 20);
        BorderPane.setAlignment(searchbar, Pos.CENTER);
        topBorderPane.setCenter(searchbar);

        // LOGO
        final FileInputStream file = new FileInputStream("U:\\Logo.png");
        final Image image = new Image(file);
        final ImageView imageView = new ImageView(image);
        imageView.setFitHeight(75);
        imageView.setFitWidth(125);
        topBorderPane.setLeft(imageView);

        // LOGOUT BUTTON
        final Button logoutButton = new Button("Log Out");
        logoutButton.setPrefSize(100, 20);
        BorderPane.setAlignment(logoutButton, Pos.CENTER_RIGHT);
        topBorderPane.setRight(logoutButton);

        // LEFT OF MAIN BORDER PANE
        final VBox leftHandVBox = new VBox();
        leftHandVBox.setPadding(new Insets(10));
        leftHandVBox.setSpacing(20);
        leftHandVBox.setStyle("-fx-background-color:purple");
        outsideBorderPane.setLeft(leftHandVBox);

        // Playlist heading
        final Label playlistHeading = new Label( currentUser.getUsername() + "'s playlists");
        playlistHeading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        leftHandVBox.getChildren().add(playlistHeading);

        // Playlist buttons
        final ArrayList<Playlist> allPlaylists = dbConn.allPlaylistsOwnedBy(currentUser);
        for (final Playlist playlist : allPlaylists)
        {
            Button button = new Button(playlist.getPlaylistName());
            button.setMinSize(200, 10);
            leftHandVBox.getChildren().add(button);
        }

        // Create New Playlist Button
        final Button newPlaylistButton = new Button("Create a new playlist!");
        newPlaylistButton.setMinSize(200, 10);
        leftHandVBox.getChildren().add(newPlaylistButton);

        // RIGHT OF MAIN BORDER PANE -- PLAYLIST OPTION BUTTONS
        VBox rightHandVBox = new VBox();
        rightHandVBox.setPadding(new Insets(10));
        rightHandVBox.setSpacing(20);
        rightHandVBox.setStyle("-fx-background-color: Pink;");

        // Customise subheading
        final Label rightSubHeading = new Label("Customise");
        rightSubHeading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        rightHandVBox.getChildren().add(rightSubHeading);

        // Playlist options buttons

        final VBox playlistOptionBox = new VBox(20);
        {
            final String[] optionButtonHeaders = {"Add to playlist", "Remove from playlist", "Music Video", "Song Lyrics"};
            for (int i = 0; i < 4; i++)
            {
                final Button button = new Button(optionButtonHeaders[i]);
                button.setMinSize(200, 10);
                playlistOptionBox.getChildren().add(button);
            }
        }

        rightHandVBox.getChildren().add(playlistOptionBox);
        outsideBorderPane.setRight(rightHandVBox);

        // CENTRE PLAYLIST BORDER PANE
        BorderPane centreBorderPane = new BorderPane();
        centreBorderPane.setPadding(new Insets(40));
        centreBorderPane.setStyle("-fx-background-color: yellow;");
        outsideBorderPane.setCenter(centreBorderPane);

        // Current playlist heading
        final Label currentPlaylistHeading = new Label(currentPlaylist.getPlaylistName());
        currentPlaylistHeading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        centreBorderPane.setTop(currentPlaylistHeading);

        // Populating main list view
        mainListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        centreBorderPane.setCenter(mainListView);

        //Creating BottomBorderPane
        BorderPane bottomBorderPane = new BorderPane();
        bottomBorderPane.setPadding(new Insets(40));
        bottomBorderPane.setStyle("-fx-background-color: orange;");
        outsideBorderPane.setBottom(bottomBorderPane);

        //Creating Pause/ play etc etc Buttons
        HBox bottomHandHBox = new HBox();
        bottomHandHBox.setPadding(new Insets(10));
        bottomHandHBox.setSpacing(20);
        bottomHandHBox.setStyle("-fx-background-color: orange;");

        // Playlist options buttons
        final HBox optionButtonsBox = new HBox(100);
        final Button[] optionButtons = new Button[6];
        {
            final String[] optionButtonNames = {"Shuffle", "Last Song", "Pause", "Play", "Next Song", "Repeat"};
            for (int i = 0; i < optionButtons.length; i++)
            {
                optionButtons[i] = new Button(optionButtonNames[i]);
                optionButtons[i].setMinSize(70, 20);
            }
        }

        optionButtonsBox.getChildren().addAll(optionButtons);
        bottomHandHBox.getChildren().add(optionButtonsBox);
        bottomBorderPane.setTop(bottomHandHBox);

        // song progress bar
        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefSize(250, 10);
        progressBar.setProgress(0.5);
        bottomBorderPane.setBottom(progressBar);
        BorderPane.setAlignment(progressBar, Pos.CENTER);

        //slider for song length
        Slider songSlider = new Slider();
        songSlider.setMin(0);
        songSlider.setMax(100);
        songSlider.setValue(50);
        songSlider.valueProperty().addListener((observable, old, newVal) -> progressBar.setProgress(newVal.doubleValue() / 100));
        bottomBorderPane.setCenter(songSlider);
        BorderPane.setAlignment(songSlider, Pos.CENTER);

        updateListView(currentPlaylist);
        stage.show();
    }

    private static void updateListView(Playlist playlist)
    {
        for (Song song : dbConn.songsFromPlaylist(playlist)) { mainListView.getItems().add(song.getTitle()); }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        launch(args);
    }
}