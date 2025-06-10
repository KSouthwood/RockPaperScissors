import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class Game {
    private final ConsoleIO console;
    private final Random    random  = new Random();
    private final Choice[]  choices = Choice.values();
    private final ScoreFile scoreFile = new ScoreFile();
    private HashMap<String, Long> players;
    private String player;

    public Game(ConsoleIO console) {
        this.console = console;
    }

    void gameStart() {
        console.print("Enter your name: ");
        player = console.getUserInput();
        console.println("Hello, " + player);
        players = scoreFile.readFile();
        players.putIfAbsent(player, 0L); // add player to HashMap if they're not there
        gameLoop();
    }

    void gameLoop() {
        String userChoice = "";

        while (!userChoice.equals("!exit")) {
            userChoice = console.getUserInput()
                                .toLowerCase(Locale.ROOT);
            switch(userChoice) {
                case "!rating" -> console.println("Your rating: " + players.get(player));
                case "!exit" -> console.println("Bye!");
                default -> playRound(userChoice);
            }
        }
    }

    void playRound(final String userChoice) {
        Choice user = getUserChoice(userChoice.toUpperCase(Locale.ROOT));
        if (user == null) {
            return;
        }
        Choice computer = getComputerChoice();
        Result result = Result.determineResult(user, computer);
        console.println(result.message(computer.choice()));
        players.put(player, result.function().apply(players.get(player)));
    }

    Choice getUserChoice(final String userChoice) {
        Choice choice = null;
        try {
            choice = Choice.valueOf(userChoice);
        } catch (IllegalArgumentException e) {
            console.println("Invalid input.");
        }
        return choice;
    }

    Choice getComputerChoice() {
        return choices[random.nextInt(Choice.values().length)];
    }

}
