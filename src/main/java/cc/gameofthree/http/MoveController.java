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
    }

    @PostMapping("/startGame")
    public Mono<String> startGame() {
        int firstMove = player.startGameWithRandomNumber();

        makeMove(firstMove);

        return Mono.just(String.format("Started game with number '%s'", firstMove));
    }

    @PostMapping(value = "/playerDidMove", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> playerDidMove(@RequestBody String moveNumber) {
        makeMove(Integer.parseInt(moveNumber));

        return Mono.just(String.format("Received your move '%s'", moveNumber))
                .doOnNext(System.out::println);
    }

    private void makeMove(int moveNumber) {
        Player.MoveResult move = player.makeMoveRespondingTo(moveNumber);

        if (!move.hasWon()) {
            moveApi.playerDidMove(move.getNumber()).subscribe();
        } else {
            System.out.println(String.format("I am application '%s' and my player won.", applicationName));
        }
    }

}
