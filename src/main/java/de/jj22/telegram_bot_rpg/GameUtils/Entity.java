package de.jj22.telegram_bot_rpg.GameUtils;

public class Entity {
	final private int entity_id;
	final private String name;
	final private String description;
	final private Integer listID;

	public Entity(int entity_id, String name, String description, Integer list_id) {
		this.entity_id = entity_id;
		this.name = name;
		this.description = description;
		this.listID = list_id;
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
	
	public Integer getLinkedListID(){
		return listID;
	}
}
