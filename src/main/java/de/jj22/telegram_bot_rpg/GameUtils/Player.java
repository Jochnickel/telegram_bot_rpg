package de.jj22.telegram_bot_rpg.GameUtils;

public class Player {

	final private int player_id;
	final private int entity_id;

	public Player(int player_id, int entity_id) {
		this.player_id = player_id;
		this.entity_id = entity_id;
	}
	
	public Entity getEntity() {
		return new Entity(entity_id);
	}

}
