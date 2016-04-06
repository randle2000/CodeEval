import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        Stream<String> lines = Files.lines(new File(args[0]).toPath());
        lines.filter(line -> line.length()!=0)
                .map(line -> {
                    String line2 = line.replaceAll("[^a-j0-9]", "");
                    if (line2.length() != 0) {
                        Pattern p = Pattern.compile("[a-j]");
                        Matcher m = p.matcher(line2);
                        StringBuffer sb = new StringBuffer();
                        while (m.find())
                            m.appendReplacement(sb, String.valueOf((char)(m.group().charAt(0)-49)));
                        m.appendTail(sb);
                        return sb.toString();
                    } else
                        return "NONE";
                })
                .forEach(System.out::println);
    }

}
