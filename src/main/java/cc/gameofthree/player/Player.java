package cc.gameofthree.player;

import java.util.Random;
import java.util.stream.IntStream;

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

	public int makeMoveRespondingTo(int moveFromOtherPlayer) {
		return (moveFromOtherPlayer+1)/3;
	}
}
