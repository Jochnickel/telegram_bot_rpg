package de.jj22.telegram_bot_rpg;

import java.sql.SQLException;

import de.jj22.telegram_bot_rpg.GameUtils.Entity;
import de.jj22.telegram_bot_rpg.GameUtils.EntityManager;
import de.jj22.telegram_bot_rpg.GameUtils.EntityNotFoundException;
import de.jj22.telegram_bot_rpg.GameUtils.Player;
import de.jj22.telegram_bot_rpg.GameUtils.PlayerNotFoundException;

public class Game {
	final private static String HOWTO_JOIN_RESPONSE = "Join the game using /join name";
	final private EntityManager entityManager;

	public Game() throws SQLException {
		entityManager = new EntityManager();
		try {
			entityManager.getFirstEntity();
		} catch (EntityNotFoundException e) {
			final var table = entityManager.createEntity("Table", "An empty table", null);
			entityManager.setFirstEntity("Training Room", "A room with a <table | Enter>", new Entity[] {table});
		}
	}

	public String getPlayerDisplay(int player_id) throws SQLException, EntityNotFoundException {
		try {
			return getCurrentRoom(player_id).getDescription();
		} catch (PlayerNotFoundException e) {
			return HOWTO_JOIN_RESPONSE;
		}
	}

	public Entity getCurrentRoom(int player_id) throws PlayerNotFoundException, SQLException, EntityNotFoundException {
		final var rooms = entityManager.getLinkedEntity(getPlayer(player_id).getEntity(), 2);
		final var currentRoom = entityManager.getLinkedEntity(rooms, 0);
		return currentRoom;
	}
	

	private Player getPlayer(int player_id) throws PlayerNotFoundException, SQLException, EntityNotFoundException {
		return entityManager.getPlayer(player_id);
	}

	public Player createPlayer(int player_id, String name) throws PlayerAlreadyExistsException, SQLException, EntityNotFoundException {
		final var inv = createEntity("Inventory", "An empty inventory", null);
		final var spe = createEntity("Spells", "An empty spellbook", null);
		final var rom = createEntity("Rooms", "In a <room>", new Entity[] {entityManager.getFirstEntity()});
		final var ent = createEntity(name, "A new Player", new Entity[] { inv, spe, rom });
		final var player = entityManager.createPlayer(player_id, ent);
		return player;
	}

	public Entity createEntity(String name, String description, Entity[] linkedEntities) throws SQLException {
		return entityManager.createEntity(name, description, linkedEntities);
	}

	public void changePlayerName(int player_id, String name) throws SQLException, PlayerNotFoundException, EntityNotFoundException {
		entityManager.changeEntityName(getPlayer(player_id).getEntity(), name);
	}
	
	
}
