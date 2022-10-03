package buttons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import drawings.DrawBackgrounds;
import drawings.DrawCoins;
import main.GameLoop;
import main.Panel;
import saves.Difficulty;
import saves.MyFile;
import saves.Shop;
import updating.KeyBoardListener;


public class MyButton {

	Font font = new Font("Monospaced", Font.BOLD, 20);
	int x, y, length, height;
	public static KeyBoardListener kb = new KeyBoardListener();

	public MyButton(int x, int y, int length, int height) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.height = height;
	}
	
	
	public void inside(Graphics g, int currentx, int currenty, String name) {

		
		Graphics2D g2D = (Graphics2D) g;
		int xplus = (length - g.getFontMetrics(font).stringWidth(name)) / 2;
		int yplus = height / 2 + 5;
		
		if(currentx >= x && currentx <= x + length && currenty >= y && currenty <= y + height) {
			g2D.setPaint(Color.BLUE);
			g2D.setStroke(new BasicStroke(15));
			g2D.drawRect(x, y, length, height);
			g2D.setPaint(Color.CYAN);
			g2D.fillRect(x, y,  length, height);
			g2D.setPaint(Color.BLACK);
			g2D.setFont(font);
			g2D.drawString(name, x + xplus, y + yplus);
		}else {
			g2D.setPaint(Color.DARK_GRAY);
			g2D.setStroke(new BasicStroke(15));
			g2D.drawRect(x, y, length, height);
			g2D.setPaint(Color.GRAY);
			g2D.fillRect(x, y, length, height);
			g2D.setFont(font);
			g2D.setPaint(Color.WHITE);
			g2D.drawString(name, x + xplus, y + yplus);
		}
	}
	
	public void inside(MyFile file, int currentx, int currenty, int state, boolean restart, boolean saveFiles) throws IOException{
		if(currentx >= x && currentx <= x + length && currenty >= y && currenty <= y + height) {
//			SoundLoop.sounds.click();
			GameLoop.sounds.click();
			if(saveFiles == true) {
			file.writeFile("gamefiles.txt", GameLoop.panel.setHighScore(file), String.valueOf(Panel.coins.writeCoins()), Difficulty.difficulty, Shop.selectedBird, DrawBackgrounds.music.getPos(), DrawBackgrounds.soundeffects.getPos(), String.valueOf(DrawCoins.HUD));
			System.out.println(Panel.coins.readFile());
			}
			if(restart == true) {
				Panel.bird.restart();
				}
			kb.setTrue(state);

		}
	}
	
	public void dispose(MyFile file, int currentx, int currenty, boolean saveFiles) throws IOException{
		if(currentx >= x && currentx <= x + length && currenty >= y && currenty <= y + height) {
//			SoundLoop.sounds.click();
			GameLoop.sounds.click();
			if(saveFiles == true) {
				file.writeFile("gamefiles.txt", GameLoop.panel.setHighScore(file), String.valueOf(Panel.coins.writeCoins()), Difficulty.difficulty, Shop.selectedBird, DrawBackgrounds.music.getPos(), DrawBackgrounds.soundeffects.getPos(), String.valueOf(DrawCoins.HUD));
				System.out.println(Panel.coins.readFile());
			}
			System.exit(0);
		}
	}
	
	
	
	
}
