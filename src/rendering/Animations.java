package rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Animations {
	
	private int animationTick;
	private int animationIndex;
	private Font font;
//	public static boolean play = false;
	
	
	
	public void beatingText(Graphics g, String text, Color color, int height1, int height2, int animationSpeed) {
		
		animationBase(animationSpeed, 1);
		
		switch(animationIndex) {
		case 0: 
			font = new Font("KufiStandardGK", Font.BOLD, height1);
			int x = (800 - g.getFontMetrics(font).stringWidth(text)) / 2;
			
			g.setFont(font);
			g.setColor(color);
			g.drawString(text, x, 300);
			break;
		case 1: 
			font = new Font("KufiStandardGK", Font.BOLD, height2);
			int x1 = (800 - g.getFontMetrics(font).stringWidth(text)) / 2;
			
			g.setFont(font);
			g.setColor(color);
			g.drawString(text, x1, 300);
			break;
		}
	}
	
	
//	public void countDown(Graphics g, int animationSpeed, boolean play) {
//		
//		animationBase(animationSpeed, 2);
//		font = new Font("Monospaced", Font.BOLD, 40);
//		
//		switch(animationIndex) {
//		
//		case 0:
//			int x = (800 - g.getFontMetrics(font).stringWidth("3")) / 2;
//
//			g.setFont(font);
//			g.setColor(Color.WHITE);
//			g.drawString("3", x, 180);
//			break;
//			
//		case 1:
//			int x1 = (800 - g.getFontMetrics(font).stringWidth("2")) / 2;
//
//			g.setFont(font);
//			g.setColor(Color.WHITE);
//			g.drawString("3", x1, 180);
//			break;
//			
//		case 2:
//			int x2 = (800 - g.getFontMetrics(font).stringWidth("1")) / 2;
//
//			g.setFont(font);
//			g.setColor(Color.WHITE);
//			g.drawString("3", x2, 180);
//			play = false;
//			break;
//		}
//		
//	}
	
	
	
	
	
	
	public void animationBase(int animationSpeed, int indexCount) {
		animationTick++;
		if(animationTick >= animationSpeed) {
			animationIndex++;
			animationTick = 0;
			if(animationIndex > indexCount) {
				animationIndex = 0;
			}
		}
	}
}
