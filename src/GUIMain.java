import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

public class GUIMain extends JFrame {

	public GUIMain(String title) {
		super(title);
		this.setSize(400, 430);
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		// Set layout manager
		setLayout(new BorderLayout());
		
		GUIConsole consoleBox = new GUIConsole();		
		GUITextInput inputBox = new GUITextInput();	
		
		
		// Add component to content pane
		Container contentPane = getContentPane();
		
		contentPane.add(consoleBox, BorderLayout.NORTH);
		contentPane.add(inputBox, BorderLayout.SOUTH);
		
	}
}
