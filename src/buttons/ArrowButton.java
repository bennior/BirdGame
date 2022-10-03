package buttons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import drawings.DrawCoins;
import main.GameLoop;

public class ArrowButton {
	
	private int x, y, width, height;
	private BufferedImage img;
	private double rotationRequired;
	private AffineTransform tx;
	private AffineTransformOp af;


	public ArrowButton(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void left(Graphics g, int xPos, int yPos, BufferedImage img) throws IOException{
		
		Graphics2D g2D = (Graphics2D) g;

		defineAffineTransform(-180.0);
		int imageXPosition = (x + (width - setImage(g , Color.RED, img).getWidth()) / 2) - 10;
		int imageYPosition = y + (height - setImage(g , Color.RED, img).getHeight()) / 2;

		
		if(xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height) {
			g2D.setPaint(Color.BLUE);
			g2D.setStroke(new BasicStroke(15));
			g2D.drawRect(x, y, width, height);
			g2D.setPaint(Color.CYAN);
			g2D.fillRect(x, y,  width, height);
			g2D.drawImage(af.filter(setImage(g, Color.BLUE, img), null), imageXPosition, imageYPosition, null);
			
		}else {
			g2D.setPaint(Color.DARK_GRAY);
			g2D.setStroke(new BasicStroke(15));
			g2D.drawRect(x, y, width, height);
			g2D.setPaint(Color.GRAY);
			g2D.fillRect(x, y, width, height);
			g2D.drawImage(af.filter(setImage(g, Color.DARK_GRAY, img), null), imageXPosition, imageYPosition, null);
		}
		
	}
	
	public void right(Graphics g, int xPos, int yPos, BufferedImage img) throws IOException{
		
		Graphics2D g2D = (Graphics2D) g;

		int imageXPosition = x + (width - setImage(g , Color.RED, img).getWidth()) / 2;
		int imageYPosition = y + (height - setImage(g , Color.RED, img).getHeight()) / 2;

		
		if(xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height) {
			g2D.setPaint(Color.BLUE);
			g2D.setStroke(new BasicStroke(15));
			g2D.drawRect(x, y, width, height);
			g2D.setPaint(Color.CYAN);
			g2D.fillRect(x, y,  width, height);
			g2D.drawImage(setImage(g, Color.BLUE, img), imageXPosition, imageYPosition, null);
			
		}else {
			g2D.setPaint(Color.DARK_GRAY);
			g2D.setStroke(new BasicStroke(15));
			g2D.drawRect(x, y, width, height);
			g2D.setPaint(Color.GRAY);
			g2D.fillRect(x, y, width, height);
			g2D.drawImage(setImage(g, Color.DARK_GRAY, img), imageXPosition, imageYPosition, null);
		}
	}
	
	public void click(int state, int xPos, int yPos) {
		if(xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height) {
			MyButton.kb.setTrue(state);
//			SoundLoop.sounds.click();
			GameLoop.sounds.click();
		} 
	}
	
	public BufferedImage setImage(Graphics g, Color color, BufferedImage img) throws IOException{
	    	
			double resizeFactor = (width*0.4) / 8;
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
	
	private void defineAffineTransform(double angle) {
		rotationRequired = Math.toRadians(angle);

		tx = AffineTransform.getRotateInstance(rotationRequired, 25, 25);
		af = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	}
}

