package de.jj22.telegram_bot_rpg;

import java.sql.SQLException;
import java.util.Scanner;

public class Bot {
	final private Game game;

	public Bot() throws SQLException {
		game = new Game();
	}

	private String evalUserInput(int user_id, String userInput) throws SQLException {
		switch (userInput) {
		case "/start":
			return game.getPlayerDisplay(user_id);

		default:
			break;
		}
		
		return "what";
	}

	public void runConsole() throws SQLException {
		final var user_id = 22;
		System.out.println("Starting console");
		System.out.println(evalUserInput(user_id, "/start"));
		final var scanner  = new Scanner(System.in);
		while(scanner.hasNext()) {
			final var userInput = scanner.nextLine();
			final var answer = evalUserInput(user_id, userInput);
			System.out.println(answer);
		}
	}

}
