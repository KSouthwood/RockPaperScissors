import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultTest {

    @Test
    void testDefaultOptions() {
        var choicesMap = buildMapOfChoices("rock,paper,scissors");
        var user = choicesMap.get("rock");
        assertEquals(Result.WIN, Result.determineResult(user, choicesMap.get("scissors")));
        assertEquals(Result.DRAW, Result.determineResult(user, choicesMap.get("rock")));
        assertEquals(Result.LOSE, Result.determineResult(user, choicesMap.get("paper")));
    }

    @Test
    void testFiveOptions() {
        var choicesMap = buildMapOfChoices("rock,paper,scissors,lizard,spock");
        var user = choicesMap.get("scissors");
        assertEquals(Result.DRAW, Result.determineResult(user, choicesMap.get("scissors")));
        assertEquals(Result.WIN, Result.determineResult(user, choicesMap.get("rock")));
        assertEquals(Result.WIN, Result.determineResult(user, choicesMap.get("paper")));
        assertEquals(Result.LOSE, Result.determineResult(user, choicesMap.get("lizard")));
        assertEquals(Result.LOSE, Result.determineResult(user, choicesMap.get("spock")));
    }

    private LinkedHashMap<String, Choices> buildMapOfChoices(final String choices) {
        var options = choices.split(",");
        var choicesMap = new LinkedHashMap<String, Choices>();

        for (int index = 0; index < options.length; index++) {
            choicesMap.put(options[index], new Choices(index, options));
        }

        return choicesMap;
    }
}
