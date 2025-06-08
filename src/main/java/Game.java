public class Game {
    private final ConsoleIO console;

    String loseMsg = "Sorry, but the computer chose %s.";

    public Game(ConsoleIO console) {
        this.console = console;
    }

    void gameLoop() {
        String choice = console.getUserInput();
        console.println(checkForWin(choice));
    }

    String checkForWin(String choice) {
        return switch (choice) {
            case "rock" -> String.format(loseMsg, "paper");
            case "paper" -> String.format(loseMsg, "scissors");
            case "scissors" -> String.format(loseMsg, "rock");
            default -> "Unknown choice. Try again.";
        };
    }
}
