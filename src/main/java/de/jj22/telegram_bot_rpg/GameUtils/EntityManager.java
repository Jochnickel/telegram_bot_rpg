package de.jj22.telegram_bot_rpg.GameUtils;

import java.sql.SQLException;

public class EntityManager {
	final private GameDatabase gameDatabase;

	public EntityManager() throws SQLException {
		gameDatabase = new GameDatabase("game.db");
	}

	public Player getPlayer(int player_id) throws PlayerNotFoundException, SQLException{
		final var sel = gameDatabase.selectPlayer(player_id);
		try {
			return new Player(sel.getInt(1), sel.getInt(2));
		} catch(Exception e) {
			throw new PlayerNotFoundException();
		}
	}
}
