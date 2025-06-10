import java.util.Locale;

public enum Choice {
    ROCK("rock", "scissors", "paper"),
    PAPER("paper", "rock", "scissors"),
    SCISSORS("scissors", "paper", "rock");

    private final String choice;
    private final String beats;
    private final String losesTo;

    Choice(final String choice, final String beats, final String loses) {
        this.choice = choice;
        this.beats = beats;
        this.losesTo = loses;
    }

    public String choice() {
        return choice;
    }

    public String beats() {
        return beats;
    }

    public String losesTo() {
        return losesTo;
    }

    Choice getChoice(String name) throws IllegalArgumentException {
        return Choice.valueOf(name.toUpperCase(Locale.ROOT));
    }
}
