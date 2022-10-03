package updating;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import drawings.DrawBackgrounds;
import drawings.DrawCoins;
import main.GameLoop;
import main.Panel;
import rendering.Animations;



public class KeyBoardListener implements KeyListener{

	public static boolean menu = true;
	public static boolean menusettings = false;
	public static boolean gamesettings = false;
	public static boolean game = false;
	public static boolean jump = false;
	public static boolean gameover = false;
	public static boolean shop = false;
	public static boolean shop2 = false;
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		
		
		switch(GameStates.currentGameState) {
		
		case MENU:
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				setTrue(2);
//				DrawBackgrounds.updateCount++;
			}
			else {
				setTrue(4);
//				DrawBackgrounds.updateCount++;
			}
			
			
			break;
			
		case MENUSETTINGS:
			break;
			
		case GAMESETTINGS:
			if(e.getKeyCode() == KeyEvent.VK_F1) {
				if(DrawCoins.HUD == true) {
					DrawCoins.HUD = false;
				}else {
					DrawCoins.HUD = true;
				}
			}
			break;
			
		case GAME:
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				setTrue(3);
//				DrawBackgrounds.updateCount++;
			} else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				KeyBoardListener.jump = true;
			}else if(e.getKeyCode() == KeyEvent.VK_F1) {
				if(DrawCoins.HUD == true) {
					DrawCoins.HUD = false;
				}else {
					DrawCoins.HUD = true;
				}
			}
			break;
			
		case GAMEOVER:
			break;
		case SHOP:
			break;
		case SHOP2:
			break;

		}
	
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		if(GameStates.currentGameState == GameStates.GAME) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				GameLoop.sounds.flap();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	public void setTrue(int x) {
		KeyBoardListener.menusettings = false;
		KeyBoardListener.menu = false;
		KeyBoardListener.gamesettings = false;
		KeyBoardListener.game = false;
		KeyBoardListener.gameover = false;
		KeyBoardListener.shop = false;
		KeyBoardListener.shop2 = false;
		
		if(0<x && x<8) {
			switch(x) {
			case 1: KeyBoardListener.menu = true;
				break;
			case 2: KeyBoardListener.menusettings = true;
				break;
			case 3: KeyBoardListener.gamesettings = true;
				break;
			case 4: KeyBoardListener.game = true;
				break;
			case 5: KeyBoardListener.gameover = true;
				break;
			case 6: KeyBoardListener.shop = true;
				break;
			case 7: KeyBoardListener.shop2 = true;
				break;
			}
		}
	}
}
