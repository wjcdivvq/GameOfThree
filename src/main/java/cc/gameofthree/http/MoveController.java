package cc.gameofthree.http;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MoveController {

    @PostMapping("/playerDidMove")
    public Mono<String> playerDidMove(@RequestBody int moveNumber) {
        return Mono.just(String.format("Received your move '%s'", moveNumber));
    }
}
