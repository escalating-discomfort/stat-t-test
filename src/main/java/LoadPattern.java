import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * The LoadPattern class takes a simple text file containing values delimited by either commas, semicolons, or spaces.
 * Any amount of newlines may be used to structure the file, it will still be parsed properly.
 */
public class LoadPattern {
    private final List<Double> values = new ArrayList<>();

    public LoadPattern(String fileName) {
        Path path = Paths.get(fileName);

        try (Stream<String> fileStream = Files.lines(path, StandardCharsets.UTF_8)) {
            fileStream.forEach(line -> Arrays.stream(line.split("[,; ]"))
                    .map(Double::valueOf)
                    .forEach(values::add));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Double> getValues() {
        return values;
    }
}
