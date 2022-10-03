package updating;

public enum GameStates {

	MENU,
	MENUSETTINGS,
	GAMESETTINGS,
	GAME,
	GAMEOVER,
	SHOP,
	SHOP2;
	
	public static boolean jump;
	
	public static GameStates currentGameState = MENU;
}
