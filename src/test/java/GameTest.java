import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(new ConsoleIO(System.in, System.out));
    }

    @ParameterizedTest
    @MethodSource
    @StdIo
    void testValidChoices(String choice, String expectedLoseTo, StdOut out) {
        String expected = String.format(game.loseMsg, expectedLoseTo);
        String actual = game.checkForWin(choice);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> testValidChoices() {
        return Stream.of(
                Arguments.of("rock", "paper"),
                Arguments.of("paper", "scissors"),
                Arguments.of("scissors", "rock")
        );
    }

    @ParameterizedTest
    @MethodSource
    @StdIo
    void testInvalidChoices(String choice, String expected, StdOut out) {
        String actual = game.checkForWin(choice);
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> testInvalidChoices() {
        return Stream.of(
                Arguments.of("vellum", "Unknown choice. Try again."),
                Arguments.of("granite", "Unknown choice. Try again."),
                Arguments.of("pliers", "Unknown choice. Try again.")
        );
    }
}
