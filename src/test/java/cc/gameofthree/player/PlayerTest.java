package cc.gameofthree.player;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	@Test
	void shouldStartTheGameWithANumber() {
		Player player = new Player();

		int firstNumber = player.startGameWithRandomNumber();

		assertThat(firstNumber).isEqualTo(3);
	}

}
