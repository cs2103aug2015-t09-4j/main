import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUITextInput extends JPanel {
	public GUITextInput() {
		Dimension size = getPreferredSize();
		size.height = 25;
		setPreferredSize(size);
		
		//setBorder(BorderFactory.createTitledBorder("Input"));
		
		JLabel inputLabel = new JLabel("Command : ");
		JTextField inputTextBox = new JTextField(28);
		
		//https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
		setLayout(new GridBagLayout());
		
		GridBagConstraints textPanelConstraint = new GridBagConstraints();
		
		//First Column
		
		textPanelConstraint.anchor = GridBagConstraints.LINE_START;
		
		textPanelConstraint.gridx = 0;
		textPanelConstraint.gridy = 0;
		add(inputLabel, textPanelConstraint);
		
		textPanelConstraint.anchor = GridBagConstraints.LINE_END;
		
		textPanelConstraint.gridx = 1;
		textPanelConstraint.gridy = 0;
		add(inputTextBox, textPanelConstraint);
	
		//Action perform
		inputTextBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String command = inputTextBox.getText();
				CommandController.processCommand(command);
				inputTextBox.setText("");
			}
		});
	}
}
