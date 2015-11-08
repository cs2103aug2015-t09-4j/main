package LemonBuddy.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import LemonBuddy.CommandExecutor;

public class TestExecutor {

	@Test
	public void testStringForParsingForEdit() {
		
		CommandExecutor tester = new CommandExecutor();
		
		String input = "edit 1 buy bed by 291215";
		String[] commandParts = input.split(" ");
		String[] expectedParts = {"add", "buy", "bed", "by", "291215"};
		
		assertArrayEquals("simple get before any add", expectedParts, tester.getStringForParsing(commandParts)); 
	}
}
