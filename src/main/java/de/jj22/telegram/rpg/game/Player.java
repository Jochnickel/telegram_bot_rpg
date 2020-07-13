package de.jj22.telegram.rpg.game;

import java.util.HashMap;
import java.util.Stack;

import de.jj22.telegram.rpg.game.exceptions.IllegalPlayerActivityException;

class Player extends Entity {
	final private Stack<Room> currentRooms = new Stack<Room>();
	final private HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();

	Player(Room spawnRoom, String name, String description) {
		super(name, description);
		enterRoom(spawnRoom);
	}

	void castSpell(Spell rubicksCubeSpell) throws IllegalPlayerActivityException {

	}

	void collectItem(Item item, Room from) {
		if (null == item || null == from) {
			return;
		}
		if (isInRoom(from) && item.isCollectable()) {
			addItem(item);
		}
	}

	void addItem(Item item) {
		final var c = getInventoryAmount(item);
		inventory.put(item, c + 1);
	}

	Room getCurrentRoom() {
		return currentRooms.peek();
	}

	int getInventoryAmount(Item item) {
		if (null == item) {
			throw new NullPointerException();
		}
		final var amt = inventory.get(item);
		if (null == amt) {
			return 0;
		}
		return amt;
	}

	boolean isInRoom(Room room) {
		if (null == room) {
			return false;
		}
		return currentRooms.contains(room);
	}

	boolean isItemInInventory(Item item) {
		if (null == item) {
			throw new NullPointerException();
		}
		return inventory.containsKey(item);
	}

	void enterRoom(Room room) {
		if (null == room) {
			return;
		}
		currentRooms.add(room);
	}
	
	void leaveRoom() {
		if (currentRooms.size()>1) {
			currentRooms.pop();
		}
	}

}
