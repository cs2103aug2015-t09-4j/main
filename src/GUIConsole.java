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
	
	//public static void successfulEdit(String content) {
	//	displayScreen.append("\"" + content + "\"" + " is deleted from File." + "\n\n");
	//}
	
	public static void successfulRecur(String content) {
		displayScreen.append(content + "\n\n");
	}
	public static void successfulNavigate(String content) {
		displayScreen.append("\"" + content + "\"" + " is added to File." + "\n\n");
	}
}
