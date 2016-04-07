import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File(args[0]).toPath(), Charset.defaultCharset() );
        for (String ln : lines) {
            if (ln.length() == 0) continue;
            List<String> strItems = Arrays.asList(ln.split("\\s+"));
            Collections.reverse(strItems);
            String output = strItems.stream().collect(Collectors.joining(" "));
            System.out.println(output);
        }
    }
}
