import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File(args[0]).toPath());
        for (String ln : lines) {
            if (ln.isEmpty()) continue;
            String[] strArr = ln.split(",");
            String strA = strArr[0];
            String strB = strArr[1];
            if (strA.matches(".*" + strB + "$"))
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}
