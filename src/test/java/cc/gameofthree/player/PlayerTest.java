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
                .map(x -> player.startGameWithRandomNumber(100))
                .map(Player.FirstMoveResult::getStartingNumber)
                .collect(Collectors.toList());

        assertThat(startingNumbers)
                .containsExactly(61, 49, 30, 48, 16);
    }

    @Test
    void shouldStartTheGameWithNumberGreaterThanZero() {
        List<Integer> startingNumbers = IntStream.range(0, 1000)
                .boxed()
                .map(x -> player.startGameWithRandomNumber(1))
                .map(Player.FirstMoveResult::getStartingNumber)
                .collect(Collectors.toList());

        assertThat(startingNumbers).allMatch(number -> number > 0);
    }

    @Test
    void shouldStartTheGameWithRandomNumberUpperBoundByParameter() {
        int upperBound = 5;
        List<Integer> startingNumbers = IntStream.range(0, 1000)
                .boxed()
                .map(x -> player.startGameWithRandomNumber(upperBound))
                .map(Player.FirstMoveResult::getStartingNumber)
                .collect(Collectors.toList());

        assertThat(startingNumbers).allMatch(number -> number <= 5);
    }

    @Test
    void shouldRespondToMoveByAddingOne() {
        assertThat(getMoveResultNumber(8) * 3).isEqualTo(9);
        assertThat(getMoveResultNumber(14) * 3).isEqualTo(15);
        assertThat(getMoveResultNumber(83) * 3).isEqualTo(84);
        assertThat(getMoveResultNumber(84935) * 3).isEqualTo(84936);
    }


    @Test
    void shouldRespondToMoveBySubtractingOne() {
        assertThat(getMoveResultNumber(10) * 3).isEqualTo(9);
        assertThat(getMoveResultNumber(16) * 3).isEqualTo(15);
        assertThat(getMoveResultNumber(85) * 3).isEqualTo(84);
        assertThat(getMoveResultNumber(84937) * 3).isEqualTo(84936);
    }

    @Test
    void shouldRespondToMoveByAddingNothing() {
        assertThat(getMoveResultNumber(9) * 3).isEqualTo(9);
        assertThat(getMoveResultNumber(15) * 3).isEqualTo(15);
        assertThat(getMoveResultNumber(84) * 3).isEqualTo(84);
        assertThat(getMoveResultNumber(84936) * 3).isEqualTo(84936);
    }

    @Test
    void shouldRespondToWithDifferenceAdded() {
        assertThat(getMoveResultAddedNumber(9)).isEqualTo(0);
        assertThat(getMoveResultAddedNumber(14)).isEqualTo(1);
        assertThat(getMoveResultAddedNumber(31)).isEqualTo(-1);
    }

    @Test
    void shouldWinWhenReachingNumberOne() {
        int moveFromOtherPlayer = 4;
        assertThat(player.makeMoveRespondingTo(moveFromOtherPlayer).getResultingNumber()).isEqualTo(1);
        assertThat(player.makeMoveRespondingTo(moveFromOtherPlayer).hasWon()).isTrue();
    }

    @Test
    void shouldNotWinWhenRespondingWithNumberOtherThanOne() {
        int moveFromOtherPlayer = 5;
        assertThat(player.makeMoveRespondingTo(moveFromOtherPlayer).getResultingNumber()).isEqualTo(2);
        assertThat(player.makeMoveRespondingTo(moveFromOtherPlayer).hasWon()).isFalse();
    }

    @Test
    void shouldWinImmediatelyIfGameStartsWithNumberOne() {
        assertThat(player.startGameWithRandomNumber(1).hasWon()).isTrue();
    }

    @Test
    void shouldNotWinImmediatlyWhenStartingWithNumberOtherThanOne() {
        List<Player.FirstMoveResult> firstMoveResults = IntStream.range(0, 1000)
                .boxed()
                .map(x -> player.startGameWithRandomNumber(2))
                .collect(Collectors.toList());

        assertThat(firstMoveResults).anyMatch(firstMove -> !firstMove.hasWon());
    }

    private int getMoveResultNumber(int moveFromOtherPlayer) {
        return player.makeMoveRespondingTo(moveFromOtherPlayer).getResultingNumber();
    }

    private int getMoveResultAddedNumber(int moveFromOtherPlayer) {
        return player.makeMoveRespondingTo(moveFromOtherPlayer).getAddedNumber();
    }
}
