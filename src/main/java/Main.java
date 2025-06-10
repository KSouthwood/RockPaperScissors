public class Main {

    public static void main(String[] args) {
        ConsoleIO console = new ConsoleIO(System.in, System.out);
        Game game = new Game(console);
        game.gameStart();
    }
}
