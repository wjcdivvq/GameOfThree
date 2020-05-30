package cc.gameofthree.player;

import java.util.Random;

public class Player {
	private final Random random;

	public Player() {
		this.random = new Random();
	}

	public Player(int seed) {
		this.random = new Random(seed);
	}

	public int startGameWithRandomNumber() {
		return random.nextInt(100); // TODO make configurable
	}

	public int makeMoveRespondingTo(int i) {
		return 3;
	}
}
