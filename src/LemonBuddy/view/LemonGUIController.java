package LemonBuddy.view;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import LemonBuddy.CommandController;
import LemonBuddy.CommandExecutor;
import LemonBuddy.Task;
import LemonBuddy.Parser;
import LemonBuddy.Task;
import LemonBuddy.Task;
import LemonBuddy.lemonGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class LemonGUIController {

	@FXML
	private TextField inputField;

	private lemonGUI mainApp;
	
	private ObservableList<Task> MainDisplayTasks = FXCollections.observableArrayList();
	private ObservableList<Task> TimelineTasks = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Task> timeline;
	@FXML
	private TableColumn<Task, String> columnHeader;
	@FXML
	private TableColumn<Task, String> columnName;
	@FXML
	private TableColumn<Task, String> column0000;
	@FXML
	private TableColumn<Task, String> column0030;
	@FXML
	private TableColumn<Task, String> column0100;
	@FXML
	private TableColumn<Task, String> column0130;
	@FXML
	private TableColumn<Task, String> column0200;
	@FXML
	private TableColumn<Task, String> column0230;
	@FXML
	private TableColumn<Task, String> column0300;
	@FXML
	private TableColumn<Task, String> column0330;
	@FXML
	private TableColumn<Task, String> column0400;
	@FXML
	private TableColumn<Task, String> column0430;
	@FXML
	private TableColumn<Task, String> column0500;
	@FXML
	private TableColumn<Task, String> column0530;
	@FXML
	private TableColumn<Task, String> column0600;
	@FXML
	private TableColumn<Task, String> column0630;
	@FXML
	private TableColumn<Task, String> column0700;
	@FXML
	private TableColumn<Task, String> column0730;
	@FXML
	private TableColumn<Task, String> column0800;
	@FXML
	private TableColumn<Task, String> column0830;
	@FXML
	private TableColumn<Task, String> column0900;
	@FXML
	private TableColumn<Task, String> column0930;
	@FXML
	private TableColumn<Task, String> column1000;
	@FXML
	private TableColumn<Task, String> column1030;
	@FXML
	private TableColumn<Task, String> column1100;
	@FXML
	private TableColumn<Task, String> column1130;
	@FXML
	private TableColumn<Task, String> column1200;
	@FXML
	private TableColumn<Task, String> column1230;
	@FXML
	private TableColumn<Task, String> column1300;
	@FXML
	private TableColumn<Task, String> column1330;
	@FXML
	private TableColumn<Task, String> column1400;
	@FXML
	private TableColumn<Task, String> column1430;
	@FXML
	private TableColumn<Task, String> column1500;
	@FXML
	private TableColumn<Task, String> column1530;
	@FXML
	private TableColumn<Task, String> column1600;
	@FXML
	private TableColumn<Task, String> column1630;
	@FXML
	private TableColumn<Task, String> column1700;
	@FXML
	private TableColumn<Task, String> column1730;
	@FXML
	private TableColumn<Task, String> column1800;
	@FXML
	private TableColumn<Task, String> column1830;
	@FXML
	private TableColumn<Task, String> column1900;
	@FXML
	private TableColumn<Task, String> column1930;
	@FXML
	private TableColumn<Task, String> column2000;
	@FXML
	private TableColumn<Task, String> column2030;
	@FXML
	private TableColumn<Task, String> column2100;
	@FXML
	private TableColumn<Task, String> column2130;
	@FXML
	private TableColumn<Task, String> column2200;
	@FXML
	private TableColumn<Task, String> column2230;
	@FXML
	private TableColumn<Task, String> column2300;
	@FXML
	private TableColumn<Task, String> column2330;
	ArrayList<TableColumn<Task, String>> timelineColumns = new ArrayList<TableColumn<Task, String>>();
	
	@FXML
	private TableView<Task> mainDisplay;
	@FXML
	private TableColumn<Task, String> mainDisplayHeader;
	@FXML
	private TableColumn<Task, String> idColumn;
	@FXML
	private TableColumn<Task, String> nameColumn;
	@FXML
	private TableColumn<Task, String> startDateColumn;
	@FXML
	private TableColumn<Task, String> endDateColumn;
	@FXML
	private TableColumn<Task, String> startTimeColumn;
	@FXML
	private TableColumn<Task, String> endTimeColumn;
	@FXML
	private TableColumn<Task, String> priorityColumn;
	@FXML
	private TableColumn<Task, String> descriptionColumn;
	ArrayList<TableColumn<Task, String>> mainDisplayColumns = new ArrayList<TableColumn<Task, String>>();
	
	@FXML
	private TextFlow notificationBar;
	
	private static Task selectedTask = new Task();
	private static ArrayList<Task> tasks;
	private static String[] listType = {"", "overdue"};
	private static String date;
	private static String displayHeader = "Overdue Tasks";
	
	private static String[] timelineDate = {"", ""};
	private static ArrayList<Task> TasksTemp;

	private static String userCommand = "";
	
	private int mainDisplayIndex = 0;
	
	private boolean swap = false;
	
	private Parser parser;
	private CommandExecutor commandExecutor;

	private int timelineIndex;
	
	public LemonGUIController() {
		
	}
	
	@FXML
	private void initialize() throws Exception {
		if (mainApp == null) {
			mainApp = new lemonGUI();
		}
		Text temp = new Text("Welcome to LemonBuddy!!!!");
		temp.setStyle("-fx-font-size: 18pt;");
		notificationBar.getChildren().add(temp);
		notificationBar.setTextAlignment(TextAlignment.CENTER);
//		date = parser.getCurrentDate();
//		timelineDate[1] = date;
		formatMainDisplay();
		generateMainDisplay(mainApp.getListForDisplay());
		formatTimeline();
//		String newDate = timelineDate[1].substring(0,2) + "/" + timelineDate[1].substring(2,4) + "/" + timelineDate[1].substring(4,6);
		generateTimeline(mainApp.getListForTimeline(), mainApp.getDate());
	}
	
	public void setMainApp(lemonGUI mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	void onEnter(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ENTER) {
			getInput();
			modifyNotificationBar();
			if (timelineDate[1].length() == 5) {
				timelineDate[1] = "0" + timelineDate[1];
			}
			String newDate = timelineDate[1].substring(0,2) + "/" + timelineDate[1].substring(2,4) + "/" + timelineDate[1].substring(4,6);
			mainApp.updateDisplayList();
			generateTimeline(mainApp.getListForTimeline(), mainApp.getDate());
			generateMainDisplay(mainApp.getListForDisplay());
			//commandExecutor.executeRemoveNewest();
			selectedTask = new Task();
		}
	}
	
	public void generateMainDisplay(ArrayList<Task> listToDisplay) throws Exception {
		modifyDisplayHeader();
		mainDisplayHeader.setText(mainApp.getMainDisplayHeader());
		MainDisplayTasks.removeAll(MainDisplayTasks);
		MainDisplayTasks.addAll(listToDisplay);
		mainDisplay.setItems(MainDisplayTasks);
		mainDisplay.scrollTo(mainApp.getMainDisplayIndex());
		mainDisplayIndex = 0;
	}
	
	private void formatMainDisplay() {
		generateMainDisplayColumnList();
		mainDisplay.setPlaceholder(new Label("No tasks to display."));
		idColumn.setCellValueFactory(cellData -> cellData.getValue().getDisplayId());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getDisplayName());
		startDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDisplayStartDate());
		endDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDisplayEndDate());
		startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getDisplayStartTime());
		endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getDisplayEndTime());
		priorityColumn.setCellValueFactory(cellData -> cellData.getValue().getDisplayPriority());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDisplayDescription());
		
		idColumn.setCellFactory(column -> {
		    return new TableCell<Task, String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem(item, empty);
		            TableRow<Task> currentRow = getTableRow();
		            if (item == null || empty) {
		                setText(null);
		                currentRow.setStyle("");           	
		            } else if (item.startsWith("^")) {
	            		setText(item.substring(1));
		            	setTextFill(Color.BLACK);
		            	currentRow.setStyle("-fx-background-color: red;");
		            	setStyle("-fx-alignment: center;");            	
		            }else if (item.startsWith("#")) {
	            		setText(item.substring(1));
		            	setTextFill(Color.BLACK);
		            	setStyle("-fx-alignment: center;");
		            	System.out.println(currentRow.getItem());
		            	currentRow.setStyle("-fx-background-color: lightgreen;"); 
		            } else {
		            	setText(item);
	                    setTextFill(Color.BLACK);
	                    setStyle(" -fx-alignment: center");
	                    currentRow.setStyle(""); 
	                }
		        }
		    };
		});
		
		for (int counter = 2; counter < 6; counter++) {
			mainDisplayColumns.get(counter).setCellFactory(column -> {
			    return new TableCell<Task, String>() {
			        @Override
			        protected void updateItem(String item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
			                setText(null);
			                setStyle("");
			            } else if (item.equals("-1")) {
			            	setText(null);
			            	setStyle("");
			            }else {
			            	setText(item);
		                    setTextFill(Color.BLACK);
		                    setStyle(" -fx-alignment: center");
		                }
			        }
			    };
			});
			
			priorityColumn.setCellFactory(column -> {
			    return new TableCell<Task, String>() {
			        @Override
			        protected void updateItem(String item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
			                setText(null);
			                setStyle("");
			            } else if (item.equals("high")) {
			            	setStyle("-fx-background-color: red;");
			            } else if (item.equals("medium")) {
			            	setStyle("-fx-background-color: yellow;");
			            } else if (item.equals("#medium")) {
			            	setStyle("-fx-background-color: yellow;"); 
			            } else if (item.equals("#high")){
			            	setStyle("-fx-background-color: red;");
			            } else {
			            	setText(null);
		                    setStyle("");
		                }
			        }
			    };
			});
		}
	}
	
	private void generateMainDisplayColumnList() {
		mainDisplayColumns.add(idColumn);
		mainDisplayColumns.add(nameColumn);
		mainDisplayColumns.add(startDateColumn);
		mainDisplayColumns.add(endDateColumn);
		mainDisplayColumns.add(startTimeColumn);
		mainDisplayColumns.add(endTimeColumn);
		mainDisplayColumns.add(priorityColumn);
		mainDisplayColumns.add(descriptionColumn);
	}
	
	public void generateTimeline(ArrayList<Task> listToTimeline, String header) throws ClassNotFoundException, IOException, ParseException {
		TimelineTasks.removeAll(TimelineTasks);
		TimelineTasks.addAll(listToTimeline);
		columnHeader.setText("Date: " + header);
		timeline.setItems(TimelineTasks);
		timeline.scrollTo(timelineIndex);
		timelineIndex = 0;
	}
	
	private void formatTimeline() {
		timeline.setPlaceholder(new Label("No events or deadlines on this day."));
		columnName.setCellValueFactory(cellData -> cellData.getValue().getDisplayName());
		generateTimelineColumnList();
		
		for (int x = 0; x < 48; x++) {
			int y = x;
			timelineColumns.get(x).setCellValueFactory(cellData -> cellData.getValue().getTimmings(y));
		}
		
		columnName.setCellFactory(column -> {
			return new TableCell<Task, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						if (item.equals("-1")) {
							setText(null);
							setStyle("");
						} else if (item.startsWith("#")) {
							setText(item.substring(1));
							setTextFill(Color.BLACK);
							setStyle("-fx-background-color: lightgreen; -fx-border-color: lightgreen;");
						} else {
							setText(item);
							setTextFill(Color.BLACK);
							setStyle("");

						}
					}
				}
			};
		});
		
		for (int counter = 0; counter < 48; counter ++) {
			timelineColumns.get(counter).setCellFactory(column -> {
			    return new TableCell<Task, String>() {
			        @Override
			        protected void updateItem(String item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
			                setText(null);
			                setStyle("");
			            } else {
			            	if (item.equals("-1")) {
			            		setText(null);
			            		setStyle("");
			            	}
			                if (item.equals("1")) {
			                	setText(null);
			                	setStyle("-fx-background-color: yellow; -fx-border-color: black");
			                }
		                    if (item.equals("0")) {
		                    	setText(null);
		                    	setStyle("-fx-background-color: black; -fx-border-color: black");
		                    }
		                    
			            }
			        }
			    };
			});
		}
	}
	
	private void generateTimelineColumnList() {
		timelineColumns.add(column0000);
		timelineColumns.add(column0030);
		timelineColumns.add(column0100);
		timelineColumns.add(column0130);
		timelineColumns.add(column0200);
		timelineColumns.add(column0230);
		timelineColumns.add(column0300);
		timelineColumns.add(column0330);
		timelineColumns.add(column0400);
		timelineColumns.add(column0430);
		timelineColumns.add(column0500);
		timelineColumns.add(column0530);
		timelineColumns.add(column0600);
		timelineColumns.add(column0630);
		timelineColumns.add(column0700);
		timelineColumns.add(column0730);
		timelineColumns.add(column0800);
		timelineColumns.add(column0830);
		timelineColumns.add(column0900);
		timelineColumns.add(column0930);
		timelineColumns.add(column1000);
		timelineColumns.add(column1030);
		timelineColumns.add(column1100);
		timelineColumns.add(column1130);
		timelineColumns.add(column1200);
		timelineColumns.add(column1230);
		timelineColumns.add(column1300);
		timelineColumns.add(column1330);
		timelineColumns.add(column1400);
		timelineColumns.add(column1430);
		timelineColumns.add(column1500);
		timelineColumns.add(column1530);
		timelineColumns.add(column1600);
		timelineColumns.add(column1630);
		timelineColumns.add(column1700);
		timelineColumns.add(column1730);
		timelineColumns.add(column1800);
		timelineColumns.add(column1830);
		timelineColumns.add(column1900);
		timelineColumns.add(column1930);
		timelineColumns.add(column2000);
		timelineColumns.add(column2030);
		timelineColumns.add(column2100);
		timelineColumns.add(column2130);
		timelineColumns.add(column2200);
		timelineColumns.add(column2230);
		timelineColumns.add(column2300);
		timelineColumns.add(column2330);
	}

	@FXML
	private void getInput() {
		String input = inputField.getText();
		mainApp.getCommand(input);
		inputField.clear();
	}
	
	public static void setTask(Task givenTask) {
		selectedTask = givenTask;
	}
	
	public static void setList(ArrayList<Task> list) {
		tasks = list;
	}
	
	public static void setTimelineList(ArrayList<Task> list) {
		TasksTemp = list;
	}

	public static void setListType(String type) {
		listType[1] = type;
	}
	
	public static void setTimeLineDate(String requestedDate) {
		timelineDate[1] = requestedDate;
	}

	public static void setMainDisplay(String type) {
		listType[1] = type;
	}	
	
	private static void modifyDisplayHeader() {
		String type = listType[1];
		if (type.equals("all")) {
			displayHeader = "All Tasks";
		}
		if (type.equals("floating")) {
			displayHeader = "Floating Tasks";
		}
		if (type.equals("deadline")) {
			displayHeader = "Deadlines";
		}
		if (type.equals("event")) {
			displayHeader = "Events";
		}
		if (type.equals("done")) {
			displayHeader = "Completed Tasks";
		}
		if (type.equals("overdue")) {
			displayHeader = "Overdue Tasks";
		}
		if (type.equals("search")) {
			displayHeader = "Search Results";
		}
	}

	public static void setCommand(String command) {
		userCommand = command;
	}
	
	public void modifyNotificationBar() {
		String toDisplay = mainApp.getNotificationBarDisplay();
		System.out.println(toDisplay);
		Text temp = new Text(toDisplay);
		temp.setStyle("-fx-font-size: 18pt;");
		notificationBar.getChildren().clear();
		notificationBar.getChildren().add(temp);
		notificationBar.setTextAlignment(TextAlignment.CENTER);
		notificationBar.setStyle("-fx-background-color: red;");
		
//		swap = false;
//		String toPrint = "";
//		int style = 0;
//		if (userCommand.equals("add")) {
//			toPrint  = (selectedTask.getTaskName() + " added!");
//			style = 1;
//			if (selectedTask.getTaskType().equals("event")) {
//				swap = true;
//				timelineDate[1] = "" + selectedTask.getTaskStartDate();
//				displayHeader = "Displaying tasks on: " + selectedTask.getTaskStartDate();
//				listType[1] = "date";
//			} else if (selectedTask.getTaskType().equals("deadline")) {
//				swap = true;
//				timelineDate[1] = "" + selectedTask.getTaskEndDate();
//				displayHeader = "Displaying tasks on: " + selectedTask.getTaskEndDate();
//				listType[1] = "date";
//			} else {
//				swap = false;
//				listType[1] = "floating";
//			}
//		}
//		if (userCommand.equals("delete")) {
//			toPrint = (selectedTask.getTaskName() + " deleted!");
//			style = 1;
//			if (selectedTask.getTaskType().equals("event")) {
//				swap = true;
//				timelineDate[1] = "" + selectedTask.getTaskStartDate();
//				displayHeader = "Displaying tasks on: " + selectedTask.getTaskStartDate();
//				listType[1] = "date";
//			} else if (selectedTask.getTaskType().equals("deadline")) {
//				swap = true;
//				timelineDate[1] = "" + selectedTask.getTaskEndDate();
//				displayHeader = "Displaying tasks on: " + selectedTask.getTaskEndDate();
//				listType[1] = "date";
//			} else {
//				swap = false;
//				listType[1] = "floating";
//			}
//		}
//		if (userCommand.equals("edit")) {
//			toPrint = (selectedTask.getTaskName() + " edited!");
//			style = 1;
//			if (selectedTask.getTaskType().equals("event")) {
//				swap = true;
//				timelineDate[1] = "" + selectedTask.getTaskStartDate();
//				displayHeader = "Displaying tasks on: " + selectedTask.getTaskStartDate();
//				listType[1] = "date";
//			} else if (selectedTask.getTaskType().equals("deadline")) {
//				swap = true;
//				timelineDate[1] = "" + selectedTask.getTaskEndDate();
//				displayHeader = "Displaying tasks on: " + selectedTask.getTaskEndDate();
//				listType[1] = "date";
//			} else {
//				swap = false;
//				listType[1] = "floating";
//			}
//		}
//		if (userCommand.equals("list")) {
//			toPrint = ("Displaying tasks");
//			style = 1;
//		}
//		if (userCommand.equals("view")) {
//			style = 1;
//			swap = true;
//			listType[1] = "date";
//			String newDate = timelineDate[1].substring(0,2) + "/" + timelineDate[1].substring(2,4) + "/" + timelineDate[1].substring(4,6);
//			displayHeader = "Displaying tasks on: " + newDate;
//			toPrint = ("Displaying tasks on " + newDate);
//		}
//		if (userCommand.equals("undo")) {
//			toPrint = ("Undo successful");
//			style = 1;
//		}
//		if (userCommand.equals("redo")) {
//			toPrint = ("Redo successful");
//			style = 1;
//		}
//		if (userCommand.equals("redo maxed")) {
//			toPrint = ("Redo unsuccessful, no more actions to redo.");
//			style = 2;
//		}
//		if (userCommand.equals("done")) {
//			toPrint = ("Task set as done.");
//			style = 1;
//		}
//		if (userCommand.equals("search")) {
//			toPrint = ("Search successful");
//			style = 1;
//		}
//		
//		Text temp = new Text(toPrint);
//		temp.setStyle("-fx-font-size: 18pt;");
//		notificationBar.getChildren().clear();
//		notificationBar.getChildren().add(temp);
//		notificationBar.setTextAlignment(TextAlignment.CENTER);
//		
//		if (style == 1) {
//			notificationBar.setStyle("-fx-background-color: lightgreen;");
//		}
//		
//		if (style == 2) {
//			notificationBar.setStyle("-fx-background-color: red;");
//		}
//		
//		style = 0;
	}

}
