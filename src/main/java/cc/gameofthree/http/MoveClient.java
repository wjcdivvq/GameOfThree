package cc.gameofthree.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MoveClient {
    private final WebClient webClient;

    public MoveClient(@Value("${otherPlayerUrl:http://localhost:8080}") String otherPlayerUrl) {
        this.webClient = WebClient.create(otherPlayerUrl);
    }

    public Mono<Void> playerDidMove(int move) {
        return webClient.post()
                .uri("/playerDidMove")
                .bodyValue(String.valueOf(move))
                .exchange()
                .then();
    }
}
