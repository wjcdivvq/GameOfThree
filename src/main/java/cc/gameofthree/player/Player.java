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

    public FirstMoveResult startGameWithRandomNumber(int upperBound) {
        return new FirstMoveResult(random.nextInt(upperBound) + 1);
    }

    public MoveResult makeMoveRespondingTo(int moveFromOtherPlayer) {
        int resultingNumber = (moveFromOtherPlayer + 1) / 3;
        int addedNumber = resultingNumber * 3 - moveFromOtherPlayer;
        return new MoveResult(resultingNumber, addedNumber);
    }

    public static class FirstMoveResult {
        private final int startingNumber;

        public FirstMoveResult(int startingNumber) {
            this.startingNumber = startingNumber;
        }

        public int getStartingNumber() {
            return startingNumber;
        }

        public boolean hasWon() {
            return startingNumber == 1;
        }
    }

    public static class MoveResult {
        private final int resultingNumber;
        private final int addedNumber;

        public MoveResult(int resultingNumber, int addedNumber) {
            this.resultingNumber = resultingNumber;
            this.addedNumber = addedNumber;
        }

        public int getResultingNumber() {
            return resultingNumber;
        }

        public int getAddedNumber() {
            return addedNumber;
        }

        public boolean hasWon() {
            return resultingNumber == 1;
        }
    }
}
