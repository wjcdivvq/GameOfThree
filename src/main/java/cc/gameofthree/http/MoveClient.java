package cc.gameofthree.http;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MoveClient {
    private final WebClient webClient = WebClient.create("http://localhost:8080"); //TODO make configurable

    public Mono<Void> playerDidMove(int move) {
        return webClient.post()
                .uri("/playerDidMove")
                .bodyValue(String.valueOf(move))
                .exchange()
                .then();
    }
}
