import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File(args[0]).toPath());
        for (String ln : lines) {
            if (ln.isEmpty()) continue;
            String[] strArr = ln.split(",\\s*");
            String output = strArr[0].replaceAll("[" + strArr[1] + "]", "");
            output = output.replaceAll("\\s+$", "");        // remove trailing spaces, if any
            System.out.println(output);
        }
    }
}
