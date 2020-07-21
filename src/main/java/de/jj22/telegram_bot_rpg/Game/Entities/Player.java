package de.jj22.telegram_bot_rpg.Game.Entities;

/**
 * Player Entities contain Inventory, Spells, Rooms
 * The Current room is room 0, they are prepended
 * @author Joachim
 *
 */
public class Player {

	final private int player_id;
	final private Entity entity;
	
	

	public Player(int player_id, Entity entity) {
		this.player_id = player_id;
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}

	public int getPlayerID() {
		return player_id;
	}
}
