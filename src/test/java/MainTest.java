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
        String loseMsg  = "Sorry, but the computer chose %s.%n";
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
    @StdIo({"Rock", "", "rock", "!exit"})
    void testUserChoiceRock(StdOut out) {
        Main.main(new String[]{});
        String[] response = out.capturedLines();
        assertEquals(4, response.length);
        assertTrue(gotCorrectResponse(response[2], "rock", "scissors", "paper"));
        assertEquals("Bye!", response[3]);
    }

    @RepeatedTest(25)
    @StdIo({"Paper", "", "paper", "!exit"})
    void testUserChoicePaper(StdOut out) {
        Main.main(new String[]{});
        String[] response = out.capturedLines();
        assertEquals(4, response.length);
        assertTrue(gotCorrectResponse(response[2], "paper", "rock", "scissors"));
        assertEquals("Bye!", response[3]);
    }

    @RepeatedTest(25)
    @StdIo({"Scissors", "", "scissors", "!exit"})
    void testUserChoiceScissors(StdOut out) {
        Main.main(new String[]{});
        String[] response = out.capturedLines();
        assertEquals(4, response.length);
        assertTrue(gotCorrectResponse(response[2], "scissors", "paper", "rock"));
        assertEquals("Bye!", response[3]);
    }

    private boolean gotCorrectResponse(String response, String draw, String win, String lose) {
        return (response.contains("draw") && response.contains(draw)) ||
                (response.contains("Well done") && response.contains(win)) ||
                (response.contains("Sorry,") && response.contains(lose));
    }

    @Test
    @StdIo({"Invalid", "", "vellum", "pliers", "granite", "!exit"})
    void testInvalidChoices(StdOut out) {
        Main.main(new String[]{});
        String[] response = out.capturedLines();
        assertEquals(6, response.length);
        assertEquals("Invalid input.", response[2]);
        assertEquals("Invalid input.", response[3]);
        assertEquals("Invalid input.", response[4]);
        assertEquals("Bye!", response[5]);
    }

    @Test
    @StdIo({"Player", "", "!rating", "!exit"})
    void testRatingWithNoPlays(StdOut out) {
        Main.main(new String[]{});
        String[] output = out.capturedLines();
        assertEquals(4, output.length);
        assertEquals("Enter your name: Hello, Player", output[0]);
        assertEquals("Okay, let's start", output[1]);
        assertEquals("Your rating: 0", output[2]);
        assertEquals("Bye!", output[3]);
    }

    @Test
    @StdIo({"Sam", "", "!rating", "rock", "paper", "scissors", "!rating", "!exit"})
    void testRatingAfterThreeRounds(StdOut out) {
        Main.main(new String[]{});
        String[] output = out.capturedLines();
        int score = calculateScore(output);
        assertEquals(8, output.length);
        assertTrue(output[0].contains("Sam"));
        assertTrue(output[2].contains("0"));
        assertTrue(output[6].contains(String.valueOf(score)));
        assertEquals("Bye!", output[7]);
    }

    int calculateScore(final String[] output) {
        int score = 0;
        for (String line : output) {
            if (line.contains("Well done")) { score += 100; }
            if (line.contains("draw")) { score += 50; }
        }
        return score;
    }
}
