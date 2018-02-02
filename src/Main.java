
import javafx.application.Application;
import javafx.event.ActionEvent;
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

public class Main extends Application
{

    private BorderPane root = new BorderPane();
    private ListView listView;
    private VBox boxOfButtons;
    private HBox setOfButtons;
    private DBConn dbConn = new DBConn();
    private MP3Player player;
    @Override
    public void start(Stage stage) throws Exception
    {
        player  = new MP3Player();
        BorderPane outsideBorderPane = new BorderPane();
        outsideBorderPane.setStyle("-fx-background-color: green;");

        Scene scene = new Scene(outsideBorderPane); //1024, 768);

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
        //BorderPane within To
        BorderPane topBorderPane = new BorderPane();
        topBorderPane.setPadding(new Insets(40));
        topBorderPane.setStyle("-fx-background-color: red;");
        outsideBorderPane.setTop(topBorderPane);

        /// /SEARCH BAR

        TextField searchbar = new TextField();
        searchbar.setPromptText("Search album, song or album");
        searchbar.setPrefSize(100, 20);
        BorderPane.setAlignment(searchbar, Pos.CENTER);
        topBorderPane.setCenter(searchbar);

        //LOGO
        final FileInputStream file = new FileInputStream("U:\\Logo.png");
        final Image image = new Image(file);
        final ImageView imageView = new ImageView(image);
        imageView.setFitHeight(75);
        imageView.setFitWidth(125);
        topBorderPane.setLeft(imageView);

        //LOGOUT BUTTON

        Button logoutButton = new Button("Log Out");
        logoutButton.setPrefSize(100, 20);
        BorderPane.setAlignment(logoutButton, Pos.CENTER_RIGHT);
        topBorderPane.setRight(logoutButton);

        // LEFT OF MAIN BORDER PANE
        VBox leftHandVBox = new VBox();

        BorderPane LeftBorderPane = new BorderPane();
        LeftBorderPane.setPadding(new Insets(40));
        LeftBorderPane.setStyle("-fx-background-color: blue");
        outsideBorderPane.setLeft(LeftBorderPane);

        Label subheading = new Label( "Playlists");
        subheading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        leftHandVBox.getChildren().add(subheading);


        leftHandVBox.setPadding(new Insets(10));
        leftHandVBox.setSpacing(20);
        leftHandVBox.setStyle("-fx-background-color:purple");

        //leftHandVBox = new VBox(10);

        Button[] myButtons;
        myButtons = new Button[4];


        myButtons[0] = new Button("Playlist 1");
        myButtons[0].setPrefSize(200, 10);

        myButtons[1] = new Button("Playlist 2");
        myButtons[1].setPrefSize(200, 10);

        myButtons[2] = new Button("Playlist 3");
        myButtons[2].setPrefSize(200, 10);

        myButtons[3] = new Button("Create A New Playlist");
        myButtons[3].setPrefSize(200, 10);

        leftHandVBox.getChildren().addAll(myButtons);

        outsideBorderPane.setLeft(leftHandVBox);

        // RIGHT OF MAIN BORDER PANE

        VBox rightHandVBox = new VBox();
        rightHandVBox.setPadding(new Insets(10));
        rightHandVBox.setSpacing(20);
        rightHandVBox.setStyle("-fx-background-color: Pink;");

        subheading = new Label("Customise");
        subheading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        rightHandVBox.getChildren().add(subheading);

        boxOfButtons = new VBox(20);

        myButtons = new Button[4];

        myButtons[0] = new Button("Add To playlist");
        myButtons[0].setPrefSize(200, 10);

        myButtons[1] = new Button("Remove From Playlist");
        myButtons[1].setPrefSize(200, 10);

        myButtons[2] = new Button("Music Video");
        myButtons[2].setPrefSize(200, 10);

        myButtons[3] = new Button("Song Lyrics");
        myButtons[3].setPrefSize(200, 10);

        boxOfButtons.getChildren().addAll(myButtons);

        rightHandVBox.getChildren().add(boxOfButtons);

        outsideBorderPane.setRight(rightHandVBox);

        //BorderPane within Centre
        BorderPane centreBorderPane = new BorderPane();
        centreBorderPane.setPadding(new Insets(40));
        centreBorderPane.setStyle("-fx-background-color: yellow;");
        outsideBorderPane.setCenter(centreBorderPane);

        subheading = new Label("PLAYLIST 1");
        subheading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        centreBorderPane.setTop(subheading);

        listView = new ListView();
        player.loadAllSongs();
        listView.getItems().addAll(dbConn.allSongNames());
        listView.getSelectionModel().selectedIndexProperty().addListener( (dontNeedThis, dontNeedThisEither, index) -> {
            final int indexAsInt = (int) index;
            if (indexAsInt >= 0 && indexAsInt < listView.getItems().size()) { player.skipTo(indexAsInt); System.out.println("hi"); }
        });
        centreBorderPane.setCenter(listView);

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

        setOfButtons = new HBox(10);

        myButtons = new Button[6];

        myButtons[0] = new Button("Shuffle");
        myButtons[0].setOnAction((ActionEvent ae) -> player.shuffle());
        myButtons[0].setPrefSize(50, 50);

        myButtons[1] = new Button("Last Song");
        myButtons[1].setOnAction((ActionEvent ae) -> player.skipBackward());
        myButtons[1].setPrefSize(50, 50);

        myButtons[2] = new Button("Pause");
        myButtons[2].setOnAction((ActionEvent ae) -> player.pause());
        myButtons[2].setPrefSize(50, 50);

        myButtons[3] = new Button("Play");
        myButtons[3].setOnAction((ActionEvent ae) -> player.play());
        myButtons[3].setPrefSize(50, 50);

        myButtons[4] = new Button("Next Song");
        myButtons[4].setOnAction((ActionEvent ae) -> player.skipForward());
        myButtons[4].setPrefSize(50, 50);

        myButtons[5] = new Button("Repeat");
        myButtons[5].setOnAction((ActionEvent ae) -> player.setRepeating());
        myButtons[5].setPrefSize(50, 50);

        setOfButtons.getChildren().addAll(myButtons);

        bottomHandHBox.getChildren().add(setOfButtons);

        outsideBorderPane.setBottom(bottomHandHBox);

        //slider for song length
        Slider songlengthslider = new Slider();
        songlengthslider.setMin(0);
        songlengthslider.setMax(100);
        songlengthslider.setValue(50);
        // songlengthslider.valueProperty().addListener(() -> ); DO THIS IN A BIT

        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefSize(400, 30);
        progressBar.setProgress(0.5);

        songlengthslider.valueProperty().addListener(
                (observable, old_value, new_value) -> progressBar.setProgress(new_value.doubleValue() / 100));

       bottomHandHBox.getChildren().add(songlengthslider);
        dbConn.close();
    }


    public static void main(String[] args) {
        launch(args);
    }}