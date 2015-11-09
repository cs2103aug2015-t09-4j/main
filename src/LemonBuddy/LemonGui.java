
//@author A0121814R
package LemonBuddy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import LemonBuddy.view.LemonGuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LemonGui extends Application {

    private static int timelineIndex = 0;
	private static int mainDisplayIndex = 0;
	private static CommandController commandcontroller;
	private Stage primaryStage;
    private BorderPane rootLayout;
    private static ArrayList<Task> listForDisplay;
    private static ArrayList<Task> listForTimeline;
	private static Logger logger = Logger.getLogger("GUI");


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LemonBuddy");
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
        initRootLayout();
        showLemonGui();
    }

    
     // Initializes the root layout.
     
    public void initRootLayout() {
        try {
        	logger.log(Level.INFO, "Initializing RootLayout");
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LemonGui.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            logger.log(Level.INFO, "Completed Initializing RootLayout");
        } catch (IOException e) {
        	logger.log(Level.INFO, "Initializing ERROR");
            e.printStackTrace();
        }
    }

    //Shows the GUI inside the root layout.
    public void showLemonGui() {
        try {
        	logger.log(Level.INFO, "Initializing LemonGui");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LemonGui.class.getResource("view/LemonGUI.fxml"));
            AnchorPane lemonGui = (AnchorPane) loader.load();

            rootLayout.setTop(lemonGui);
            
            LemonGuiController controller = loader.getController();
            controller.setMainApp(this);
            logger.log(Level.INFO, "Completed Initializing LemonGui");
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.INFO, "LemonGui Initializing Error");
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) throws Exception {
    	commandcontroller = CommandController.getInstance();
        launch(args);
    }
    
    public void updateDisplayList() throws Exception {
    	ArrayList<ArrayList<Task>> temp = commandcontroller.passListsToGUI();
    	listForTimeline = temp.get(0);
    	listForDisplay = temp.get(1);
    }
    
    // Functions for setting up the data for the Main Display Panel
    public void setListForDisplay(ArrayList<Task> list) {
    	listForDisplay = list;
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
    
    public int getMainDisplayIndex() {		//for the scrollTo function of the tables
    	int temp = mainDisplayIndex;
    	mainDisplayIndex = 0;
    	return temp;
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
    
 // Functions for setting up the data for the Timetable
    public void setListForTimeline(ArrayList<Task> list) {
    	listForTimeline = list;
    }
    
    public String getDate() {
    	String temp = commandcontroller.getDate();
    	String finalDate = temp.substring(0,2) + "/" + temp.substring(2,4) + "/" + temp.substring(4,6);
    	return finalDate;
    }
    
    public ArrayList<Task> getListForTimeline() {
    	for (int counter = 0; counter < listForTimeline.size(); counter++) {
    		if (listForTimeline.get(counter).getTaskIsNewest()) {
    			listForTimeline.get(counter).setTaskName("#" + (listForTimeline.get(counter).getTaskName()));
    			timelineIndex = counter;
    		} else {
    			listForTimeline.get(counter).setTaskName("^" + (listForTimeline.get(counter).getTaskName()));
    		}
    	}
    	return listForTimeline;
    }
    
    public int getTimelineIndex() {						//for the scrollTo function of the tables
    	int temp = timelineIndex;
    	timelineIndex = 0;
    	return temp;
    }
    
 // Functions for setting up the data for the Notification Bar
    public void getCommand(String input) {
    	commandcontroller.processCommand(input);
    }
    
    public String getNotificationBarDisplay() {
    	if (commandcontroller.checkForSuccessfulCommand()) {
    		String command = commandcontroller.passCommand();
        		return command.toUpperCase() + " Successful";
    	}
    	String errorMessage = commandcontroller.getNotificationMessage();
    	int index = errorMessage.indexOf(":");
    	return errorMessage.substring(index + 1);
    }
    
    public boolean checkSuccess() {
    	return commandcontroller.checkForSuccessfulCommand();
    }
    
}
		
