package cc.gameofthree.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    Player player;

    @BeforeEach
    void before() {
        player = new Player(0);
    }

    @Test
    void shouldStartTheGameWithRandomNumber() {
        List<Integer> startingNumbers = IntStream.range(0, 5)
                .boxed()
                .map(x -> player.startGameWithRandomNumber())
                .collect(Collectors.toList());

        assertThat(startingNumbers)
                .containsExactly(60, 48, 29, 47, 15);
    }

    @Test
    void shouldRespondToMoveByAddingOne() {
        assertThat(player.makeMoveRespondingTo(8) * 3).isEqualTo(9);
        assertThat(player.makeMoveRespondingTo(14) * 3).isEqualTo(15);
        assertThat(player.makeMoveRespondingTo(83) * 3).isEqualTo(84);
        assertThat(player.makeMoveRespondingTo(84935) * 3).isEqualTo(84936);
    }


    @Test
    void shouldRespondToMoveBySubtractingOne() {
        assertThat(player.makeMoveRespondingTo(10) * 3).isEqualTo(9);
        assertThat(player.makeMoveRespondingTo(16) * 3).isEqualTo(15);
        assertThat(player.makeMoveRespondingTo(85) * 3).isEqualTo(84);
        assertThat(player.makeMoveRespondingTo(84937) * 3).isEqualTo(84936);
    }

    @Test
    void shouldRespondToMoveByAddingNothing() {
        assertThat(player.makeMoveRespondingTo(9) * 3).isEqualTo(9);
        assertThat(player.makeMoveRespondingTo(15) * 3).isEqualTo(15);
        assertThat(player.makeMoveRespondingTo(84) * 3).isEqualTo(84);
        assertThat(player.makeMoveRespondingTo(84936) * 3).isEqualTo(84936);
    }

}
