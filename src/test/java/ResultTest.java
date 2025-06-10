import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultTest {

    @ParameterizedTest
    @MethodSource
    void testDetermineResult(Choice user, Choice computer, Result expected) {
        Result actual = Result.determineResult(user, computer);
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> testDetermineResult() {
        return Stream.of(
                Arguments.of(Choice.ROCK, Choice.ROCK, Result.DRAW),
                Arguments.of(Choice.ROCK, Choice.PAPER, Result.LOSE),
                Arguments.of(Choice.ROCK, Choice.SCISSORS, Result.WIN),
                Arguments.of(Choice.PAPER, Choice.ROCK, Result.WIN),
                Arguments.of(Choice.PAPER, Choice.PAPER, Result.DRAW),
                Arguments.of(Choice.PAPER, Choice.SCISSORS, Result.LOSE),
                Arguments.of(Choice.SCISSORS, Choice.ROCK, Result.LOSE),
                Arguments.of(Choice.SCISSORS, Choice.PAPER, Result.WIN),
                Arguments.of(Choice.SCISSORS, Choice.SCISSORS, Result.DRAW)
        );
    }
}
