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

    private static int timelineIndex = 0;
	private static int mainDisplayIndex = 0;
	private static CommandController commandcontroller = null;
	private Stage primaryStage;
    private BorderPane rootLayout;
    private static ArrayList<Task> listForDisplay;
    private String[] command = {"", ""};
	private static ArrayList<Task> listForTimeline;


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
    	if (commandcontroller == null) {
    		commandcontroller  = new CommandController();
		}
    	updateDisplayList();
        launch(args);
    }
    
    public void setListForDisplay(ArrayList<Task> list) {
    	listForDisplay = list;
    }
    
    public void setListForTimeline(ArrayList<Task> list) {
    	System.out.println(list);
    	listForTimeline = list;
    }
    
    public ArrayList<Task> getListForDisplay() {
    	for (int counter = 0; counter < listForDisplay.size(); counter++) {
    		if (listForDisplay.get(counter).getTaskIsNewest()) {
    			listForDisplay.get(counter).setDisplayId("#" + (counter + 1));
    			mainDisplayIndex = counter;
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
    
    public static void updateDisplayList() throws Exception {
    	ArrayList<ArrayList<Task>> temp = commandcontroller.passListsToGUI();
    	listForTimeline = temp.get(0);
    	listForDisplay = temp.get(1);
    	System.out.println("help" + temp.get(1));
    }
    
    public void getCommand(String input) {
    	commandcontroller.processCommand(input);
    }
    
    public int getMainDisplayIndex() {
    	int temp = mainDisplayIndex;
    	mainDisplayIndex = 0;
    	return temp;
    }
    
    public int getTimelineIndex() {
    	return timelineIndex;
    }
    
    public String getDate() {
    	String temp = commandcontroller.getDate();
    	String finalDate = temp.substring(0,2) + "/" + temp.substring(2,4) + "/" + temp.substring(4,6);
    	return finalDate;
    }
    
    public String getMainDisplayHeader() {
    	String list = commandcontroller.getListType();
    	if (list.equals("date")) {
    		return "Tasks on: " + getDate();
    	} else if (list.equals("event")) {
    		return "Events";
    	} else if (list.equals("floating")) {
    		return "Floating Tasks";
    	} else if (list.equals("deadline")) {
    		return "Deadline Tasks";
    	} else if (list.equals("overdue")){
    		return "Overdue Tasks";
    	} else if (list.equals("done")) {
    		return "Done Tasks";
    	} else if (list .equals("all")) {
    		return "All Events and Tasks";
    	} else {
    		return "Search Results";
    	}
    }
    
    public String getNotificationBarDisplay() {
    	return commandcontroller.getNotificationMessage();
    }
    
}
		
