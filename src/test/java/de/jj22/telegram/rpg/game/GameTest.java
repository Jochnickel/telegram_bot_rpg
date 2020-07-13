package de.jj22.telegram.rpg.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.function.Function;

import org.junit.Test;

import de.jj22.telegram.rpg.game.exceptions.IllegalPlayerActivityException;

public class GameTest {

	@Test
	public void test() throws Exception {

		final var rubicksCube = new Item("Rubicks Cube", "A colorful <solvable> cube",
				cube -> new Spell("Solve", "Solve the <cube>!", cube).toArray());

		final var room0 = new Room("Training Room", "Some room. A <Rubicks Cube> is on a table", rubicksCube);

		final var player1 = room0.createNewPlayer("JJ", "A new player");

		try {
			player1.castSpell(null);
			fail();
		} catch (IllegalPlayerActivityException e) {
		}

		player1.collectItem(rubicksCube);
//		player1.castSpell(rubicksCubeSpell);

	}

}
