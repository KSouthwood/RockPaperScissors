import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junitpioneer.jupiter.StdIn;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;
import org.junitpioneer.jupiter.WritesStdIo;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    private final String loseMsg  = "Sorry, but the computer chose %s.%n";

    @Disabled("Just testing StdIn & StdOut capture.")
    @Test
    @StdIo("rock")
    void testMain(StdIn in, StdOut out) {
        Main.main(new String[]{});
        System.err.println("StdIn captured:");
        System.err.println(Arrays.toString(in.capturedLines()));
        System.err.println("StdOut captured:");
        System.err.println(Arrays.toString(out.capturedLines()));
    }

    @Disabled("Not needed beyond stage 1")
    @ParameterizedTest
    @MethodSource
    @WritesStdIo
    @StdIo
    void testStage1Run(String input, String output, StdOut out) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        Main.main(new String[]{});
        String expected = String.format(loseMsg, output);
        String actual   = out.capturedString();
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> testStage1Run() {
        return Stream.of(
                Arguments.of("rock", "paper"),
                Arguments.of("paper", "scissors"),
                Arguments.of("scissors", "rock")
        );
    }

    @RepeatedTest(25)
    @StdIo("rock")
    void testUserChoiceRock(StdOut out) {
        Main.main(new String[]{});
        String response = out.capturedString();
        assertTrue(gotCorrectResponse(response, "rock", "scissors", "paper"));
    }

    @RepeatedTest(25)
    @StdIo("paper")
    void testUserChoicePaper(StdOut out) {
        Main.main(new String[]{});
        String response = out.capturedString();
        assertTrue(gotCorrectResponse(response, "paper", "rock", "scissors"));
    }

    @RepeatedTest(25)
    @StdIo("scissors")
    void testUserChoiceScissors(StdOut out) {
        Main.main(new String[]{});
        String response = out.capturedString();
        assertTrue(gotCorrectResponse(response, "scissors", "paper", "rock"));
    }

    private boolean gotCorrectResponse(String response, String draw, String win, String lose) {
        return (response.contains("draw") && response.contains(draw)) ||
                (response.contains("Well done") && response.contains(win)) ||
                (response.contains("Sorry,") && response.contains(lose));
    }
}
