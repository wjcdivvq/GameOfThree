package cc.gameofthree.http;

import cc.gameofthree.player.Player;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MoveController {
    private final Player player = new Player();
    private final MoveClient moveApi;

    public MoveController(MoveClient moveApi) {
        this.moveApi = moveApi;
    }

    @PostMapping(value = "/playerDidMove", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> playerDidMove(@RequestBody String moveNumber) {
        Player.MoveResult move = player.makeMoveRespondingTo(Integer.parseInt(moveNumber));

        if (!move.hasWon()) {
            moveApi.playerDidMove(move.getNumber()).subscribe();
        } else {
            System.out.println("I won");
        }

        String message = String.format("Received your move '%s'", moveNumber);

        return Mono.just(message)
                .doAfterTerminate(() -> System.out.println(message));
    }

}
