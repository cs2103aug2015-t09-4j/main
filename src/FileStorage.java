import java.io.*;

public class FileStorage {
	public FileStorage() {
		
	}
	
	public static void addEvent(String command) {
		try {
			BufferedWriter textWriter = new BufferedWriter(new FileWriter("test.txt", true));
			
			textWriter.write(command.substring(4));
			textWriter.newLine();
			
			textWriter.close();
			
			GUIConsole.successfulAdd(command.substring(4));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteEvent(String command) {
		File tempFile = new File("temp.txt");
		File inputFile = new File("test.txt");
		Integer lineToBeDelete = Integer.parseInt(command.substring(7));
		Integer lineCount = 1;
		String aLineOfContent;
		
		try {
			BufferedReader textReader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter textWriter = new BufferedWriter(new FileWriter(tempFile, true));
			
			while((aLineOfContent = textReader.readLine()) != null) {
				if(lineCount != lineToBeDelete) {
					textWriter.write(aLineOfContent);
				}
				lineCount++;
			}
			
			textReader.close();
			textWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputFile.delete();
		tempFile.renameTo(inputFile);
		
	}
	
	public static void recurEvent(String command) {
		//TBC
		GUIConsole.successfulRecur("TBC");
	}
	
	public static void navigateDay(String command) {
		//TBC
		GUIConsole.successfulNavigate("TBC");
	}

	public static void editEvent(String command) {
		File tempFile = new File("temp.txt");
		File inputFile = new File("test.txt");
		Integer lineToBeEdit = Integer.parseInt(command.substring(5, 6));
		Integer lineCount = 1;
		String aLineOfContent;
		String replacement = command.substring(7);
		
		try {
			BufferedReader textReader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter textWriter = new BufferedWriter(new FileWriter(tempFile, true));
			
			while((aLineOfContent = textReader.readLine()) != null) {
				if(lineCount != lineToBeEdit) {
					textWriter.write(aLineOfContent);
				}
				else {
					textWriter.write(replacement);
				}
				lineCount++;
			}
			
			textReader.close();
			textWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		inputFile.delete();
		tempFile.renameTo(inputFile);
		
	}
		
}
