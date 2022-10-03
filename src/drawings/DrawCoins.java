package drawings;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import main.Frame;

import main.GameLoop;
import main.Panel;
import saves.Coins;
import saves.MyFile;
import updating.GameStates;

public class DrawCoins {
	
	private int lastCoins = Coins.coins;
	private int between;
	private int animationIndex;
	private Color color;
	public static int alpha = 0;
	public static float alphaImage = 1f;
	public static boolean checkHighScore;
	private double rotationRequired;
	private AffineTransform at;
	private AffineTransformOp ato;
	private Font font = new Font("KufiStandartGK", Font.BOLD, 20);
	public static boolean HUD;
	
	DrawBackgrounds background;
	MyFile file;
	BufferedImage img;

	public DrawCoins(DrawBackgrounds background, MyFile file, BufferedImage img) {
		this.background = background;
		this.file = file;
		this.img = img;
		init();
	}
	
	//Pop up animation -> how many coins you got
	private void fadeAnimation(Graphics g, String text, int x, int y) {
		
		int fontHeight = (int) (30*((30.0 / Frame.getScreenDevice()) / 0.0075));

		Font font = new Font("KufiStandartGK", Font.BOLD, 110 - fontHeight);
		
		DrawBackgrounds.ani.animationBase(40, 0);
		if(alpha > 10 && alphaImage > 0.01f) {
		switch(animationIndex) {
		case 0: 
			alpha -= 3;
			alphaImage -= 0.012f;
			color = new Color(255, 255, 255, alpha);
			if(GameStates.currentGameState == GameStates.GAME && HUD == true || GameStates.currentGameState == GameStates.GAMESETTINGS && HUD == true) {

				background.drawVerticalStrings(g, text, font, color, x, y, -10.0);
				try {
					drawVerticalCoins(g, text, x, y, font, fontHeight);
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
			
			break;
			}
		}else {
			lastCoins = (int) (Coins.coins*Coins.coinFactor);
		}
	}
	
	//Draws your collected coins ingame
	public void drawCoins(Graphics g) {
		if(GameStates.currentGameState == GameStates.GAME) {
		DrawBackgrounds.drawStrings(g, "Coins: " + Integer.toString(Panel.coins.calculateGameCoins()), 16, Color.WHITE.darker(), 25, 55);
		}
	}
	
	//Draws pop up -> uses fadeAnimation
	public void drawAdditionalCoins(Graphics g) {
		between = (int) (Coins.coins*Coins.coinFactor - lastCoins);
		
		int x = (int) (200*((200.0 / Frame.getScreenDevice()) / 0.05));
		int y = (int) (200*((200.0 / Frame.getScreenDevice()) / 0.05));
		fadeAnimation(g, "+ " + between, 900 - x, 800 - y);
		
	}
	
	//Draws in GameOver your result plus (if) highscore
	public void drawResult(Graphics g) {
		
		try {
			
			if(GameLoop.panel.checkHighScore(file) == true) {
				background.drawStrings(g, "Collected coins: " + Coins.collectedCoins, 20, Color.WHITE.darker(), 80);
				drawCoinIcon(g, img.getSubimage(320, 0, 8, 10), 12, 16, (800 - g.getFontMetrics(font).stringWidth("Collected coins: " + Coins.collectedCoins)) / 2, 64, "Collected coins: " + Coins.collectedCoins, 20);
				background.drawNewHighscore(g, "Collected coins: " + Coins.collectedCoins, "+ " + (int) (10000*Coins.coinFactor) + "    (New Highscore)", 20, Color.WHITE.darker(), 110);
				drawCoinIcon(g, img.getSubimage(320, 0, 8, 10), 12, 16, 142 + (800 - g.getFontMetrics(font).stringWidth("Collected coins: " + Coins.collectedCoins)) / 2, 94, "+ " + (int) (10000*Coins.coinFactor), 20);
			}else {
				background.drawStrings(g, "Collected coins: " + Coins.collectedCoins, 20, Color.WHITE.darker(), 80); //320, 159	
				drawCoinIcon(g, img.getSubimage(320, 0, 8, 10), 12, 16, (800 - g.getFontMetrics(font).stringWidth("Collected coins: " + Coins.collectedCoins)) / 2, 64, "Collected coins: " + Coins.collectedCoins, 20);
				
				//test
				

				}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}
	
	//Draws Coin
	public static void drawCoinIcon(Graphics g, BufferedImage originalImage, int targetWidth, int targetHeight, int xPos, int y, String text, int stringHeight) {
				
			Font font = new Font("KufiStandartGK", Font.BOLD, stringHeight);
			int x = xPos + g.getFontMetrics(font).stringWidth(text) + 5;
					
			try {
				g.drawImage(resizeImage(originalImage, targetWidth, targetHeight), x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	//Scales Image
	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
		
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		
		return outputImage;
	}
	
	public void drawVerticalCoins(Graphics g, String text, int x, int y, Font font, int fontHeight) throws IOException {
		at = AffineTransform.getRotateInstance(rotationRequired, 25, 25);
		ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		
		int resizeY = (110 - fontHeight) - 14;
		int resizeX = (int) (8 * (resizeY / 10) + 2);
		
		
//		int xPosition = (int) (x + Math.cos(10.0) * g.getFontMetrics(font).stringWidth(text));
//		int yPosition = (int) (y - Math.cos(10.0) * g.getFontMetrics(font).stringWidth(text));
		int xPosition = (int) (x + g.getFontMetrics(font).stringWidth(text) + 7);
		int yPosition = y - resizeY;
		Graphics2D g2D = (Graphics2D) g;
		
        g2D.setComposite(AlphaComposite.SrcOver.derive(alphaImage));
        g2D.drawImage(ato.filter(resizeImage(img.getSubimage(320, 0, 8, 10), resizeX, resizeY), null), xPosition, yPosition, null);
        

	}
	
	private void init() {
		try {
			HUD = Boolean.valueOf(Panel.file.readFile("gamefiles.txt", 12));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
