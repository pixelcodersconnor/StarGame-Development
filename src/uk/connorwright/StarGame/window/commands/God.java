package uk.connorwright.StarGame.window.commands;

import uk.connorwright.StarGame.Game;
import uk.connorwright.StarGame.Game.STATE;
import uk.connorwright.StarGame.util.Strings;

public class God {

	public static void godMode() {
		if (Game.State == STATE.GAME) {
			Game.HEALTH = 1000000;	


		} else {
			System.out.println(Strings.COMMAND_GOD_ERROR);
		}
	}

}
