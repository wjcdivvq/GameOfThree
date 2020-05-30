package cc.gameofthree.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoveControllerTest {
    @Mock
    MoveClient moveApi;

    MoveController moveController;

    @BeforeEach
    void before() {
        when(moveApi.playerDidMove(anyInt())).thenReturn(Mono.empty());
        moveController = new MoveController(moveApi, "app1");
    }

    @Test
    void shouldRespondWithMove() {
        moveController.playerDidMove("10").block();

        verify(moveApi).playerDidMove(3);
    }

    @Test
    void shouldStartTheGame() {
        moveController.startGame().block();

        verify(moveApi).playerDidMove(anyInt());
    }
}