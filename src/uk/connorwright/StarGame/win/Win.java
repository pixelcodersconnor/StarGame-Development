package uk.connorwright.StarGame.win;

import uk.connorwright.StarGame.Game;
import uk.connorwright.StarGame.Game.STATE;
import uk.connorwright.StarGame.audio.Jukebox;

public class Win {

	public static void getWin(){

		if (Game.enemy_kill >= 50) {
			Game.State = STATE.MENU;
			Jukebox.gameWin.play();
		}
	}
}
