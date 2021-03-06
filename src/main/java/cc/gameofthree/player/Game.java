package cc.gameofthree.player;

import cc.gameofthree.http.MoveClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Game {
    static final int DEFAULT_UPPER_BOUND = Integer.MAX_VALUE;

    private final Player player = new Player();
    private final MoveClient moveApi;
    private final String applicationName;

    public Game(
            MoveClient moveApi,
            @Value("${applicationName:app1}") String applicationName) {
        this.moveApi = moveApi;
        this.applicationName = applicationName;
        System.out.println("Waiting for the game to start...");
    }

    public String makeGameStart() {
        return makeGameStart(DEFAULT_UPPER_BOUND);
    }

    public String makeGameStart(int upperBound) {
        Player.FirstMoveResult firstMove = player.startGameWithRandomNumber(upperBound);

        if (firstMove.hasWon()) {
            return String.format("Started the game and immediately won because it was a '%d'",
                    firstMove.getStartingNumber());
        }

        // Asynchronously inform the other player about the starting move
        moveApi.playerDidMove(firstMove.getStartingNumber()).subscribe();

        return String.format("Started game with number '%s'", firstMove.getStartingNumber());
    }

    public String makeMove(int moveNumber) {
        Player.MoveResult move = player.makeMoveRespondingTo(moveNumber);

        final String response = String.format("I received your move '%d'. I added '%d', and answered with '%d'.",
                moveNumber,
                move.getAddedNumber(),
                move.getResultingNumber());

        if (move.hasWon()) {
            return response + String.format("\nI am application '%s' and my player won.", applicationName);
        }

        // Asynchronously inform the other player about the move
        moveApi.playerDidMove(move.getResultingNumber()).subscribe();

        return response;
    }
}
