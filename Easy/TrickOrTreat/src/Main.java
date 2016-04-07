import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    //private static final String[] elems = new String[] { "vampires", "zombies", "witches", "houses" };
    private static final String HOUSES = "houses";
    private static final Map<String, Integer> REWARD_MAP;
    static {
        Map<String, Integer> aMap = new HashMap<>();
        aMap.put("vampires", 3);
        aMap.put("zombies", 4);
        aMap.put("witches", 5);
        REWARD_MAP = Collections.unmodifiableMap(aMap);
    }

    public static void main(String[] args) throws IOException {
        class Christmas {
            private int totalCandies = 0;   // No. of collected candies
            private int visitedHouses = 0;  // No. of visited houses
            private int totalKids = 0;

            public void addElem(String elem, int n) {
                if (elem.equalsIgnoreCase(HOUSES)) {
                    visitedHouses += n;
                }
                else {
                    totalKids += n;
                    totalCandies += n * REWARD_MAP.get(elem.toLowerCase());
                }
            }

            public int getAvgCandies() {    // Avg candies per kid
                return (int)(totalCandies * visitedHouses / totalKids);
            }
        }

        List<String> lines = Files.readAllLines(new File(args[0]).toPath(), Charset.defaultCharset() );
        // Sample line: Vampires: 1, Zombies: 1, Witches: 1, Houses: 10
        //final String REGEX = "(vampires.*?(\\d+))|(witches.*?(\\d+))|(houses.*?(\\d+))";
        String allElems = String.join("|", REWARD_MAP.keySet().stream().toArray(String[]::new)) + "|" + HOUSES;
        final String REGEX = "(" + allElems + ").*?(\\d+)";
        Pattern p = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        for (String ln : lines) {
            Matcher m = p.matcher(ln);
            Christmas myChristmas = new Christmas();
            while (m.find())
                myChristmas.addElem(m.group(1), Integer.parseInt(m.group(2)));
            System.out.println(myChristmas.getAvgCandies());
        }

    }
}
