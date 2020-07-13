package de.jj22.telegram.rpg.game;

import java.util.List;
import java.util.Arrays;

public class EntityDescription {
	private final String description;
	private final List<String> labels;
	private final List<Entity> linkedItems;

	public EntityDescription(String description, Entity... entities) {
		this.description = description;
		linkedItems = Arrays.asList(entities);
		labels = null;
	}
}
