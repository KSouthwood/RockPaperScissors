import java.util.*;

public class Game {
    private final ConsoleIO                      console;
    private final Random                         random    = new Random();
    private final ScoreFile                      scoreFile = new ScoreFile();

    private       LinkedHashMap<String, Choices> mapOfChoices;
    private       ArrayList<String>              listOfChoices;
    private       HashMap<String, Long>          players;
    private       String                         player;

    public Game(ConsoleIO console) {
        this.console = console;
    }

    void gameStart() {
        getPlayer();
        getOptions();
        gameLoop();
    }

    private void getPlayer() {
        console.print("Enter your name: ");
        player = console.getUserInput();
        console.println("Hello, " + player);
        players = scoreFile.readFile();
        players.putIfAbsent(player, 0L); // add player to HashMap if they're not there
    }

    private void getOptions() {
        String defaultOptions = "rock,paper,scissors";
        String userOptions    = console.getUserInput();
        if (userOptions.isEmpty()) {
            userOptions = defaultOptions;
        }
        processOptions(userOptions);
        console.println("Okay, let's start");
    }

    private void processOptions(final String options) {
        String[] optionsArray = options.split(",");
        mapOfChoices = new LinkedHashMap<>();
        for (int index = 0; index < optionsArray.length; index++) {
            mapOfChoices.put(optionsArray[index], new Choices(index, optionsArray));
        }
        listOfChoices = new ArrayList<>(mapOfChoices.keySet());
    }

    void gameLoop() {
        String userChoice = "";

        while (!userChoice.equals("!exit")) {
            userChoice = console.getUserInput()
                                .toLowerCase(Locale.ROOT);
            switch (userChoice) {
                case "!rating" -> console.println("Your rating: " + players.get(player));
                case "!exit" -> console.println("Bye!");
                default -> playRound(userChoice);
            }
        }
    }

    void playRound(final String userChoice) {
        if (!mapOfChoices.containsKey(userChoice)) {
            console.println("Invalid input.");
            return;
        }
        Choices user     = mapOfChoices.get(userChoice);
        Choices computer = mapOfChoices.get(listOfChoices.get(random.nextInt(listOfChoices.size())));
        Result  result   = Result.determineResult(user, computer);
        console.println(result.message(computer.name()));
        players.put(player, result.function()
                                  .apply(players.get(player)));
    }
}
