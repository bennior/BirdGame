package buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.GameLoop;

public class Slider {
	
	int x;
	int y;
	float width;
	int height;
	float pos;
	
	public Slider(int x, int y, float width, int height, float pos) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.pos = pos;
//		init();
	}
	
	public void draw(Graphics g, String title) {
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setPaint(Color.GRAY);
		g2D.setFont(new Font("KufiStandartGk", Font.BOLD, 17));
		g2D.drawString(title, x, y - 7);
		g2D.setPaint(Color.DARK_GRAY);
		g2D.fillRect(x, y, (int) width, height);
		g2D.setPaint(Color.GRAY);
		g2D.fillRect(x, y, (int) (width*pos), height);
	}
	
	public void isClicked(int xPos, int yPos) {
		
			if(xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height) {
				pos = (xPos - x) / width;
//				SoundLoop.sounds.click();
				GameLoop.sounds.click();
			}
		
		}
	
	public void isDragged(int pressx, int pressy, int dragx) {
		
		if(pressx >= x && pressx <= x + width && pressy >= y && pressy <= y + height) {
			
			pos = (dragx - x) / width;
			
			if(dragx < x) {
				pos = 0.0f;
			}else if(dragx > x + width) {
				pos = 1.0f;
			}

		}
	}
	
	public String getPos() {
		
		return String.valueOf(pos);
	}
	
//	private void init() {
//		
//		try {
//			pos = Double.valueOf(Panel.file.readFile("gamefiles.txt", 10));
//		} catch (NumberFormatException | IOException e) {
//			e.printStackTrace();
//		}
//	}

	}

