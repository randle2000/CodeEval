import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> inLines = Files.readAllLines(new File(args[0]).toPath());  // Read input file
        List<String> outLines = processLines(inLines);                          // Process lines
        printOutput(outLines);                                                  // Print lines
    }

    private static void printOutput(List<String> lines) {
        lines.stream().forEach(System.out::println);
    }

    private static List<String> processLines(List<String> lines) {
        List<String> outLines = new ArrayList<>();
        for (String ln : lines) {
            long originalNum = Integer.parseInt(ln);
            int count = 0;
            long reversedNum = reverseNumber(originalNum);
            while (originalNum != reversedNum) {        // repeat until reversed number is not a palindrome
                originalNum += reversedNum;
                count++;
                reversedNum = reverseNumber(originalNum);
            } ;
            outLines.add(count + " " + originalNum);
        }
        return outLines;
    }

    private static long reverseNumber(long inNum) {       // returns a long with its digits reversed
        long reversedNum = 0;
        while (inNum != 0) {
            reversedNum = reversedNum * 10 + inNum % 10;
            inNum = inNum / 10;
        }
        /*if (reversedNum > Integer.MAX_VALUE || reversedNum < Integer.MIN_VALUE)
            throw new IllegalArgumentException();*/
        return reversedNum;
    }
}

