package saves;

import java.io.IOException;

import drawings.DrawCoins;
import main.GameLoop;
import main.Panel;

public class Coins {

	public static int coins = 0;
	public static int collectedCoins = 0;
	private int count;
	private String fileName = "gamefiles.txt";
	private String output;
	public static float coinFactor = 0.5f;
	public static int coinAmount;
	
	//Changes: calculateCoins, writeCoins, coinScore, additionalCoins, result

	
	MyFile file;
	
	public Coins(MyFile file) {
		this.file = file;
		coinAmount = Integer.parseInt(readFile());
//		createFile(file, fileName);
	}
	
	public int calculateGameCoins() {
		
		if(count < Panel.score) {
			if(Panel.score % 10 != 0) {
				coins += Panel.score*100;
			}
			
			if(Panel.score % 10 == 0 && Panel.score % 100 != 0) {
				coins += 500*Panel.score;
			}
			
			if(Panel.score % 100 == 0 && Panel.score % 1000 != 0) {
				coins += 1000*Panel.score;
			}
			
			if(Panel.score % 1000 == 0 && Panel.score % 10000 != 0) {
				coins += 2000*Panel.score;
			}
		
		}
		
		try {
			if(GameLoop.panel.checkHighScore(file) == true) {
				DrawCoins.checkHighScore = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		count = Panel.score;		
		collectedCoins = (int) (coins*coinFactor); 

		return (int) (coins*coinFactor);
	}
	
	
	public int writeCoins() {
			if(DrawCoins.checkHighScore == true) {
//				writeCoinsOutput = Integer.toString(Integer.parseInt(readFile()) + collectedCoins + (int) (10000*coinFactor));
				coinAmount += collectedCoins + (int) (10000*coinFactor);
				DrawCoins.checkHighScore = false;
			}else {
//				writeCoinsOutput = Integer.toString(Integer.parseInt(readFile()) + collectedCoins);
				coinAmount += collectedCoins;
			}
			return coinAmount;
	}
	
	
//	private void createFile(MyFile file, String fileName) {
//		try {
//			file.createFile(fileName);
//			try {
//				int i = Integer.parseInt(file.readFile(fileName, 0));
//			} catch(Exception x) {
//				file.writeFile(fileName, "0");
//			}
//			}
//			catch(Exception e) {
//				
//			}
//	}
	
	public String readFile() {
		try {
			output = file.readFile(fileName, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
}
