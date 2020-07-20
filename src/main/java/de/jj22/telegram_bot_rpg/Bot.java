package de.jj22.telegram_bot_rpg;

import java.sql.SQLException;
import java.util.Scanner;

import de.jj22.telegram_bot_rpg.GameUtils.EntityNotFoundException;
import de.jj22.telegram_bot_rpg.GameUtils.PlayerNotFoundException;

public class Bot {
	private static final String CANT_CREATE_PLAYER_AGAIN_RESPONSE = "Player already exists.";
	private static final String PLEASE_ENTER_VALID_NAME_RESPONSE = "Invalid name";
	private static final String STARTING_CONSOLE_MESSAGE = "Starting console";
	private static final String CMD_JOIN_PREFIX = "/join ";
	private static final String CMD_START_PREFIX = "/start";
	private static final int CONSOLE_USER_ID = 22;
	private static final String JOIN_FIRST_RESPONSE = "You have to join the game first";
	final private Game game;

	public Bot() throws SQLException, EntityNotFoundException {
		game = new Game();
	}

	private String evalUserInput(int user_id, String userInput) throws SQLException, EntityNotFoundException {
		if (userInput.startsWith(CMD_START_PREFIX)) {

			return game.getPlayerDisplay(user_id);

		} else if (userInput.startsWith("/changeName ")) {
			try {

				final var splitted = userInput.split("/changeName ");
				if (splitted.length < 1) {
					return PLEASE_ENTER_VALID_NAME_RESPONSE;
				}
				game.changePlayerName(user_id, splitted[1]);
				return game.getPlayerDisplay(user_id);
			} catch (PlayerNotFoundException e) {
				return JOIN_FIRST_RESPONSE;
			}
		} else if (userInput.startsWith(CMD_JOIN_PREFIX)) {
			try {

				final var splitted = userInput.split(CMD_JOIN_PREFIX);
				if (splitted.length < 1) {
					return PLEASE_ENTER_VALID_NAME_RESPONSE;
				}
				game.createPlayer(user_id, splitted[1]);
				return game.getPlayerDisplay(user_id);
			} catch (PlayerAlreadyExistsException e) {
				return CANT_CREATE_PLAYER_AGAIN_RESPONSE;
			}
		}
		return "what";

	}

	public void runConsole() throws SQLException, EntityNotFoundException {
		System.out.println(STARTING_CONSOLE_MESSAGE);
		System.out.println(evalUserInput(CONSOLE_USER_ID, CMD_START_PREFIX));
		final var scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			final var userInput = scanner.nextLine();
			final var answer = evalUserInput(CONSOLE_USER_ID, userInput);
			System.out.println(answer);
		}
		scanner.close();
	}

}
