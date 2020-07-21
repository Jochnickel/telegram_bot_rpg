package de.jj22.telegram_bot_rpg;

import java.sql.SQLException;
import java.util.Scanner;

import de.jj22.telegram_bot_rpg.Game.Game;
import de.jj22.telegram_bot_rpg.Game.PlayerDisplay;
import de.jj22.telegram_bot_rpg.Game.Entities.EntityNotFoundException;
import de.jj22.telegram_bot_rpg.Game.Entities.PlayerAlreadyExistsException;
import de.jj22.telegram_bot_rpg.Game.Entities.PlayerNotFoundException;

public class Bot {
	private static final PlayerDisplay CANT_CREATE_PLAYER_AGAIN_RESPONSE = new PlayerDisplay("Player already exists.");
	private static final String CMD_JOIN_PREFIX = "/join ";
	private static final String CMD_START_PREFIX =  "/start";
	private static final int CONSOLE_USER_ID = 22;
	private static final PlayerDisplay JOIN_FIRST_RESPONSE = new PlayerDisplay("You have to join the game first");
	private static final PlayerDisplay PLEASE_ENTER_VALID_NAME_RESPONSE = new PlayerDisplay("Invalid name");
	private static final PlayerDisplay STARTING_CONSOLE_MESSAGE = new PlayerDisplay("Starting console");
	private static final String CMD_CHANGE_NAME = "/changeName ";
	final private Game game;

	public Bot() throws SQLException{
		game = new Game();
	}

	private PlayerDisplay evalUserInput(int user_id, String userInput) throws SQLException, EntityNotFoundException, PlayerNotFoundException {
		if (userInput.startsWith(CMD_START_PREFIX)) {

			return game.getPlayerDisplay(user_id);

		} else if (userInput.startsWith(CMD_CHANGE_NAME)) {
			try {

				final var splitted = userInput.split(CMD_CHANGE_NAME);
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
		} else {
			return game.usePlayerCommand(user_id, userInput);
		}

	}

	public void runConsole() throws SQLException, EntityNotFoundException, PlayerNotFoundException {
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
