package cc.gameofthree.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class MoveClient {
    private final WebClient webClient;
    private final String otherPlayerUrl;

    public MoveClient(@Value("${otherPlayerUrl:http://localhost:8080}") String otherPlayerUrl) {
        this.webClient = WebClient.create(otherPlayerUrl);
        this.otherPlayerUrl = otherPlayerUrl;
    }

    public Mono<Void> playerDidMove(int move) {
        return webClient.post()
                .uri("/playerDidMove")
                .bodyValue(String.valueOf(move))
                .exchange()
                .retryWhen(Retry.backoff(10, Duration.ofSeconds(5)) //TODO make configurable
                        .doBeforeRetry(s -> System.out.println(String.format("Cannot connect to '%s', will retry...", otherPlayerUrl))))
                .doOnError(throwable -> System.out.println("Failed to send move to other player."))
                .then();
    }
}
