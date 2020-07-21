package de.jj22.telegram_bot_rpg.Game;

import de.jj22.telegram_bot_rpg.Game.Entities.Entity;

public class ActionPipe {
	final String data;
	final Entity caller;
	final int player_id;

	public ActionPipe(String data, Entity caller, int player_id) {
		this.data = data;
		this.caller = caller;
		this.player_id = player_id;
	}
}
