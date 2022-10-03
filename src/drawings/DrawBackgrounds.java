package drawings;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import buttons.ArrowButton;
import buttons.DifficultyButton;
import buttons.MyButton;
import buttons.ShopButton;
import buttons.Slider;
import main.Panel;
import rendering.Animations;
import saves.Difficulty;
import saves.MyFile;
import updating.GameStates;

public class DrawBackgrounds {
		
		public static Animations ani = new Animations();
		//Buttons MenuSettings
		public static MyButton mr = new MyButton(350, 335, 100, 40);
		public static MyButton mq = new MyButton(350, 275, 100, 40);
		//Buttons GameSettings
		public static MyButton gsc = new MyButton(350, 80, 100, 40);
		public static MyButton gsr = new MyButton(350, 145, 100, 40);
		public static MyButton gsm = new MyButton(350, 210, 100, 40);
		public static MyButton gsq = new MyButton(350, 275, 100, 40);
		//Buttons GameOver
		public static MyButton gor = new MyButton(190, 296, 100, 40);
		public static MyButton gom = new MyButton(350, 296, 100, 40);
		public static MyButton goq = new MyButton(510, 296, 100, 40);
		//Button Shop
		public static MyButton sr = new MyButton(350, 275, 100, 40);
		
		//DifficultyButtons
		public static DifficultyButton easy = new DifficultyButton(220, 75, 20, 20); //200
		public static DifficultyButton medium = new DifficultyButton(415, 75, 20, 20); //400
		public static DifficultyButton hard = new DifficultyButton(560, 75, 20, 20); //600
		
		//ShopButton
		public static ShopButton shop = new ShopButton(748, 8, 45, 45);
		
		//ShopSlots
		public static ShopSlot orangeBird = new ShopSlot(87, 90, 0, 0);
		public static ShopSlot purpleBird = new ShopSlot(324, 90, 30000, 3);
		public static ShopSlot blueBird = new ShopSlot(561, 90, 50000, 4);
		public static ShopSlot greenBird = new ShopSlot(87, 90, 100000, 5);
		public static ShopSlot phoenixBird = new ShopSlot(324, 90, 200000, 1);
		public static ShopSlot iceBird = new ShopSlot(561, 90, 500000, 2);
		
		//ArrowButtons
		public static ArrowButton arrow = new ArrowButton(350, 335, 100, 40);
		
		public static Slider music;
		public static Slider soundeffects;
		
		public static int mouseX;
		public static int mouseY;
		
		private double rotationRequired;
		AffineTransform tx;
//		BufferedImage coinImage;
		
		public DrawBackgrounds() {
			init();
			try {
				Difficulty.difficulty = Panel.file.readFile("gamefiles.txt", 2);
				System.out.println(Difficulty.difficulty);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

//		private String fileName = "gamefiles.txt";
//		AffineTransformOp af;
//		public static int updateCount = 3;
		
		
	public void paintBackgrounds(Graphics g, BufferedImage img, MyFile file) {
		
//		try {
//			coinImage = DrawCoins.resizeImage(img.getSubimage(320, 10, 8, 10), 16, 20);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		
		switch(GameStates.currentGameState) {
		
		case MENU:
//				if(updateCount >= 1) {
//				g.drawImage(img.getSubimage(0, 0, 800, 400), 0, 0, null);
//				updateCount -= 1;
//				}
				g.setColor(Color.DARK_GRAY.darker());
				g.fillRect(0, 0, 800, 400);
				
				centerStrings(g, "BirdGame", 120, Color.WHITE);
				ani.beatingText(g, "Press any key to start!", Color.WHITE.darker(), 40, 39, 60);
				
				break;
				
		case MENUSETTINGS:
//				if(updateCount >= 1) {
//				g.drawImage(img.getSubimage(800, 0, 800, 400), 0, 0, null);
//				updateCount = 0;
//				}
				g.setColor(Color.DARK_GRAY.darker());
				g.fillRect(0, 0, 800, 400);
				
//			try {
//				shop.drawInside(g, mouseX, mouseY, DrawCoins.resizeImage(img.getSubimage(320, 10, 8, 10), 16, 20));
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
				mq.inside(g, mouseX, mouseY, "Quit");
 				mr.inside(g, mouseX, mouseY, "Return");
 				hard.drawButton(g, "Hard");
 				medium.drawButton(g, "Medium");
 				easy.drawButton(g, "Easy");
 				music.draw(g, "Music");
 				soundeffects.draw(g, "Soundeffects");
 				try {
 				shop.drawInside(g, mouseX, mouseY, img.getSubimage(320, 10, 8, 10));
 				hard.drawSelected(g, 8, 8, "HARD");
 				medium.drawSelected(g, 8, 8, "MEDIUM");
 				easy.drawSelected(g, 8, 8, "EASY");
// 				System.out.println(MyMouseListener.difficulty);
 				}
 				catch(Exception e) { //System.out.println("lol");
 				}

				
				break;
			
		case GAMESETTINGS:
//				if(updateCount >= 1) {
//				g.drawImage(img.getSubimage(800, 0, 800, 400), 0, 0, null);
//				updateCount = 0;
//				}
				g.setColor(Color.DARK_GRAY.darker());
				g.fillRect(0, 0, 800, 400);
			
				gsc.inside(g, mouseX, mouseY, "Resume");
				gsr.inside(g, mouseX, mouseY, "Restart");
				gsm.inside(g, mouseX, mouseY, "Menu");
				gsq.inside(g, mouseX, mouseY, "Quit");

				break;
			
		case GAME:
//				if(updateCount >= 1) {
//				g.drawImage(img.getSubimage(800, 0, 800, 400), 0, 0, null);
//				updateCount = 0;
//				}
				g.setColor(Color.DARK_GRAY.darker());
				g.fillRect(0, 0, 800, 400);			
				break;
			
		case GAMEOVER:
//				if(updateCount >= 1) {
//				g.drawImage(img.getSubimage(1600, 0, 800, 400), 0, 0, null);
//				updateCount = 0;
//				}
				g.setColor(Color.DARK_GRAY.darker());
				g.fillRect(0, 0, 800, 400);
				
				gor.inside(g, mouseX, mouseY, "Restart");
				gom.inside(g, mouseX, mouseY, "Menu");
				goq.inside(g, mouseX, mouseY, "Quit");
				
				centerStrings(g, "Game Over!", 60, Color.WHITE);
				drawStrings(g, "Your score: " + Panel.score, 20, Color.WHITE.darker(), 50);	
				Panel.drawCoins.drawResult(g);
				break;
		case SHOP:
				g.setColor(Color.DARK_GRAY.darker());
				g.fillRect(0, 0, 800, 400);
				
			try {
				orangeBird.drawSlot(g, img, "Orange Bird", mouseX, mouseY);
				purpleBird.drawSlot(g, img, "Purple Bird", mouseX, mouseY);
				blueBird.drawSlot(g, img, "Blue Bird", mouseX, mouseY);
				arrow.right(g, mouseX, mouseY, img.getSubimage(320, 20, 60, 36));
			} catch (IOException e) {
				e.printStackTrace();
			}
				
				sr.inside(g, mouseX, mouseY, "Return");
			break;
		case SHOP2:
			g.setColor(Color.DARK_GRAY.darker());
			g.fillRect(0, 0, 800, 400);
			
			try {
				greenBird.drawSlot(g, img, "Green Bird", mouseX, mouseY);
				iceBird.drawSlot(g, img, "Ice Bird", mouseX, mouseY);
				phoenixBird.drawSlot(g, img, "Phoenix Bird", mouseX, mouseY);
				arrow.left(g, mouseX, mouseY, img.getSubimage(320, 20, 60, 36));
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			break;
				}
	}
	
	public void centerStrings(Graphics g, String title, int height, Color color) {
		Font font = new Font("KufiStandartGK", Font.BOLD, height);

		int x = (800 - g.getFontMetrics(font).stringWidth(title)) / 2;
		
		g.setFont(font);
		g.setColor(color);
		g.drawString(title, x, 200);
	
	}
	
	public static void drawStrings(Graphics g, String title, int height, Color color, int x, int y) {
		Font font = new Font("KufiStandartGK", Font.BOLD, height);
		
		g.setFont(font);
		g.setColor(color);
		g.drawString(title, x, y);
	
	}
	
	public void drawStrings(Graphics g, String title, int height, Color color, int y) {
		Font font = new Font("KufiStandartGK", Font.BOLD, height);
		int x = (800 - g.getFontMetrics(font).stringWidth(title)) / 2;

		g.setFont(font);
		g.setColor(color);
		g.drawString(title, x, y);
	
	}
	
	public void drawNewHighscore(Graphics g, String title, String text, int height, Color color, int y) {
		Font font = new Font("KufiStandartGK", Font.BOLD, height);
		int x = (800 - g.getFontMetrics(font).stringWidth(title)) / 2;
		
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, x + 142, y); //142, 159

	}
	
	public void drawVerticalStrings(Graphics g, String title, Font font, Color color, int x, int y, double value) {

		rotationRequired = Math.toRadians(value);
		tx = AffineTransform.getRotateInstance(rotationRequired, 25, 25);
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setColor(color);
		g2D.setTransform(tx);
		g2D.setFont(font);
		g2D.drawString(title, x, y);
	}
	
	private void init() {
		try {
			music = new Slider (250, 210, 300.0f, 20, Float.valueOf(Panel.file.readFile("gamefiles.txt", 10)));
			soundeffects = new Slider(250, 150, 300.0f, 20, Float.valueOf(Panel.file.readFile("gamefiles.txt", 11)));
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
