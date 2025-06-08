import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
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
    void testDetermineWinner(Choice user, Choice computer, String winType) {
        String expected = switch (winType) {
            case "win" -> String.format(game.winMsg, computer.getChoice());
            case "draw" -> String.format(game.drawMsg, computer.getChoice());
            case "lose" -> String.format(game.loseMsg, computer.getChoice());
            default -> "";
        };

        String actual = game.determineWinner(user, computer);
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> testDetermineWinner() {
        return Stream.of(
                Arguments.of(Choice.ROCK, Choice.ROCK, "draw"),
                Arguments.of(Choice.ROCK, Choice.PAPER, "lose"),
                Arguments.of(Choice.ROCK, Choice.SCISSORS, "win"),
                Arguments.of(Choice.PAPER, Choice.ROCK, "win"),
                Arguments.of(Choice.PAPER, Choice.PAPER, "draw"),
                Arguments.of(Choice.PAPER, Choice.SCISSORS, "lose"),
                Arguments.of(Choice.SCISSORS, Choice.ROCK, "lose"),
                Arguments.of(Choice.SCISSORS, Choice.PAPER, "win"),
                Arguments.of(Choice.SCISSORS, Choice.SCISSORS, "draw")
        );
    }

    @ParameterizedTest
    @MethodSource
    void testGetUserChoice(String input, Choice expected) {
        Choice actual = game.getUserChoice(input.toUpperCase(Locale.ROOT));
        assertEquals(expected, actual);
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
