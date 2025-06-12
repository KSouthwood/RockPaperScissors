import java.util.function.Function;

enum Result {
    WIN("Well done. The computer chose %s and failed", x -> x + 100),
    DRAW("There is a draw (%s).", x -> x + 50),
    LOSE("Sorry, but the computer chose %s.", x -> x);

    private final String                 message;
    private final Function<Long, Long> function;

    Result(final String msg, final Function<Long, Long> func) {
        this.message = msg;
        this.function = func;
    }

    public String message(final String choice) {
        return String.format(message, choice);
    }

    public Function<Long, Long> function() {
        return function;
    }

    static Result determineResult(final Choices user, final Choices computer) {
        if (user.name().equals(computer.name())) { return DRAW; }
        if (user.loses().contains(computer.name())) { return LOSE; }
        return WIN;
    }
}
