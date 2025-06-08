import java.util.Locale;
import java.util.Random;

public class Game {
    private final ConsoleIO console;
    private final Random random = new Random();
    private final Choice[] choices = Choice.values();

    String winMsg = "Well done. The computer chose %s and failed";
    String drawMsg = "There is a draw (%s).";
    String loseMsg = "Sorry, but the computer chose %s.";

    public Game(ConsoleIO console) {
        this.console = console;
    }

    void gameLoop() {
        Choice user = getUserChoice();
        Choice computer = getComputerChoice();
        console.println(determineWinner(user, computer));
    }

    Choice getUserChoice() {
        String userChoice = console.getUserInput().toUpperCase(Locale.ROOT);
        Choice choice     = null;
        try {
            choice = Choice.valueOf(userChoice);
        } catch (IllegalArgumentException e) {
            console.println("Unknown choice. Try again.");
        }
        return choice;
    }

    Choice getComputerChoice() {
        return choices[random.nextInt(Choice.values().length)];
    }

    String determineWinner(final Choice user, final Choice computer) {
        if (user.equals(computer)) {
            return String.format(drawMsg, user.getChoice());
        } else if (user.beats().equals(computer.getChoice())) {
             return String.format(winMsg, computer.getChoice());
        } else {
            return String.format(loseMsg, computer.getChoice());
        }
    }
}
