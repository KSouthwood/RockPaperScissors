import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(new ConsoleIO(System.in, System.out));
    }

    @Disabled("No longer of use")
    @ParameterizedTest
    @MethodSource
    void testGetUserChoice(String input, Choice expected) {
//        Choice actual = null;
//        assertEquals(expected, actual);
    }

    static Stream<Arguments> testGetUserChoice() {
        return Stream.of(
                Arguments.of("rock", Choice.ROCK),
                Arguments.of("RoCk", Choice.ROCK),
                Arguments.of("paper", Choice.PAPER),
                Arguments.of("PaPEr", Choice.PAPER),
                Arguments.of("scissors", Choice.SCISSORS),
                Arguments.of("SCisSOrs", Choice.SCISSORS),
                Arguments.of("stone", null),
                Arguments.of("veLlUm", null),
                Arguments.of("PlieRs", null)
        );
    }
}
