import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUIConsole extends JPanel {
	
	static JTextArea displayScreen = new JTextArea(18, 33);
	
	public GUIConsole() {
		Dimension size = getPreferredSize();
		size.height=350;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Console"));
		
		setLayout(new GridBagLayout());
		
		displayScreen.setEditable(false);
		
		GridBagConstraints screenPenalConstraint = new GridBagConstraints();
		
		//First Column
		screenPenalConstraint.anchor = GridBagConstraints.CENTER;
		screenPenalConstraint.gridx = 0;
		screenPenalConstraint.gridy = 0;
		add(displayScreen, screenPenalConstraint);
	}

	public static void successfulAdd(String content) {
		displayScreen.append("\"" + content + "\"" + " is added to File." + "\n\n");
	}
	
	public static void successfulDelete(String content) {
		displayScreen.append("\"" + content + "\"" + " is deleted from File." + "\n\n");
	}
	
	public static void successfulEditName(String content, String content1) {
		displayScreen.append("Task name \"" + content + "\" changed to \"" + content1 + "\"\n\n");
	}
	
	public static void successfulEditDate(String content, Integer content1) {
		displayScreen.append("Date of \"" + content + "\" changed to \"" + content1 + "\"\n\n");
	}
	
	public static void successfulEditTime(Integer content, Integer content1, Integer content2) {
		displayScreen.append("Time of \"" + content1 + "\" changed to \"" + content2 + "-" + content1 + "\"\n\n");
	}
	
	public static void successfulRecur(String content) {
		displayScreen.append(content + "\n\n");
	}
	public static void successfulNavigate(String content) {
		displayScreen.append("\"" + content + "\"" + " is added to File." + "\n\n");
	}
}
