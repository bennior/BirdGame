package drawings;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


import main.GameLoop;
import main.Panel;
import saves.Coins;
import saves.Shop;

public class ShopSlot {
	
	private int x; 
	private int y;
	private int height = 160;
	private int width = 150;
	private int price;
	private int birdtype;
	
	
	public ShopSlot(int x, int y, int price, int birdtype) {
		this.x = x;
		this.y = y;
		this.price = price;
		this.birdtype = birdtype;
	}
	
	public void drawSlot(Graphics g, BufferedImage img, String birdName, int xPos, int yPos) throws IOException {
		
		Graphics2D g2D = (Graphics2D) g;
		
		//Shop title
//		DrawBackgrounds.drawStrings(g2D, "SHOP", 100, Color.WHITE, 400, 100);

		//Draws current coin amount
		int coinlength = g2D.getFontMetrics(new Font("KufiStandartGK", Font.BOLD, 16)).stringWidth("Coins: " + Coins.coinAmount);
		int coinX = 800 - (coinlength + 25);
		DrawBackgrounds.drawStrings(g2D, "Coins: " + Coins.coinAmount, 16, Color.WHITE.darker(), coinX, 25);
		DrawCoins.drawCoinIcon(g2D, img.getSubimage(320, 0, 8, 10), 11, 14, coinX, 12, "Coins: " + Coins.coinAmount, 16);
		
		//Draws actual slot
		int namePos = x + (width - g2D.getFontMetrics(new Font("KufiStandartGK", Font.BOLD, 14)).stringWidth(birdName)) / 2;
		DrawBackgrounds.drawStrings(g, birdName, 14, Color.WHITE.darker(), namePos, y + 25);
//		g2D.setPaint(Color.WHITE.darker());
//		g2D.setStroke(new BasicStroke(3));
//		g2D.drawRect(x, y, width, height);
		g2D.drawImage(img.getSubimage(0, 40*birdtype, 40, 40), x + (width - 40) / 2, y + (height - 40) / 2, null);
//		g2D.drawImage(img.getSubimage(0, 40*birdtype, 40, 40), x + (width - 40) / 2, y + 45, null);
		currentState(g, xPos, yPos, img); //draws state
		
	}
	
	private int checkState() throws NumberFormatException, IOException {
		int state = 0;
		//Selected
//		if(Integer.parseInt(Panel.file.readFile("gamefiles.txt", 3)) == birdtype) {
		if(Shop.selectedBird.equals(String.valueOf(birdtype)) == true) {
			state = 1;
		} else if
		//Collectable
		(checkPurchased() == true) {
			state = 2;
		}
		else if
		//Not affordable
		(Coins.coinAmount < price) { //(Integer.parseInt(Panel.file.readFile("gamefiles.txt", 1)) < price)
			state = 3;
		}else if(Coins.coinAmount >= price) { //(Integer.parseInt(Panel.file.readFile("gamefiles.txt", 1))
		//Afforable
			state = 4;
		}
		
		return state;
	}
	
	
	public void currentState(Graphics g, int xPos, int yPos, BufferedImage img) throws NumberFormatException, IOException {
		
		switch(checkState()) {
			
		case 1: //Selected
			selected(g);
			break;
		case 2: //Collectable
			button(g, xPos, yPos, "Collect", Color.BLUE, Color.CYAN, Color.WHITE);
			break;
		case 3: //Not affordable
			button(g, xPos, yPos, "Buy", new Color(139,0,0), new Color(255,0,0), new Color(139,0,0));
			drawPrice(g, img);
			break;
		case 4: //Affordable
			button(g, xPos, yPos, "Buy", new Color(0,100,0), new Color(50,205,50), new Color(0,100,0));
			drawPrice(g, img);
			break;
		}
	}
	
	private void button(Graphics g, int xPos, int yPos, String name, Color priCol, Color secCol, Color fontCol) {
		
		Graphics2D g2D = (Graphics2D) g;

		int width = 50;
		int height = 25;
		int x = this.x + (this.width - width) / 2;
		int y = this.y + 115;
		Font font = new Font("KufiStandartGK", Font.BOLD, 13);
		int stringWidth = g2D.getFontMetrics(font).stringWidth(name);
		
		if(xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height) {
			g2D.setPaint(priCol);
			g2D.setStroke(new BasicStroke(10));
			g2D.drawRect(x, y, width, height);
			g2D.setPaint(secCol);
			g2D.fillRect(x, y,  width, height);
			g2D.setFont(font);
			g2D.setPaint(Color.BLACK);
			g2D.drawString(name, x + (width - stringWidth) / 2, y + 17);
		}else {
			g2D.setPaint(Color.DARK_GRAY);
			g2D.setStroke(new BasicStroke(10));
			g2D.drawRect(x, y, width, height);
			g2D.setPaint(Color.GRAY);
			g2D.fillRect(x, y, width, height);
			g2D.setFont(font);
			g2D.setPaint(fontCol);
			g2D.drawString(name, x + (width - stringWidth) / 2, y + 17);
		}
	}
	
	public void isClicked(int xPos, int yPos) throws NumberFormatException, IOException {
		
		if(xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height && checkState() == 2) {
			Shop.selectedBird = String.valueOf(birdtype);
//			SoundLoop.sounds.click();
			GameLoop.sounds.click();
		}
		
		if(xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height && checkState() == 4) {
			writePurchased();
			Coins.coinAmount -= price;
//			SoundLoop.sounds.click();
			GameLoop.sounds.click();
 		}
	}
	
	private void selected(Graphics g) {
		
		Graphics2D g2D = (Graphics2D) g;
		
		Font font = new Font("KufiStandartGK", Font.BOLD, 13);
		
		int x = this.x + (width - g2D.getFontMetrics(font).stringWidth("Selected")) / 2;
		int y = this.y + 130;
		
		g2D.setFont(font);
		g2D.setPaint(Color.WHITE.darker());
		g2D.drawString("Selected", x, y);

	}
	
	private void writePurchased() throws IOException {
		for(int i = 4; i < 10; i++) {
			
			if(Shop.outputArray(i).equals("null")) {
				Shop.array[i - 4] = String.valueOf(birdtype);
				i = 10;
			}
		}
	}
	
	private boolean checkPurchased() {
				
		boolean check = false;

		for(int i = 4; i < 10; i++) {
			
			if(Shop.array[i - 4].equals(String.valueOf(birdtype)) == true) {
				check = true;
				i = 10;
			}else {
				check = false;
			}
		}
		return check;
	}
	
	private void drawPrice(Graphics g, BufferedImage img) {
		
		Graphics2D g2D = (Graphics2D) g;
		
		int coinlength = g2D.getFontMetrics(new Font("KufiStandartGK", Font.BOLD, 12)).stringWidth(String.valueOf(price));
		int coinX = x + (width - coinlength) / 2;
		DrawBackgrounds.drawStrings(g2D, String.valueOf(price), 12, Color.WHITE.darker(), coinX, y + 50);
		DrawCoins.drawCoinIcon(g2D, img.getSubimage(320, 0, 8, 10), 8, 11, coinX - 2, y + 40, String.valueOf(price), 12);
		
	}
	
	
	
} 
