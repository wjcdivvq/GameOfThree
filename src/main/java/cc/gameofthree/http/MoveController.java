package cc.gameofthree.http;

import cc.gameofthree.player.Game;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MoveController {
    private final Game game;

    public MoveController(Game game) {
        this.game = game;
    }

    @PostMapping("/startGame")
    public Mono<String> startGame(@RequestBody String upperBoundAsString) {
        return Mono.just(upperBoundAsString != null
                ? game.makeGameStart(Integer.parseInt(upperBoundAsString))
                : game.makeGameStart())
                .doOnNext(System.out::println);
    }

    @PostMapping(value = "/playerDidMove", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> playerDidMove(@RequestBody String moveNumber) {
        return Mono.just(moveNumber)
                .map(Integer::parseInt)
                .map(game::makeMove)
                .doOnNext(System.out::println);
    }
}
