import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChoicesTest {

    @ParameterizedTest
    @MethodSource
    void name(int number, String expected, String[] options) {
        Choices actual = new Choices(number, options);
        assertEquals(expected, actual.name());
    }

    static Stream<Arguments> name() {
        return Stream.of(
                Arguments.of(0, "rock", new String[]{"rock", "paper", "scissors" }),
                Arguments.of(1, "paper", new String[]{"rock", "paper", "scissors" }),
                Arguments.of(2, "scissors", new String[]{"rock", "paper", "scissors" })
        );
    }

    @ParameterizedTest
    @MethodSource
    void loses(int number, String[] options, HashSet<String> expected) {
        Choices actual = new Choices(number, options);
        assertEquals(expected, actual.loses());
    }

    static Stream<Arguments> loses() {
        return Stream.of(
                Arguments.of(0,
                             new String[]{"rock", "paper", "scissors" },
                             new HashSet<>(Set.of("paper"))),
                Arguments.of(1,
                             new String[]{"rock", "paper", "scissors" },
                             new HashSet<>(Set.of("scissors"))),
                Arguments.of(2,
                             new String[]{"rock", "paper", "scissors" },
                             new HashSet<>(Set.of("rock"))),
                Arguments.of(2,
                             new String[]{"rock", "paper", "scissors", "lizard", "spock" },
                             new HashSet<>(Set.of("lizard", "spock"))),
                Arguments.of(4,
                             new String[]{"rock", "paper", "scissors", "lizard", "spock" },
                             new HashSet<>(Set.of("rock", "paper"))),
                Arguments.of(4,
                             new String[]{"rock", "fire", "scissors", "sponge", "paper", "air", "water" },
                             new HashSet<>(Set.of("air", "water", "rock"))),
                Arguments.of(5,
                             new String[]{"rock", "fire", "scissors", "sponge", "paper", "air", "water" },
                             new HashSet<>(Set.of("water", "rock", "fire")))
        );
    }
}
