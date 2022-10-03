package audio;

import drawings.DrawBackgrounds;
import updating.GameStates;

public class Music {
	
	Audio lobby = new Audio("lobby.wav");
	Audio game = new Audio("ingame.wav");
	
	public void play() {
		lobby.play();
		game.play();
		
		lobby.loop();
		game.loop();
	}
	
	public void controle() {
		if(GameStates.currentGameState == GameStates.MENU || GameStates.currentGameState == GameStates.MENUSETTINGS || GameStates.currentGameState == GameStates.SHOP) {
			lobby.controle((Float.valueOf(DrawBackgrounds.music.getPos()) * 36.0f) - 30.0f);
			game.mute();
		}else if (GameStates.currentGameState == GameStates.GAME || GameStates.currentGameState == GameStates.GAMESETTINGS) {
			game.controle((Float.valueOf(DrawBackgrounds.music.getPos()) * 36.0f) - 30.0f);
			lobby.mute();
		}else if(GameStates.currentGameState == GameStates.GAMEOVER) {
			lobby.mute();
			game.mute();
		}

	}
	
}