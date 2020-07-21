package de.jj22.telegram_bot_rpg.Game.Entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDatabase {
	final private Connection connection;

	public GameDatabase(String filename) throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
		_initDatabase();
	}

	private void _initDatabase() throws SQLException {
		var asd = connection.createStatement();
		asd.execute("CREATE TABLE IF NOT EXISTS FirstEntity ( entity_id INTEGER PRIMARY KEY)");
		asd.execute(
				"CREATE TABLE IF NOT EXISTS Entity ( entity_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT )");
		asd.execute(
				"CREATE TABLE IF NOT EXISTS EntityList ( list_id INTEGER , list_index INTEGER, linked_entity_id INTEGER, PRIMARY KEY(list_id, list_index) )");
		asd.execute(
				"CREATE TABLE IF NOT EXISTS Player ( player_id INTEGER PRIMARY KEY, entity_id INTEGER, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP )");
		asd.close();
	}

	public void deleteEntity(int entity_id) throws SQLException {
		preparedStatement("DELETE FROM Entity WHERE entity_id=?", entity_id).executeUpdate();
	}

	public void deleteEntityList(int list_id) throws SQLException {
		preparedStatement("DELETE FROM EntityList WHERE list_id=?", list_id).executeUpdate();
	}

	public void deleteFirstEntity() throws SQLException {
		connection.createStatement().execute("DELETE FROM FirstEntity");
	}

	public void deletePlayer(int player_id) throws SQLException {
		preparedStatement("DELETE FROM Player WHERE player_id=?", player_id).executeUpdate();
	}

	public ResultSet insertEntity(String name, String description) throws SQLException {
		final var stmnt = preparedStatement("INSERT INTO Entity (name, description) VALUES (?, ?)", name, description);
		stmnt.executeUpdate();
		return stmnt.getGeneratedKeys();
	}

	public void insertEntityList(int list_id, int index, int entity_id) throws SQLException {
		preparedStatement("INSERT INTO EntityList VALUES (?, ?, ?)", list_id, index, entity_id).executeUpdate();
	}

	public void insertPlayer(int player_id, int entity_id) throws SQLException {
		preparedStatement("INSERT INTO Player (player_id, entity_id) VALUES (?, ?)", player_id, entity_id).executeUpdate();
	}

	private PreparedStatement preparedStatement(String pre, Object... vals) throws SQLException {
		final var statement = connection.prepareStatement(pre, Statement.RETURN_GENERATED_KEYS);

		for (int i = 0; i < vals.length; i++) {
			statement.setObject(i + 1, vals[i]);
		}

		return statement;
	}

	/**
	 * 
	 * @param entity_id
	 * @return Integer entity_id, String name, String description
	 * @throws SQLException
	 */
	public ResultSet selectEntity(int entity_id) throws SQLException {
		final var statement = preparedStatement("SELECT entity_id, name, description FROM Entity WHERE entity_id=?", entity_id);
		return statement.executeQuery();
	}

	/**
	 * 
	 * @param list_id
	 * @param index
	 * @return Integer list_id, Integer list_index , Integer linked_entity_id
	 * @throws SQLException
	 */
	public ResultSet selectEntityList(int list_id, int index) throws SQLException {
		final var statement = preparedStatement("SELECT list_id, list_index, linked_entity_id FROM EntityList WHERE (list_id=? AND list_index=?)", list_id,
				index);
		return statement.executeQuery();
	}

	/**
	 * 
	 * @return Integer entity_id
	 * @throws SQLException
	 */
	public ResultSet selectFirstEntity() throws SQLException {
		final var statement = preparedStatement("SELECT entity_id FROM FirstEntity");
		return statement.executeQuery();
	}

	/**
	 * 
	 * @param player_id
	 * @return Integer player_id, Integer entity_id
	 * @throws SQLException
	 */
	public ResultSet selectPlayer(int player_id) throws SQLException {
		final var statement = preparedStatement("SELECT player_id, entity_id FROM Player WHERE player_id=?", player_id);
		return statement.executeQuery();
	}

	public void setFirstEntity(int entity_id) throws SQLException {
		deleteFirstEntity();
		preparedStatement("INSERT INTO FirstEntity VALUES (?)", entity_id).execute();
	}

	public void updateEntityDescription(int entity_id, String description) throws SQLException {
		preparedStatement("UPDATE Entity SET description=? WHERE entity_id=?", description, entity_id).execute();
	}

	public void updateEntityList(int list_id, int index, int entity_id) throws SQLException {
		preparedStatement("UPDATE EntityList SET entity_id=? WHERE (list_id=? AND list_index=?)", entity_id, list_id,
				index).execute();
	}

	public void updateEntityName(int entity_id, String name) throws SQLException {
		preparedStatement("UPDATE Entity SET name=? WHERE entity_id=?", name, entity_id).execute();
	}

	public void updatePlayerTimestamp(int player_id) throws SQLException {
		preparedStatement("UPDATE Player SET timestamp=CURRENT_TIMESTAMP WHERE player_id=?", player_id).execute();
	}

}
