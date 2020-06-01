package cc.gameofthree.http;

import cc.gameofthree.player.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = MoveController.class)
@ContextConfiguration(classes = {MoveClient.class, Game.class, MoveController.class})
class MoveControllerWebfluxTest {
    @Autowired
    WebTestClient webClient;

    @Test
    void shouldAcceptMove() {
        webClient.post()
                .uri("/playerDidMove")
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue("4")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("I received your move '4'. I added '-1', and answered with '1'.\nI am application 'app1' and my player won.");
    }
}