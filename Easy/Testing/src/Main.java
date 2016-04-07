import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File(args[0]).toPath(), Charset.defaultCharset() );
        for (String ln : lines) {
            if (ln.length() == 0) continue;
            String[] twoItems = ln.split(" \\| ");
            int bugs = 0;
            int i = 0;
            for(char c : twoItems[0].toCharArray())
                if(twoItems[1].charAt(i++) != c)
                    bugs++;
            String out;
            if (bugs == 0)
                out = "Done";
            else if (bugs <= 2)
                out = "Low";
            else if (bugs <= 4)
                out = "Medium";
            else if (bugs <= 6)
                out = "High";
            else
                out = "Critical";
            System.out.println(out);
        }
    }
}
