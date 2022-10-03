package buttons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import drawings.DrawCoins;
import main.GameLoop;

public class ShopButton {
	
	int x;
	int y;
	int width;
	int height;
	
	public ShopButton(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void drawInside(Graphics g, int mouseX, int mouseY, BufferedImage img) throws IOException{
		
		Graphics2D g2D = (Graphics2D) g;

		
		int imageXPosition = x + (width - drawImage(g , Color.RED, img).getWidth()) / 2;
		int imageYPosition = y + (height - drawImage(g , Color.RED, img).getHeight()) / 2;
//		int imageXPosition = x;
//		int imageYPosition = y;
//		int titleX = x + (width - g2D.getFontMetrics(new Font("KufiStandartGK", Font.BOLD, 16)).stringWidth("Shop")) / 2;
//		int titleX = x - (g2D.getFontMetrics(new Font("KufiStandartGK", Font.BOLD, 16)).stringWidth("Shop") + 10);
//		DrawBackgrounds.drawStrings(g, "Shop", 16, Color.WHITE.darker(), titleX, 20);
		
		if(mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
			g2D.setPaint(Color.BLUE);
			g2D.setStroke(new BasicStroke(15));
			g2D.drawRect(x, y, width, height);
			g2D.setPaint(Color.CYAN);
			g2D.fillRect(x, y,  width, height);
			g2D.drawImage(drawImage(g, Color.BLUE, img), imageXPosition, imageYPosition, null);
			
		}else {
			g2D.setPaint(Color.DARK_GRAY);
			g2D.setStroke(new BasicStroke(15));
			g2D.drawRect(x, y, width, height);
			g2D.setPaint(Color.GRAY);
			g2D.fillRect(x, y, width, height);
			g2D.drawImage(drawImage(g, Color.DARK_GRAY, img), imageXPosition, imageYPosition, null);
			
		}
	}
	
	public void inside(int xPos, int yPos) {
			if(xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height) {
				MyButton.kb.setTrue(6);
//				SoundLoop.sounds.click();
				GameLoop.sounds.click();
			} 
		}
	
	public BufferedImage drawImage(Graphics g, Color color, BufferedImage img) throws IOException {
    	
		double resizeFactor = (width*0.6) / 8;
		int rgb = color.getRGB();
		
		 for (int col = 0; col < img.getWidth(); col++) {
		        for(int row = 0; row < img.getHeight(); row++) {
		        	
		        	int p = img.getRGB(col, row);
		        	int alpha = (p>>24) & 0xff;

		        	if(alpha > 0) {
			        	img.setRGB(col, row, rgb);
		        	}
		        }
		    }
		 return DrawCoins.resizeImage(img, (int) (8*resizeFactor), (int) (10*resizeFactor));
	}
	
 }
