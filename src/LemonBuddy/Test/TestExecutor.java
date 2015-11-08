package LemonBuddy.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import LemonBuddy.CommandExecutor;

public class TestExecutor {

	@Test
	public void testStringForParsingForEdit() {
		
		
		
		String input = "edit 1 buy bed by 291215";
		String[] commandParts = input.split(" ");
		String[] expectedParts = {"add", "buy", "bed", "by", "291215"};
		
		testOneCommand("simple get before any add", expectedParts, commandParts);
		
	}

	//@SuppressWarnings("deprecation")
	private void testOneCommand(String description, String[] expectedParts, String[] commandParts) {
		CommandExecutor tester = new CommandExecutor();
		assertArrayEquals(description, expectedParts, tester.getStringForParsing(commandParts)); 
	}
}
