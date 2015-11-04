package LemonBuddy.view;

import java.io.IOException;
import java.util.ArrayList;

import LemonBuddy.CommandController;
import LemonBuddy.CommandExecutor;
import LemonBuddy.MainDisplayTask;
import LemonBuddy.Parser;
import LemonBuddy.Task;
import LemonBuddy.TimelineTask;
import LemonBuddy.lemonGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
	
	private ObservableList<TimelineTask> timelineTasks = FXCollections.observableArrayList();
	private ObservableList<MainDisplayTask> mainDisplayTasks = FXCollections.observableArrayList();
	
	@FXML
	private TableView<TimelineTask> timeline;
	@FXML
	private TableColumn<TimelineTask, String> columnHeader;
	@FXML
	private TableColumn<TimelineTask, String> columnName;
	@FXML
	private TableColumn<TimelineTask, String> column0000;
	@FXML
	private TableColumn<TimelineTask, String> column0030;
	@FXML
	private TableColumn<TimelineTask, String> column0100;
	@FXML
	private TableColumn<TimelineTask, String> column0130;
	@FXML
	private TableColumn<TimelineTask, String> column0200;
	@FXML
	private TableColumn<TimelineTask, String> column0230;
	@FXML
	private TableColumn<TimelineTask, String> column0300;
	@FXML
	private TableColumn<TimelineTask, String> column0330;
	@FXML
	private TableColumn<TimelineTask, String> column0400;
	@FXML
	private TableColumn<TimelineTask, String> column0430;
	@FXML
	private TableColumn<TimelineTask, String> column0500;
	@FXML
	private TableColumn<TimelineTask, String> column0530;
	@FXML
	private TableColumn<TimelineTask, String> column0600;
	@FXML
	private TableColumn<TimelineTask, String> column0630;
	@FXML
	private TableColumn<TimelineTask, String> column0700;
	@FXML
	private TableColumn<TimelineTask, String> column0730;
	@FXML
	private TableColumn<TimelineTask, String> column0800;
	@FXML
	private TableColumn<TimelineTask, String> column0830;
	@FXML
	private TableColumn<TimelineTask, String> column0900;
	@FXML
	private TableColumn<TimelineTask, String> column0930;
	@FXML
	private TableColumn<TimelineTask, String> column1000;
	@FXML
	private TableColumn<TimelineTask, String> column1030;
	@FXML
	private TableColumn<TimelineTask, String> column1100;
	@FXML
	private TableColumn<TimelineTask, String> column1130;
	@FXML
	private TableColumn<TimelineTask, String> column1200;
	@FXML
	private TableColumn<TimelineTask, String> column1230;
	@FXML
	private TableColumn<TimelineTask, String> column1300;
	@FXML
	private TableColumn<TimelineTask, String> column1330;
	@FXML
	private TableColumn<TimelineTask, String> column1400;
	@FXML
	private TableColumn<TimelineTask, String> column1430;
	@FXML
	private TableColumn<TimelineTask, String> column1500;
	@FXML
	private TableColumn<TimelineTask, String> column1530;
	@FXML
	private TableColumn<TimelineTask, String> column1600;
	@FXML
	private TableColumn<TimelineTask, String> column1630;
	@FXML
	private TableColumn<TimelineTask, String> column1700;
	@FXML
	private TableColumn<TimelineTask, String> column1730;
	@FXML
	private TableColumn<TimelineTask, String> column1800;
	@FXML
	private TableColumn<TimelineTask, String> column1830;
	@FXML
	private TableColumn<TimelineTask, String> column1900;
	@FXML
	private TableColumn<TimelineTask, String> column1930;
	@FXML
	private TableColumn<TimelineTask, String> column2000;
	@FXML
	private TableColumn<TimelineTask, String> column2030;
	@FXML
	private TableColumn<TimelineTask, String> column2100;
	@FXML
	private TableColumn<TimelineTask, String> column2130;
	@FXML
	private TableColumn<TimelineTask, String> column2200;
	@FXML
	private TableColumn<TimelineTask, String> column2230;
	@FXML
	private TableColumn<TimelineTask, String> column2300;
	@FXML
	private TableColumn<TimelineTask, String> column2330;
	ArrayList<TableColumn<TimelineTask, String>> timelineColumns = new ArrayList<TableColumn<TimelineTask, String>>();
	
	
	@FXML
	private TableView<MainDisplayTask> mainDisplay;
	@FXML
	private TableColumn<MainDisplayTask, String> mainDisplayHeader;
	@FXML
	private TableColumn<MainDisplayTask, String> idColumn;
	@FXML
	private TableColumn<MainDisplayTask, String> nameColumn;
	@FXML
	private TableColumn<MainDisplayTask, String> startDateColumn;
	@FXML
	private TableColumn<MainDisplayTask, String> endDateColumn;
	@FXML
	private TableColumn<MainDisplayTask, String> startTimeColumn;
	@FXML
	private TableColumn<MainDisplayTask, String> endTimeColumn;
	@FXML
	private TableColumn<MainDisplayTask, String> priorityColumn;
	@FXML
	private TableColumn<MainDisplayTask, String> descriptionColumn;
	ArrayList<TableColumn<MainDisplayTask, String>> mainDisplayColumns = new ArrayList<TableColumn<MainDisplayTask, String>>();
	
	@FXML
	private TextArea notificationBar;
	
	private static Task selectedTask = new Task();
	private static ArrayList<Task> tasks;
	private static String[] listType = {"", "overdue"};
	private static String date;
	private static String displayHeader = "Overdue Tasks";
	
	private static String[] timelineDate = {"", ""};
	private static ArrayList<Task> timelineTasksTemp;

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
		if (parser == null) {
			parser = new Parser();
		}
		if (commandExecutor == null) {
			commandExecutor = new CommandExecutor();
		}
		date = parser.getCurrentDate();
		timelineDate[1] = date;
		formatMainDisplay();
		generateMainDisplay();
		formatTimeline();
		generateTimeline(timelineDate[1]);
	}
	
	public void setMainApp(lemonGUI mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	void onEnter(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ENTER) {
			getInput();
			modifyNotificationBar();
			commandExecutor.executeUpdate();
			generateTimeline(timelineDate[1]);
			generateMainDisplay();
			commandExecutor.executeRemoveNewest();
			selectedTask = new Task();
		}
	}
	
	public void generateMainDisplay() throws Exception {
		if (swap) {
			tasks = timelineTasksTemp;
		} else {
			commandExecutor.executeList(listType);
		}
		modifyDisplayHeader();
		mainDisplayHeader.setText(displayHeader);
		mainDisplayTasks.removeAll(mainDisplayTasks);
		for (int counter = 0; counter < tasks.size(); counter++) {
			mainDisplayTasks.add(convertForMainDisplay(tasks.get(counter), counter));
		}
		mainDisplay.setItems(mainDisplayTasks);
		mainDisplay.scrollTo(mainDisplayIndex);
		mainDisplayIndex = 0;
	}
	
	private void formatMainDisplay() {
		generateMainDisplayColumnList();
		mainDisplay.setPlaceholder(new Label("No tasks to display."));
		idColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskId());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskName());
		startDateColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskStartDate());
		endDateColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskEndDate());
		startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskStartTime());
		endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskEndTime());
		priorityColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskPriority());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskDescription());
		
		idColumn.setCellFactory(column -> {
		    return new TableCell<MainDisplayTask, String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem(item, empty);

		            if (item == null || empty) {
		                setText(null);
		                setStyle("");
		            } else if (item.startsWith("#")) {
		            		setText(item.substring(1));
			            	setTextFill(Color.BLACK);
			            	setStyle("-fx-background-color: lightgreen; -fx-border-color: lightgreen;"
			            			+ "-fx-alignment: center;");            	
		            } else {
		            	setText(item);
	                    setTextFill(Color.BLACK);
	                    setStyle(" -fx-alignment: center");
	                }
		        }
		    };
		});
		
		nameColumn.setCellFactory(column -> {
		    return new TableCell<MainDisplayTask, String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem(item, empty);

		            if (item == null || empty) {
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
		    };
		});
		
		for (int counter = 2; counter < 6; counter++) {
			mainDisplayColumns.get(counter).setCellFactory(column -> {
			    return new TableCell<MainDisplayTask, String>() {
			        @Override
			        protected void updateItem(String item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
			                setText(null);
			                setStyle("");
			            } else if (item.equals("-1")) {
			            	setText(null);
			            	setStyle("");
			            } else if (item.startsWith("#")) {
			            	if (item.endsWith("-1")) {
			            		setText("");
				            	setStyle("-fx-background-color: lightgreen; -fx-border-color: lightgreen;"
				            			+ "-fx-alignment: center;");
			            	} else {
			            		setText(item.substring(1));
				            	setTextFill(Color.BLACK);
				            	setStyle("-fx-background-color: lightgreen; -fx-border-color: lightgreen;"
				            			+ "-fx-alignment: center;");
			            	}
			            	
			            } else {
			            	setText(item);
		                    setTextFill(Color.BLACK);
		                    setStyle(" -fx-alignment: center");
		                }
			        }
			    };
			});
			
			priorityColumn.setCellFactory(column -> {
			    return new TableCell<MainDisplayTask, String>() {
			        @Override
			        protected void updateItem(String item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
			                setText(null);
			                setStyle("");
			            } else if (item.equals("high")) {
			            	setStyle("-fx-background-color: red; -fx-border-color: red;");
			            } else if (item.equals("medium")) {
			            	setStyle("-fx-background-color: yellow; -fx-border-color: yellow;");
			            } else if (item.equals("#medium")) {
			            	setStyle("-fx-background-color: yellow; -fx-border-color: yellow;"); 
			            } else if (item.equals("#high")){
			            	setStyle("-fx-background-color: red; -fx-border-color: red;");
			            } else if (item.equals("#")) {
			            	setStyle("-fx-background-color: lightgreen; -fx-border-color: lightgreen;"
			            			+ "-fx-alignment: center;");
			            } else {
			            	setText(null);
		                    setStyle("");
		                }
			        }
			    };
			});
			
			descriptionColumn.setCellFactory(column -> {
			    return new TableCell<MainDisplayTask, String>() {
			        @Override
			        protected void updateItem(String item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
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
	
	public void generateTimeline(String header) throws ClassNotFoundException, IOException {
		timelineTasks.removeAll(timelineTasks);
		columnHeader.setText("Date: " + header);
		commandExecutor.executeNavigate(timelineDate);
		for (int counter = 0; counter < timelineTasksTemp.size(); counter++) {
			if (!timelineTasksTemp.get(counter).getTaskType().equals("floating")){
				timelineTasks.add(convertForTimeline(timelineTasksTemp.get(counter)));
				if (timelineTasksTemp.get(counter).getTaskIsNewest()) {
					timelineIndex = counter;
				}
			}
		}
		timeline.setItems(timelineTasks);
		timeline.scrollTo(timelineIndex);
		timelineIndex = 0;
	}
	
	private void formatTimeline() {
		timeline.setPlaceholder(new Label("No events or deadlines on this day."));
		columnName.setCellValueFactory(cellData -> cellData.getValue().getTaskName());
		generateTimelineColumnList();
		
		for (int x = 0; x < 48; x++) {
			int y = x;
			timelineColumns.get(x).setCellValueFactory(cellData -> cellData.getValue().getTimmings(y));
		}
		
		columnName.setCellFactory(column -> {
			return new TableCell<TimelineTask, String>() {
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
			    return new TableCell<TimelineTask, String>() {
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
		CommandController.processCommand(input);
		inputField.clear();
	}
	
	public static void setTask(Task givenTask) {
		selectedTask = givenTask;
	}
	
	public static void setList(ArrayList<Task> list) {
		tasks = list;
	}
	
	public static void setTimelineList(ArrayList<Task> list) {
		timelineTasksTemp = list;
	}

	public static void setListType(String type) {
		listType[1] = type;
	}
	
	public MainDisplayTask convertForMainDisplay(Task newTask, int num) {
		MainDisplayTask temp = new MainDisplayTask();
		int id = num + 1;
		if (newTask.getTaskIsNewest()) {
			System.out.println(newTask.getTaskIsNewest());
			temp.setTaskId("#" + id);
			temp.setTaskName("#" + newTask.getTaskName());
			temp.setTaskStartDate("#" + newTask.getTaskStartDate());
			temp.setTaskEndDate("#" + newTask.getTaskEndDate());
			temp.setTaskStartTime("#" + newTask.getTaskStartTime());
			temp.setTaskEndTime("#" + newTask.getTaskEndTime());
			temp.setTaskPriority("#" + newTask.getTaskPriority());
			temp.setTaskDescription("#" + newTask.getTaskDescription());
			mainDisplayIndex = num;
			return temp;
		}
		
		temp.setTaskId("" + id);
		temp.setTaskName(newTask.getTaskName());
		temp.setTaskStartDate("" + newTask.getTaskStartDate());
		temp.setTaskEndDate("" + newTask.getTaskEndDate());
		temp.setTaskStartTime("" + newTask.getTaskStartTime());
		temp.setTaskEndTime("" + newTask.getTaskEndTime());
		temp.setTaskPriority(newTask.getTaskPriority());
		temp.setTaskDescription(newTask.getTaskDescription());
		System.out.println(newTask.getTaskIsNewest());

		return temp;
	}
	
	public TimelineTask convertForTimeline(Task newTask) {
		TimelineTask temp = new TimelineTask();
		if (newTask.getTaskIsNewest()) {
			temp.setTaskName("#" + newTask.getTaskName());
		} else {
			temp.setTaskName(newTask.getTaskName());
		}
		temp.setTaskStartDate(newTask.getTaskStartDate());
		temp.setTaskEndDate(newTask.getTaskEndDate());
		temp.setTaskType(newTask.getTaskType());
		int rstartTime = -1;
		int rendTime = -1;
		if (newTask.getTaskType().equals("deadline")) {
			rendTime = roundDown(newTask.getTaskEndTime());
		}
		if (newTask.getTaskType().equals("event")) {
			rstartTime = roundDown(newTask.getTaskStartTime());
			rendTime = roundUp(newTask.getTaskEndTime());
		}
		if (newTask.getTaskStartDate() != Integer.parseInt(timelineDate[1])
				&& newTask.getTaskEndDate() != Integer.parseInt(timelineDate[1])) {
			for (int counter2 = 0; counter2 < 48; counter2++) {
				temp.setTime(counter2);
			}
			return temp;
		}
		
		int time = 0;
		int add = 0;
		if (newTask.getTaskStartDate() != Integer.parseInt(timelineDate[1]) && newTask.getTaskType().equals("event")) {
			add = 1;
		} else {
			add = 0;
		}
		
		for (int counter = 0; counter < 48; counter ++) {
			if (time == rstartTime && newTask.getTaskStartDate() == Integer.parseInt(timelineDate[1])) {
				add = 1;
			}
			if (time == rendTime && newTask.getTaskEndDate() == Integer.parseInt(timelineDate[1])) {
				add = 0;
			}
			if (add == 1) {
				temp.setTime(counter);
			}
			if (add == 0) {
				temp.missTime(counter);
			}
			if (time == rendTime && newTask.getTaskEndDate() == Integer.parseInt(timelineDate[1]) &&
					newTask.getTaskType().equals("deadline")) {
				temp.setDeadline(counter);
			}
			if(time % 100 == 0) {
				time = time + 30;
			} else {
				time = time - 30 + 100;
			}
		}
		return temp;
	}
	
	private int roundDown(int time) {
		int ans = time;
		int temp = time;
		temp = temp % 100;
		if (temp == 0) {
			return ans;
		}
		if (temp > 30) {
			ans = ans +30 - temp;
		}
		if (temp < 30) {
			ans = ans - temp;
		}
		return ans;
	}
	
	private int roundUp(int time) {
		int ans = time;
		int temp = time;
		temp = temp % 100;
		if (temp == 0) {
			return ans;
		}
		if (temp > 30) {
			ans = ans + 100 - temp;
		}
		if (temp < 30) {
			ans = ans - temp + 30;
		}
		return ans;
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
		swap = false;
		if (userCommand.equals("add")) {
			notificationBar.setText(selectedTask.getTaskName() + " added!");
			notificationBar.setStyle("-fx-background-color: green;");
			if (selectedTask.getTaskType().equals("event")) {
				swap = true;
				timelineDate[1] = "" + selectedTask.getTaskStartDate();
				displayHeader = "Displaying tasks on: " + selectedTask.getTaskStartDate();
				listType[1] = "date";
			} else if (selectedTask.getTaskType().equals("deadline")) {
				swap = true;
				timelineDate[1] = "" + selectedTask.getTaskEndDate();
				displayHeader = "Displaying tasks on: " + selectedTask.getTaskEndDate();
				listType[1] = "date";
			} else {
				swap = false;
				listType[1] = "floating";
			}
		}
		if (userCommand.equals("delete")) {
			notificationBar.setText(selectedTask.getTaskName() + " deleted!");
			notificationBar.setStyle("-fx-background-color: green;");
			if (selectedTask.getTaskType().equals("event")) {
				swap = true;
				timelineDate[1] = "" + selectedTask.getTaskStartDate();
				displayHeader = "Displaying tasks on: " + selectedTask.getTaskStartDate();
				listType[1] = "date";
			} else if (selectedTask.getTaskType().equals("deadline")) {
				swap = true;
				timelineDate[1] = "" + selectedTask.getTaskEndDate();
				displayHeader = "Displaying tasks on: " + selectedTask.getTaskEndDate();
				listType[1] = "date";
			} else {
				swap = false;
				listType[1] = "floating";
			}
		}
		if (userCommand.equals("edit")) {
			notificationBar.setText(selectedTask.getTaskName() + " edited!");
			notificationBar.setStyle("-fx-background-color: green;");
			if (selectedTask.getTaskType().equals("event")) {
				swap = true;
				timelineDate[1] = "" + selectedTask.getTaskStartDate();
				displayHeader = "Displaying tasks on: " + selectedTask.getTaskStartDate();
				listType[1] = "date";
			} else if (selectedTask.getTaskType().equals("deadline")) {
				swap = true;
				timelineDate[1] = "" + selectedTask.getTaskEndDate();
				displayHeader = "Displaying tasks on: " + selectedTask.getTaskEndDate();
				listType[1] = "date";
			} else {
				swap = false;
				listType[1] = "floating";
			}
		}
		if (userCommand.equals("list")) {
			notificationBar.setText("Displaying tasks");
			notificationBar.setStyle("-fx-background-color: green;");
		}
		if (userCommand.equals("view")) {
			notificationBar.setText("Displaying tasks on " + timelineDate[1]);
			notificationBar.setStyle("-fx-background-color: green;");
			swap = true;
			listType[1] = "date";
			displayHeader = "Displaying tasks on: " + timelineDate[1];
		}
		if (userCommand.equals("undo")) {
			notificationBar.setText("Undo successful");
			notificationBar.setStyle("-fx-background-color: green;");
		}
		if (userCommand.equals("redo")) {
			notificationBar.setText("Redo successful");
			notificationBar.setStyle("-fx-background-color: green;");
		}
		if (userCommand.equals("redo max")) {
			notificationBar.setText("Redo unsuccessful, no more actions to redo.");
			notificationBar.setStyle("-fx-background-color: red;");
		}
		if (userCommand.equals("done")) {
			notificationBar.setText("Task set as done.");
			notificationBar.setStyle("-fx-background-color: green;");
		}
		if (userCommand.equals("search")) {
			notificationBar.setText("Search successful");
			notificationBar.setStyle("-fx-background-color: green;");
		}
	}

}
