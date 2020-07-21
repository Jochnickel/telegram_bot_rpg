package de.jj22.telegram_bot_rpg.Game;

import java.util.Arrays;

public class PlayerDisplay {
	final public String message;
	final public String[] options;

	public PlayerDisplay(String message) {
		this.message = message;
		options = new String[] {};
	}

	public PlayerDisplay(String message, String[] options) {
		this.message = message;
		this.options = options;
	}

	@Override
	public String toString() {
		return options.length > 0 ? message + "\noptions: " + Arrays.toString(options) : message;
	}
}
