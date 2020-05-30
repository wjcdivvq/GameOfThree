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

    public MoveResult makeMoveRespondingTo(int moveFromOtherPlayer) {
        return new MoveResult((moveFromOtherPlayer + 1) / 3);
    }

    public static class MoveResult {
        private final int number;

        public MoveResult(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public boolean hasWon() {
            return number == 1;
        }
    }
}
