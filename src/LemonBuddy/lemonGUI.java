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

    private static CommandExecutor commandexecutor;
	private Stage primaryStage;
    private BorderPane rootLayout;
    private static ArrayList<Task> listForDisplay = new ArrayList<Task>();
    private String[] command = {"", ""};
	private static ArrayList<Task> listForTimeline = new ArrayList<Task>();


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LemonBuddy");
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));

        initRootLayout();

        showLemonGUI();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(lemonGUI.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the GUI inside the root layout.
     */
    public void showLemonGUI() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(lemonGUI.class.getResource("view/LemonGUI.fxml"));
            AnchorPane lemonGUI = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setTop(lemonGUI);
            
            LemonGUIController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) throws Exception {
    	if (commandexecutor == null) {
			commandexecutor = new CommandExecutor();
		}
    	commandexecutor.passToGUI();
        launch(args);
    }
    
    public void setListForDisplay(ArrayList<Task> list) {
    	listForDisplay = list;
    }
    
    public void setListForTimeline(ArrayList<Task> list) {
    	listForTimeline = list;
    }
    
    public ArrayList<Task> getListForDisplay() {
    	
    	for (int counter = 0; counter < listForDisplay.size(); counter++) {
    		System.out.println(listForDisplay.get(counter).getTaskIsNewest());
    		if (listForDisplay.get(counter).getTaskIsNewest()) {
    			listForDisplay.get(counter).setDisplayId("#" + (counter + 1));
    		} else if (listForDisplay.get(counter).getTaskType().equals("overdue")){
    			listForDisplay.get(counter).setDisplayId("^" + (counter + 1));
    		} else {
    			listForDisplay.get(counter).setDisplayId("" + (counter + 1));
    		}
    	}
    	return listForDisplay;
    }
    
    public ArrayList<Task> getListForTimeline() {
    	return listForTimeline;
    }
    
    public void setCommand(String[] command) {
    	this.command = command;
    }

	public void update() throws Exception {
		commandexecutor.passToGUI();
		commandexecutor.updateLists();
	}
    
}
		
