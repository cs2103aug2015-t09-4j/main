import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;

public class LemonBuddyMain {
	
	public static void main(String[] args) {
		
		//quick tutorial http://www.javamex.com/tutorials/threads/invokelater.shtml
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIMain frame = new GUIMain("LemonBuddy");
			}
		});
	}
}