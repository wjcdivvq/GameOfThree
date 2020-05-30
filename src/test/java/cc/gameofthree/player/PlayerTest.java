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

}
