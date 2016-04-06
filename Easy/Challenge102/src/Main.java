import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        final String REGEX = "\\{\"id\": (\\d+)[^}]+label[^}]+\\}";  // {"id": XXX ... label ... }  XXX will be stored in m.group(1)

        Pattern pattern = Pattern.compile(REGEX);
        ToIntFunction<String> myMapper = new ToIntFunction<String>() {  // just to practice: anonymous classes, functional interfaces, lambda, streams, collections
            public int applyAsInt(String line) {
                List<Integer> intList = new ArrayList<Integer>();
                Matcher m = pattern.matcher(line);
                while (m.find())
                    intList.add(Integer.valueOf(m.group(1)));
                return intList.stream().mapToInt(Integer::intValue).sum();
            }
        };

        Stream<String> lines = Files.lines(new File(args[0]).toPath());
        lines.filter(line -> line.length()!=0)      //filter(line -> line.matches(".*?" + REGEX + ".*?"))
                .mapToInt(myMapper)
                .forEach(System.out::println);
    }
}
