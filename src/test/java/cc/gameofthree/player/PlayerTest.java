package cc.gameofthree.player;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void shouldStartTheGameWithRandomNumber() {
        Player player = new Player(0);

        List<Integer> startingNumbers = IntStream.range(0, 5)
                .boxed()
                .map(x -> player.startGameWithRandomNumber())
                .collect(Collectors.toList());

        assertThat(startingNumbers)
                .containsExactly(60, 48, 29, 47, 15);
    }

    @Test
    void shouldRespondToMoveByAddingOne() {
        Player player = new Player(0);

        assertThat(player.makeMoveRespondingTo(8) * 3).isEqualTo(9);
        assertThat(player.makeMoveRespondingTo(14) * 3).isEqualTo(15);
        assertThat(player.makeMoveRespondingTo(83) * 3).isEqualTo(84);
        assertThat(player.makeMoveRespondingTo(84935) * 3).isEqualTo(84936);
    }

}
