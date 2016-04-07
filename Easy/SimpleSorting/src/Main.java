import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static final Comparator<Double> MY_ORDER = new Comparator<Double>() {
        public int compare(Double d1, Double d2) {
            return d1.compareTo(d2);
        }
    };

    public static void main(String[] args) throws IOException {
        // This time doing it in the Collections way, not streams
        // Just to practice collections and Comparator interface
        List<String> lines = Files.readAllLines(new File(args[0]).toPath(), Charset.defaultCharset() );
        for (String ln : lines) {
            // Arrays.asList returns a fixed-size List backed by the array. So strItems will be non-mutable
            List<String> strItems = Arrays.asList(ln.split("\\s+"));
            //List<Double> dblItems = strItems.stream().map(Double::valueOf).collect(Collectors.toList());
            List<Double> dblItems = strItems.stream().map(Double::new).collect(Collectors.toList());  // using new just to illustrate
            Collections.sort(dblItems, MY_ORDER);
            String output = dblItems.stream()
                    //.map(Object::toString)
                    .map(dbl -> new DecimalFormat("0.000").format(dbl))
                    .collect(Collectors.joining(" "));
            System.out.println(output);
        }
    }
}
