package saves;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFile {
	String output;
//    String[] array = new String[5];  
    
    
	
	public void createFile(String fileName) {
		File file = new File(fileName);
	}
	
	public void writeFile(String fileName, String highscore, String coins, String difficulty, String selectedbird, String music, String sound, String HUD) throws IOException{
		PrintWriter writer = new PrintWriter(fileName);
		writer.println(highscore);
		writer.println(coins);
		writer.println(difficulty);
		writer.println(selectedbird);
		Shop.writeArray(writer);
		writer.println(music);
		writer.println(sound);
		writer.println(HUD);
		writer.close();
	}
	
	public String readFile(String fileName, int  lineNumber) throws IOException {

		BufferedReader r = new BufferedReader(new FileReader(fileName));
		for(int i = 0; i < lineNumber + 1; i++)
		{
			output = r.readLine();
			
		}
		

		return output;
		}
	
//	private void init(PrintWriter writer) {
//		for (int i = 0; i < 5; i++)  
//	    {  
//	        array[i] = "0"; 
//	        writer.println(array[i]);
//	    } 
//	}
	
}
