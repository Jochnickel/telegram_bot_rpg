package de.jj22.telegram_bot_rpg.Game.Entities;

public class Entity {
	final private int entity_id;
	final private String name;
	final private String description;

	public Entity(int entity_id, String name, String description) {
		this.entity_id = entity_id;
		this.name = name;
		this.description = description;
	}

	public int getEntityID() {
		return this.entity_id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
}
