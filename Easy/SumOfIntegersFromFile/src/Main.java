import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        Stream<String> lines = Files.lines(new File(args[0]).toPath());
        int sum = lines
                .filter(line -> line.length()!=0)
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println(sum);
    }
}
