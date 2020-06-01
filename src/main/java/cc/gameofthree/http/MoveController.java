package cc.gameofthree.http;

import cc.gameofthree.player.Player;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MoveController {
    private final Player player = new Player();
    private final MoveClient moveApi;
    private final String applicationName;

    public MoveController(
            MoveClient moveApi,
            @Value("${applicationName:app1}") String applicationName) {
        this.moveApi = moveApi;
        this.applicationName = applicationName;
        System.out.println("Waiting for the game to start...");
    }

    @PostMapping("/startGame")
    public Mono<String> startGame(@RequestBody String upperBoundAsString) {
        int upperBound = upperBoundAsString == null ? 100 : Integer.parseInt(upperBoundAsString);

        return Mono.just(upperBound)
                .map(this::makeGameStart)
                .doOnNext(System.out::println);
    }

    @PostMapping(value = "/playerDidMove", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> playerDidMove(@RequestBody String moveNumber) {
        return Mono.just(moveNumber)
                .map(Integer::parseInt)
                .map(this::makeMove)
                .doOnNext(System.out::println);
    }

    private String makeGameStart(int upperBound) {
        Player.FirstMoveResult firstMove = player.startGameWithRandomNumber(upperBound);

        if (firstMove.hasWon()) {
            return String.format("Started the game and immediately won because it was a '%d'",
                    firstMove.getStartingNumber());
        }

        makeMove(firstMove.getStartingNumber());

        return String.format("Started game with number '%s'", firstMove);
    }

    private String makeMove(int moveNumber) {
        Player.MoveResult move = player.makeMoveRespondingTo(moveNumber);

        final String response = String.format("I received your move '%d'. I added '%d', and answered with '%d'.",
                moveNumber,
                move.getAddedNumber(),
                move.getResultingNumber());

        if (!move.hasWon()) {
            moveApi.playerDidMove(move.getResultingNumber()).subscribe();
            return response;
        } else {
            return response + String.format("\nI am application '%s' and my player won.", applicationName);
        }
    }

}
