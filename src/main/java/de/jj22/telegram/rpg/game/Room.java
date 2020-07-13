package de.jj22.telegram.rpg.game;

class Room extends Entity {

	Room(String name, String description) {
		super(name, description);
	}

	Room(String string, String string2, Entity ...entities) {
		super(string, string2, entities);
	}

	Player createNewPlayer(String name, String description) {
		return new Player(this, name, description);
	}
}
