import java.util.HashSet;

public class Choices {
    private final String          name;
    private final HashSet<String> loses;

    public Choices(int number, String[] choices) {
        this.name = choices[number];
        loses = new HashSet<>();
        int size = (choices.length - 1) / 2;
        for (int x = 1; x <= size; x++) {
            int index = (number + x) % choices.length;
            loses.add(choices[index]);
        }
    }

    public String name() {
        return name;
    }

    public HashSet<String> loses() {
        return loses;
    }
}
