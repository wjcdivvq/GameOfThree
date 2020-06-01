package cc.gameofthree.http;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class MoveClient {
    private final HttpConfig httpConfig;

    public MoveClient(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }

    public Mono<Void> playerDidMove(int move) {
        return Mono.delay(Duration.ofMillis(httpConfig.getArtificialDelayInMs())).then(
                httpConfig.webClient()
                        .post()
                        .uri("/playerDidMove")
                        .bodyValue(String.valueOf(move))
                        .exchange()
                        .retryWhen(Retry.backoff(10, Duration.ofSeconds(5)) //TODO make configurable
                                .doBeforeRetry(s ->
                                        System.out.println(String.format(
                                                "Cannot connect to '%s', will retry...",
                                                httpConfig.getOtherPlayerUrl()))))
                        .doOnError(throwable -> System.out.println("Failed to send move to other player."))
                        .then());
    }
}
