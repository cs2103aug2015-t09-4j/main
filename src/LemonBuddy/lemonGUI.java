package LemonBuddy;

import java.io.IOException;
import java.util.ArrayList;

import LemonBuddy.view.LemonGUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class lemonGUI extends Application {

    private Stage primaryStage;
    private AnchorPane LemonGUI;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LemonBuddy");
        
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));

        initRootLayout();

        //showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(lemonGUI.class.getResource("view/LemonGUI.fxml"));
            LemonGUI = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(LemonGUI);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
//    public void showPersonOverview() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(lemonGUI.class.getResource("view/LemonGUI.fxml"));
//            AnchorPane personOverview = (AnchorPane) loader.load();
//
//            // Set person overview into the center of root layout.
//            rootLayout.setCenter(personOverview);
//            
//            LemonGUIController controller = loader.getController();
//            controller.setMainApp(this);
//            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
    
    
}
		
