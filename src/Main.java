import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.event.TreeModelEvent;

public class Main extends Application {

    private TreeModelEvent root;
    private ListView ListView;


    @Override
    public void start(Stage stage) throws Exception {

        BorderPane outsideBorderPane = new BorderPane();
        outsideBorderPane.setStyle("-fx-background-color: green;");

        Scene scene = new Scene(outsideBorderPane); //1024, 768);

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
        //BorderPane within Top
        BorderPane topBorderPane = new BorderPane();
        topBorderPane.setPadding(new Insets(40));
        topBorderPane.setStyle("-fx-background-color: red;");
        outsideBorderPane.setTop(topBorderPane);

        //SEARCH BAR

        TextField searchbar = new TextField();
        searchbar.setPromptText("Search album, song or album");
        searchbar.setPrefSize(100, 20);
        topBorderPane.setCenter(searchbar);

        //LOGOUT BUTTON

        Button logoutButton = new Button("Log Out");
        logoutButton.setPrefSize(100, 20);
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
        leftHandVBox.setSpacing(8);
        leftHandVBox.setStyle("-fx-background-color:purple");

        //leftHandVBox = new VBox(10);

        Button[] myButtons;
        myButtons = new Button[6];


        myButtons[0] = new Button("Playlist 1");
        myButtons[0].setPrefSize(200, 10);

        myButtons[1] = new Button("Playlist 2");
        myButtons[1].setPrefSize(200, 10);

        myButtons[2] = new Button("Playlist 3");
        myButtons[2].setPrefSize(200, 10);

        myButtons[3] = new Button("Create A New Playlist");
        myButtons[3].setPrefSize(200, 10);

        myButtons[4] = new Button("Saved Music");
        myButtons[4].setPrefSize(200, 10);

        myButtons[5] = new Button("Favourite Songs");
        myButtons[5].setPrefSize(200, 10);


        leftHandVBox.getChildren().addAll(myButtons);

        outsideBorderPane.setLeft(leftHandVBox);

        // RIGHT OF MAIN BORDER PANE

        VBox rightHandVBox = new VBox();
        rightHandVBox.setPadding(new Insets(10));
        rightHandVBox.setSpacing(8);
        rightHandVBox.setStyle("-fx-background-color: Pink;");

        subheading = new Label("Similar Songs");
        subheading.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        rightHandVBox.getChildren().add(subheading);

        //boxOfButtons = new VBox(10);

        myButtons = new Button[5];

        myButtons[0] = new Button("Similar Song 1");
        myButtons[0].setPrefSize(200, 10);

        myButtons[1] = new Button("Similar Song 2");
        myButtons[1].setPrefSize(200, 10);

        myButtons[2] = new Button("Similar Song 3");
        myButtons[2].setPrefSize(200, 10);

        myButtons[3] = new Button("Settings");
        myButtons[3].setPrefSize(200, 10);

        myButtons[4] = new Button("Languages");
        myButtons[4].setPrefSize(200, 10);

        //boxOfButtons.getChildren().addAll(myButtons);

        //rightHandVBox.getChildren().add(boxOfButtons);

        outsideBorderPane.setRight(rightHandVBox);

        //BorderPane within Centre
        BorderPane centreBorderPane = new BorderPane();
        centreBorderPane.setPadding(new Insets(40));
        centreBorderPane.setStyle("-fx-background-color: yellow;");
        outsideBorderPane.setCenter(centreBorderPane);

        subheading = new Label("PLAYLIST 1");
        subheading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        centreBorderPane.setTop(subheading);

        ListView = new ListView();
        ListView.getItems().addAll("Pain - Jimmy Eat World", "Obstacle 1 - Interpol ", "Obstacle 1 - Interpol ", "Obstacle 1 - Interpol ", "Obstacle 1 - Interpol ");
        ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        centreBorderPane.setCenter(ListView);


        BorderPane BottomBorderPane = new BorderPane();
        BottomBorderPane.setPadding(new Insets(40));
        BottomBorderPane.setStyle("-fx-background-color: orange;");
        outsideBorderPane.setBottom(BottomBorderPane);


    }


    public static void main(String[] args) {
        launch(args);
    }
}