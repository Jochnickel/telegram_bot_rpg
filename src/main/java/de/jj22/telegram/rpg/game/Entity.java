package de.jj22.telegram.rpg.game;

import java.util.function.Function;

abstract class Entity {
	final String description;
	final String name;
	final long id;
	private static long idCounter = 0;

	Entity(String name, String description, Entity... entities) {
		this.name = name;
		this.description = description;
		this.id = ++idCounter;
	}
	Entity(String name, String description, Function<Entity, Entity[]> foo) {
		this.name = name;
		foo.apply(this);
		this.description = description;
		this.id = ++idCounter;
	}

	Entity[] toArray() {
		return new Entity[] { this };
	}

	@Override
	public String toString() {
		return "$%d".formatted(id);
	}
}