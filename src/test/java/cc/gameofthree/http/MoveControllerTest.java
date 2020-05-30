package cc.gameofthree.http;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = MoveController.class)
class MoveControllerTest {
    @Autowired
    WebTestClient webClient;

    @Test
    void shouldAcceptMove() {
        webClient.post()
                .uri("/playerDidMove")
                .bodyValue(4)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Received your move '4'");
    }
}