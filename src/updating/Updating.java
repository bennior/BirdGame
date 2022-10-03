package updating;

import java.io.IOException;

import audio.Music;
import main.Panel;
import saves.Difficulty;
import saves.MyFile;

public class Updating {
		
	public void updating() {
		
		
		if(KeyBoardListener.menu == true) {
			GameStates.currentGameState = GameStates.MENU;
		} 
		else if(KeyBoardListener.gamesettings == true) {
			GameStates.currentGameState = GameStates.GAMESETTINGS;
		}
		else if(KeyBoardListener.game == true) {
			GameStates.currentGameState = GameStates.GAME;
		} 
		else if(KeyBoardListener.gameover == true) {
			GameStates.currentGameState = GameStates.GAMEOVER;
		}
		else if(KeyBoardListener.menusettings == true)
		{
			GameStates.currentGameState = GameStates.MENUSETTINGS;
		}else if(KeyBoardListener.shop == true) 
		{
			GameStates.currentGameState = GameStates.SHOP;
		}else if(KeyBoardListener.shop2 == true) 
		{
			GameStates.currentGameState = GameStates.SHOP2;
		}

		if(KeyBoardListener.jump == true) {
			GameStates.jump = true;
			KeyBoardListener.jump = false;
		}
		
//		changeDifficulty();
	}
	
//	private void changeDifficulty() {
//		
//		try {
//			Difficulty.currentDifficulty = Difficulty.valueOf(Panel.file.readFile("gamefiles.txt", 2));
//		} catch (IOException e) {
////			e.printStackTrace();
//		}
//	}
}
