package de.jj22.telegram_bot_rpg;

import java.sql.SQLException;

import de.jj22.telegram_bot_rpg.Game.Entities.EntityNotFoundException;

public class Main {

	public static void main(String[] args) throws SQLException, EntityNotFoundException{
		new Bot().runConsole();
	}

}
