import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File(args[0]).toPath());
        for (String ln : lines) {
            String str = ln.toLowerCase().replaceAll("[^a-z]", "");     // convert line to lower case and remove all non-alphabet chars from it
            List<Character> alphabet = "abcdefghijklmnopqrstuvwxyz".chars()     // create List of all alphabet chars
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList());
            Set<Character> strSet = str.chars()                         // put all chars from line into a Set
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toSet());
            alphabet.removeAll(strSet);                                 // remove all chars that are present in the line from alphabet List
            String output = alphabet                                    // convert remaining chars in alphabet List into String
                    .stream()
                    .map(e->e.toString())
                    .collect(Collectors.joining());
            if (output.isEmpty())
                output = "NULL";
            System.out.println(output);
        }
    }
}
