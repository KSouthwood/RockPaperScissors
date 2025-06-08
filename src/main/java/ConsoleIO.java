import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public final class ConsoleIO {
    private final PrintStream output;
    private final Scanner input;

    public ConsoleIO(InputStream input, PrintStream output) {
        Objects.requireNonNull(input, "Input stream cannot be null and should be System.in");
        Objects.requireNonNull(output, "Output stream cannot be null and should be System.out");
        this.input = new Scanner(input);
        this.output = output;
    }

    public String getUserInput() {
        return input.nextLine().trim();
    }

    public void println(String message) {
        output.println(message);
    }
}
