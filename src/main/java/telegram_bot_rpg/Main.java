package telegram_bot_rpg;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		final var connection = DriverManager.getConnection("jdbc:sqlite:test.db");
		System.out.println("hi");
	}

}
