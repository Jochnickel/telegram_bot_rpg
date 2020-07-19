package de.jj22.telegram_bot_rpg;

import java.sql.SQLException;

import de.jj22.telegram_bot_rpg.GameUtils.EntityManager;
import de.jj22.telegram_bot_rpg.GameUtils.PlayerNotFoundException;

public class Game {
	final private EntityManager entityManager;

	public Game() throws SQLException {
		entityManager = new EntityManager();
	}

	public String getPlayerDisplay(int player_id) throws SQLException {
		if(playerExists(player_id)) {
			return "Something gamy";
		} else {
			return "Join the game using /join name";
		}
	}

	private boolean playerExists(int player_id) throws SQLException {
		try{
			entityManager.getPlayer(player_id);
			return true;
		} catch (PlayerNotFoundException e) {
			return false;
		}
	}
}
