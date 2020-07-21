package de.jj22.telegram_bot_rpg.Game;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.regex.Pattern;

import de.jj22.telegram_bot_rpg.Game.Entities.Entity;
import de.jj22.telegram_bot_rpg.Game.Entities.EntityManager;
import de.jj22.telegram_bot_rpg.Game.Entities.EntityNotFoundException;
import de.jj22.telegram_bot_rpg.Game.Entities.Player;
import de.jj22.telegram_bot_rpg.Game.Entities.PlayerAlreadyExistsException;
import de.jj22.telegram_bot_rpg.Game.Entities.PlayerNotFoundException;

public class Game {
	final private static String HOWTO_JOIN_RESPONSE = "Join the game using /join name";
	final private EntityManager entityManager;

	public Game() throws SQLException {
		entityManager = new EntityManager();
		try {
			entityManager.getFirstEntity();
		} catch (EntityNotFoundException e) {
			final var table = entityManager.createEntity("Table", "An empty table");
			final var room = entityManager.createEntity("Training Room", "A room with a < table | Enter >", table);
			entityManager.setFirstEntity(room);
		}
	}

	private String getCommandLabel(String fullCommand) {
		final var pat = Pattern.compile("<?\\s*(.*?)\\s*\\|");
		final var mat = pat.matcher(fullCommand);
		mat.find();
		return mat.group(1);
	}

	private PlayerDisplay convertDisplay(String toConvert) {
		final var pat = Pattern.compile("<.*?>");
		final var mat = pat.matcher(toConvert);
		final var options = new LinkedList<String>();

		var converted = toConvert;
		for (; mat.find();) {
			final var opt = getCommandLabel(mat.group());
			options.add(opt);
			converted = converted.replaceFirst("<.*?>", opt);
		}

		return new PlayerDisplay(converted, options.toArray(new String[options.size()]));
	}

	public PlayerDisplay getPlayerDisplay(int player_id) throws SQLException, EntityNotFoundException {
		try {
			final var desc = getCurrentRoom(player_id).getDescription();
			return convertDisplay(desc);
		} catch (PlayerNotFoundException e) {
			return new PlayerDisplay(HOWTO_JOIN_RESPONSE);
		}
	}

	public Entity getCurrentRoom(int player_id) throws PlayerNotFoundException, SQLException, EntityNotFoundException {
		final var roomsEntity = entityManager.getLinkedEntity(getPlayer(player_id).getEntity(), 2);
		final var currentRoom = entityManager.getLinkedEntity(roomsEntity, 0);
		return currentRoom;
	}

	private Player getPlayer(int player_id) throws PlayerNotFoundException, SQLException, EntityNotFoundException {
		return entityManager.getPlayer(player_id);
	}

	public Player createPlayer(int player_id, String name)
			throws PlayerAlreadyExistsException, SQLException, EntityNotFoundException {
		final var inv = createEntity("Inventory", "An empty inventory");
		final var spe = createEntity("Spells", "An empty spellbook");
		final var rom = createEntity("Rooms", "In a <room>", entityManager.getFirstEntity());
		final var ent = createEntity(name, "A new Player. <Inventory>, <Spells>, <Rooms>", inv, spe, rom);
		final var player = entityManager.createPlayer(player_id, ent);
		return player;
	}

	public Entity createEntity(String name, String description, Entity... linkedEntities) throws SQLException {
		return entityManager.createEntity(name, description, linkedEntities);
	}

	public void changePlayerName(int player_id, String name)
			throws SQLException, PlayerNotFoundException, EntityNotFoundException {
		entityManager.changeEntityName(getPlayer(player_id).getEntity(), name);
	}

	public void enterRoom(int player_id, Entity room)
			throws IllegalPlayerAction, PlayerNotFoundException, SQLException, EntityNotFoundException {
		if (playerCanEnterRoom(player_id, room)) {
			final var entity = entityManager.getPlayer(player_id).getEntity();
			final var roomsEntity = entityManager.getLinkedEntity(entity, 2);
			final var rooms = entityManager.getLinkedEntities(roomsEntity);
			rooms.add(0, room);
			entityManager.setLinkedEntities(roomsEntity, rooms.toArray(new Entity[rooms.size()]));
		} else {
			throw new IllegalPlayerAction();
		}
	}

	public boolean playerCanEnterRoom(int player_id, Entity room) {
		System.err.println("please implement Game.playerCanEnterRoom");
		return true;
	}

	public void leaveRoom(int player_id)
			throws IllegalPlayerAction, SQLException, EntityNotFoundException, PlayerNotFoundException {
		final var entity = entityManager.getPlayer(player_id).getEntity();
		final var roomsEntity = entityManager.getLinkedEntity(entity, 2);
		final var rooms = entityManager.getLinkedEntities(roomsEntity);
		if (rooms.size() > 1) {
			rooms.remove(0);
			entityManager.setLinkedEntities(roomsEntity, (Entity[]) rooms.toArray());
		} else {
			throw new IllegalPlayerAction();
		}
	}

	public void enterWorld(int player_id, Entity[] rooms)
			throws IllegalPlayerAction, SQLException, PlayerNotFoundException, EntityNotFoundException {
		final var entity = entityManager.getPlayer(player_id).getEntity();
		final var roomsEntity = entityManager.getLinkedEntity(entity, 2);
		if (rooms.length > 0) {
			entityManager.setLinkedEntities(roomsEntity, rooms);
		} else {
			throw new IllegalPlayerAction();
		}
	}

	public void collectItem(int player_id, Entity item)
			throws IllegalPlayerAction, PlayerNotFoundException, SQLException, EntityNotFoundException {
		if (playerCanCollect(player_id, item)) {
			final var entity = getPlayer(player_id).getEntity();
			final var inventoryEntity = entityManager.getLinkedEntity(entity, 0);
			final var inv = entityManager.getLinkedEntities(inventoryEntity);
			inv.add(item);
			entityManager.setLinkedEntities(inventoryEntity, (Entity[]) inv.toArray());
		} else {
			throw new IllegalPlayerAction();
		}
	}

	private boolean playerCanCollect(int player_id, Entity item) {
		System.err.println("please implement Game.playerCanCollect");
		return true;
	}

	public void say(int player_id, String message) {
		System.err.println("please implement Game.say");
		System.err.println(message);
	}

	public void announce(Entity room, String message) {
		System.err.println("please implement Game.announce");
		System.err.println(message);
	};

	public void announce(String message) {
		System.err.println("please implement Game.announce");
		System.err.println(message);
	}

	public void useAction(int player_id, Entity entity, String actionName) throws ActionNotFoundException {
		
	}
	
	

	public PlayerDisplay usePlayerCommand(int player_id, String userInput)
			throws PlayerNotFoundException, SQLException, EntityNotFoundException {
		final var command = userInput;
		try {
			useAction(player_id, getCurrentRoom(player_id), command);
			return getPlayerDisplay(player_id);
		} catch (ActionNotFoundException e) {
		}
		try {
			useAction(player_id, getSpellBook(player_id), command);
			return getPlayerDisplay(player_id);
		} catch (ActionNotFoundException e) {
		}
		try {
			useAction(player_id, getCurrentRoom(player_id), command);
			return getPlayerDisplay(player_id);
		} catch (ActionNotFoundException e) {
		}
		return new PlayerDisplay("command not Found");
	}

	private Entity getSpellBook(int player_id) throws PlayerNotFoundException, SQLException, EntityNotFoundException {
		final var entity = getPlayer(player_id).getEntity();
		return entityManager.getLinkedEntity(entity, 1);
	}

}
