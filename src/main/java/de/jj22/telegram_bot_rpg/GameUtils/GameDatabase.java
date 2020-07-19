package de.jj22.telegram_bot_rpg.GameUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDatabase {
	final private Connection connection;

	public GameDatabase(String filename) throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
		initDatabase();
	}

	
	private void initDatabase() throws SQLException {
		connection.createStatement().execute("CREATE TABLE IF NOT EXISTS Entity ( entity_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, linked_entities_list_id INTEGER )");
		connection.createStatement().execute("CREATE TABLE IF NOT EXISTS EntityLists ( ll_id INTEGER PRIMARY KEY AUTOINCREMENT, entity_id INTEGER, next_ll_id INTEGER )");
		connection.createStatement().execute("CREATE TABLE IF NOT EXISTS Player ( player_id INTEGER PRIMARY KEY, entity_id INTEGER )");
	}


	private PreparedStatement preparedStatement(String pre, Object... vals) throws SQLException {
		
		final var args = new String[vals.length];
		for (int i= 0; i< vals.length; i++) {
			args[i] = String.valueOf(vals[i]);
		}

		final var statement = connection.prepareStatement(pre, args);
		return statement;
	}
	/**
	 * 
	 * @param player_id
	 * @return player_id, entity_id
	 * @throws SQLException
	 */
	public ResultSet selectPlayer(int player_id) throws SQLException {
		final var statement = preparedStatement("SELECT * FROM Player WHERE player_id=?", player_id);
		return statement.executeQuery();
	}

}
