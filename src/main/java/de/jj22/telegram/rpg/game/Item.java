package de.jj22.telegram.rpg.game;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

class Item extends Entity {
	final boolean collectable = false;
	final Spell mainSpell = null;
	final private Set<Spell> spellList = new HashSet<Spell>();
	
	Item(String name, String description, Entity... entities) {
		super(name, description, entities);
	}

	public Item(String name, String description, Function<Entity, Entity[]> foo) {
		super(name, description, foo);
	}

	public boolean isCollectable() {
		return collectable;
	}


}
