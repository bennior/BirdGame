package saves;

import java.io.IOException;
import java.io.PrintWriter;

import main.Panel;

public class Shop {
	
	public static String selectedBird;
	public static String array[] = new String[6];
	
	public static void initSelected() throws IOException {
		selectedBird = Panel.file.readFile("gamefiles.txt", 3);
	}
	
	public static void initArray() throws IOException {
		
		for(int lineNumber = 4; lineNumber < 10; lineNumber++) {
			
			array[lineNumber - 4] = Panel.file.readFile("gamefiles.txt", lineNumber);
		}
	}
	
	public static void writeArray(PrintWriter writer) {
		
		for(int lineNumber = 4; lineNumber < 10; lineNumber++) {
			
			
			writer.println(array[lineNumber - 4]);

		}
	}
	
	public static String outputArray(int number) {
		
		return array[number - 4];
	}
}
