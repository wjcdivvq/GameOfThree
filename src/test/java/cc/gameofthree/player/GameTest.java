package cc.gameofthree.player;

import cc.gameofthree.http.MoveClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameTest {
    @Mock
    MoveClient moveApi;

    Game game;

    @BeforeEach
    void before() {
        when(moveApi.playerDidMove(anyInt())).thenReturn(Mono.empty());
        game = new Game(moveApi, "app1");
    }

    @Test
    void shouldRespondWithMove() {
        game.makeMove(10);

        verify(moveApi).playerDidMove(3);
    }

    @Test
    void shouldStartTheGameWithSpecifiedUpperBound() {
        game.makeGameStart(100);

        verify(moveApi).playerDidMove(anyInt());
    }

    @Test
    void shouldStartTheGameWithoutUpperBound() {
        game.makeGameStart();

        verify(moveApi).playerDidMove(anyInt());
    }
}