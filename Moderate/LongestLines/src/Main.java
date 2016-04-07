import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File(args[0]).toPath());
        int printCount = Integer.parseInt(lines.remove(0));
        Collections.sort(lines, (String s1, String s2) -> s2.length() - s1.length() );
        for (int i = 0; i < printCount; i++)
            System.out.println(lines.get(i));
    }
}
