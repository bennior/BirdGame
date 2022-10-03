package rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

import buttons.MyButton;
import drawings.DrawCoins;
import main.GameLoop;
import main.Panel;
import saves.Coins;
import saves.Difficulty;
import saves.MyFile;
import updating.GameStates;

public class Generation {

	private Bird bird = Panel.bird;
	MyFile myFile;
	private double xPos = 800.0;
	double position;
	int secondCount = 0;
	private Color color;
	private int height1;
	private int height2;
	private Dimension dimension = randomNumber();
	private static int gap;
	private static int maxUnit;
	private static int randomUnit;
	
	public Generation(MyFile myFile) {
		this.myFile = myFile;
	}
	
	
	public void move(Graphics g) {
		
		if(GameStates.currentGameState == GameStates.GAME) {
		
		if(secondCount < 1) {
		position = xPos;
		color = randomColor();
		dimension = randomNumber();
		height1 = (int) dimension.getHeight();
		height2 = (int) dimension.getWidth();
		}

		g.setColor(color);
		g.fillRect((int) position, 0, 80, height1*10);  //times one unit
		
		g.setColor(color);
		g.fillRect((int) position, height1*10 + gap, 80, height2*10);  //gap, 180 = easy, 160 = medium/hard, 170 = medium                    ////Change here

		
		position -= 1.0;	
		
		checkCollision(g, bird.getPosition(), position, height1);

		secondCount++;
		
		if(position < -79.0) {
			secondCount = 0;
		}
		}
	}
	
	public Color randomColor() {
		
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		
		return new Color(r, g, b);
	}
	
	public Dimension randomNumber() {
		int height1;
		int height2;                  //one unit = 10  
		height1 = (int) (Math.random() * randomUnit) + 1;   //400 - gap, one unit beneath + always - 1 -> generates between 0 and number   //in total minus one unit minus 1    ////Change here
		height2 = maxUnit - height1;   //max unit             ////Change here

		
		return new Dimension(height1, height2);
	}
	
	public void generateRandom() {
		secondCount = 0;
	}
	
	public void checkCollision(Graphics g, int height, double xPos, int height1) {
		
		if(height + 10 >= height1*10 && height + 40 <= height1*10 + gap) { //50x50 -> 12 and 50 //32x32 -> 8 and 32 //40x40 -> 10 and 40           ////Change here
		}else {
			if(295.0 <= xPos && xPos <= 415.0) {
			MyButton.kb.setTrue(5);
//			SoundLoop.sounds.death();
			GameLoop.sounds.death();
			}
		}
		if(xPos + 80.0 == 374.0) {
			Panel.score++;
			DrawCoins.alpha = 255;
			DrawCoins.alphaImage = 1f;
		}
	}
	
	public static void difficultyGeneration() {
		
		try {
			Difficulty.currentDifficulty = Difficulty.valueOf(Panel.file.readFile("gamefiles.txt", 2));
		} catch (IOException e) {
			e.printStackTrace();
		}

		switch(Difficulty.currentDifficulty) {
		case EASY: 
			gap = 180;
			randomUnit = (400 - (gap + 20)) / 10;
			maxUnit = (400 - gap) / 10;
			Coins.coinFactor = 0.5f;
			break;
		case HARD:
			gap = 160;
			randomUnit = (400 - (gap + 20)) / 10;
			maxUnit = (400 - gap) / 10;
			Coins.coinFactor = 1.0f;
			break;
		case MEDIUM:
			gap = 170;
			randomUnit = (400 - (gap + 20)) / 10;
			maxUnit = (400 - gap) / 10;
			Coins.coinFactor = 0.75f;
 			break;
		
		}
	}

}
