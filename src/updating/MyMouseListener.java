package updating;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//import audio.SoundLoop;

import drawings.DrawBackgrounds;
import main.GameLoop;
import saves.Difficulty;
import saves.MyFile;

public class MyMouseListener implements MouseListener, MouseMotionListener{
	
	KeyBoardListener kb;
	MyFile file;
	private int pressx;
	private int pressy;

	
	public MyMouseListener(MyFile file) {
		this.file = file;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int currentx = e.getX();
		int currenty = e.getY();
		
		try {
		switch(GameStates.currentGameState) {
		case MENU:
			break;
				
		case MENUSETTINGS:
			if(e.getButton() == MouseEvent.BUTTON1) {
				DrawBackgrounds.mr.inside(file, currentx, currenty, 1, true, true);
				DrawBackgrounds.mq.dispose(file, currentx, currenty, true);
				DrawBackgrounds.shop.inside(currentx, currenty);
				summaryInside(currentx, currenty);
			}
			break;
			
		case GAMESETTINGS:
			
			if(e.getButton() == MouseEvent.BUTTON1) {
				DrawBackgrounds.gsc.inside(file, currentx, currenty, 4, false, false);
				DrawBackgrounds.gsm.inside(file,currentx, currenty, 1, true, true);
				DrawBackgrounds.gsq.dispose(file, currentx, currenty, true);
				DrawBackgrounds.gsr.inside(file, currentx, currenty, 4, true, true);
				
			}
			break;
			
		case GAME:
			break;
			
		case GAMEOVER:
			if(e.getButton() == MouseEvent.BUTTON1) {
				DrawBackgrounds.gom.inside(file, currentx, currenty, 1, true, true);
				DrawBackgrounds.goq.dispose(file, currentx, currenty, true);
				DrawBackgrounds.gor.inside(file, currentx, currenty, 4, true, true);
			}
			break;
		case SHOP:
			if(e.getButton() == MouseEvent.BUTTON1) {
				DrawBackgrounds.sr.inside(file, currentx, currenty, 2, true, true);
				DrawBackgrounds.orangeBird.isClicked(currentx, currenty);
				DrawBackgrounds.purpleBird.isClicked(currentx, currenty);
				DrawBackgrounds.blueBird.isClicked(currentx, currenty);
				DrawBackgrounds.arrow.click(7, currentx, currenty);
			}
			break;
		case SHOP2:
			if(e.getButton() == MouseEvent.BUTTON1) {
				DrawBackgrounds.arrow.click(6, currentx, currenty);
				DrawBackgrounds.phoenixBird.isClicked(currentx, currenty);
				DrawBackgrounds.iceBird.isClicked(currentx, currenty);
				DrawBackgrounds.greenBird.isClicked(currentx, currenty);

			}
			break;
			
			
		}
		}
		catch(Exception error) {}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressx = e.getX();
		pressy = e.getY();
		
		if(GameStates.currentGameState == GameStates.MENUSETTINGS) {
			DrawBackgrounds.music.isClicked(pressx, pressy);
			DrawBackgrounds.soundeffects.isClicked(pressx, pressy);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		DrawBackgrounds.mouseX = e.getX();
		DrawBackgrounds.mouseY = e.getY();
		
		if(GameStates.currentGameState == GameStates.MENUSETTINGS) {
			DrawBackgrounds.music.isDragged(pressx, pressy, e.getX());
			DrawBackgrounds.soundeffects.isDragged(pressx, pressy, e.getX());
		}
		

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		DrawBackgrounds.mouseX = e.getX();
		DrawBackgrounds.mouseY = e.getY();
		}
	
	public void summaryInside(int mouseX, int mouseY) {
				
		if(DrawBackgrounds.easy.inside(mouseX, mouseY) == true) {
//			SoundLoop.sounds.click();
			GameLoop.sounds.click();
			Difficulty.difficulty = "EASY";
		}else if(DrawBackgrounds.medium.inside(mouseX, mouseY) == true) {
//			SoundLoop.sounds.click();
			GameLoop.sounds.click();
			Difficulty.difficulty = "MEDIUM";
		}else if(DrawBackgrounds.hard.inside(mouseX, mouseY) == true) {
//			SoundLoop.sounds.click();
			GameLoop.sounds.click();
			Difficulty.difficulty = "HARD";
		}
	} 

}
