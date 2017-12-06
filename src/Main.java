import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.layoutInArea;


public class Main extends Application {

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

        TextField searchbar = new TextField("Search album, song or album");
        searchbar.setPrefSize(100, 20);
        topBorderPane.setCenter(searchbar);

        //LOGOUT BUTTON

        Button logoutButton = new Button("Log Out");
        logoutButton.setPrefSize(100, 20);
        topBorderPane.setRight(logoutButton);

        // LEFT OF MAIN BORDER PANE

        VBox leftHandVBox = new VBox();
        leftHandVBox.setPadding(new Insets(10));
        leftHandVBox.setSpacing(8);

        Text title = new Text("Playlists");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        leftHandVBox.getChildren().add(title);

        outsideBorderPane.setLeft(leftHandVBox);

    }

    public static void main(String[] args) {
        launch(args);
    }
}