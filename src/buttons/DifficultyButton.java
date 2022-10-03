package buttons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import saves.Difficulty;


public class DifficultyButton {

	//Constructor x, y, width, height, file
	int x;
	int y;
	int width;
	int height;
	private Font font = new Font("StandartGK", Font.BOLD, 20);
	
	public DifficultyButton(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	//boolean inside -> in MyMouseListener class -> method summarizes all three inside methods, output string -> call method in MyButton (if this doesn't work use a public static string)
	public boolean inside(int mouseX, int mouseY) {
		if(x <= mouseX && x + width >= mouseX && y <= mouseY && y + height >= mouseY) {
			return true;
		}else {
			return false;
		}
	}
	
	//method draw button
	public void drawButton(Graphics g, String difficulty) {
		
		Graphics2D g2D = (Graphics2D) g;
		
		int stringWidth = g2D.getFontMetrics(font).stringWidth(difficulty);
		
		g2D.setPaint(Color.DARK_GRAY);
		g2D.setStroke(new BasicStroke(8));
		g2D.drawRect(x, y, width, height);
		g2D.setPaint(Color.GRAY);
		g2D.fillRect(x, y, width, height);
		
		g2D.drawString(difficulty, x - (stringWidth + 15), y + 15);
	}
	
	//method switch case difficulty -> draw selected
	public void drawSelected(Graphics g, int selectedWidth, int selectedHeight, String expectedDifficulty) throws IOException {
		String currentDifficulty = Difficulty.difficulty;
		
		if(expectedDifficulty.equals(currentDifficulty)) {
			
		Graphics2D g2D = (Graphics2D) g;
		int dotX = x + ((width - selectedWidth) / 2);
		int dotY = y + ((height - selectedHeight) / 2);
		
		g2D.setPaint(Color.BLACK);
		g2D.fillRect(dotX, dotY, selectedWidth, selectedHeight);
		
//		System.out.println("worked");
		}
	}
	
	//in Updating class -> currentDifficulty = file.read(2)

	
	
	
}
