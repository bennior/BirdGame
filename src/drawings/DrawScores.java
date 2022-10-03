package drawings;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Panel;
import saves.MyFile;
import updating.GameStates;

public class DrawScores {
	
	private Font font = new Font("Monospaced", Font.BOLD, 30);
	
	private MyFile file;
	private DrawCoins coins;
	private BufferedImage img;
	
	public DrawScores(MyFile file, DrawCoins coins, BufferedImage img) {
		this.file = file;
		this.coins = coins;
		this.img = img;
	}
	
	//summary of all
	public void drawScores(Graphics g, int score) {
		drawScore(g, score);
		drawHighScore(g);
		drawCoins(g);
	}
	
	
	//Draws you current score
	private void drawScore(Graphics g, int score) {
		if(GameStates.currentGameState == GameStates.GAME) {
		int x = (800 - g.getFontMetrics(font).stringWidth(Integer.toString(score))) / 2;
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(Integer.toString(score), x, 75);
		}
	}
	
	//Draws your highscore
	private void drawHighScore(Graphics g) {
		if(GameStates.currentGameState == GameStates.GAME) {
			try {
				DrawBackgrounds.drawStrings(g, "Highscore: " + file.readFile("gamefiles.txt", 0), 16, Color.WHITE.darker(), 25, 35);
				}
				catch(Exception e) {
					
				}
		}

	}
	
	//Draws all coin elements
	private void drawCoins(Graphics g) {
		if(GameStates.currentGameState == GameStates.GAME) {
		coins.drawCoins(g);         //Graphics, originalImg, targetWidth, targetHeight, xPos of String, y, text, textHeight
		DrawCoins.drawCoinIcon(g, img.getSubimage(320, 0, 8, 10), 11, 14, 25, 42, "Coins: " + Integer.toString(Panel.coins.calculateGameCoins()), 16);
		coins.drawAdditionalCoins(g);
		}
	}
}
