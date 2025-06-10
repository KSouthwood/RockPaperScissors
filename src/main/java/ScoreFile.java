import java.io.*;
import java.util.HashMap;

public class ScoreFile {
    private static final String filename = "rating.txt";

    public ScoreFile() {
    }

    HashMap<String, Long> readFile() {
        HashMap<String, Long> players = new HashMap<>();
        try (BufferedReader file = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(" ");
                players.put(parts[0], Long.parseLong(parts[1]));
            }
        } catch (IOException ignored) {
            System.err.println("File not found.");
        }
        return players;
    }

    void writeFile(final HashMap<String, Long> players) {
        try (BufferedWriter file = new BufferedWriter(new FileWriter(filename))) {
            for (String name : players.keySet()) {
                String entry = name + players.get(name)
                                             .toString() + "\n";
                file.write(entry);
            }
        } catch (IOException ignored) {

        }
    }
}
