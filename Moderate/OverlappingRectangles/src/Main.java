import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
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
            int[] coords = Arrays.asList(ln.split(",")).stream().mapToInt(Integer::parseInt).toArray();
            Rectangle rect1 = new Rectangle(coords[0], coords[1], coords[2], coords[3]);
            Rectangle rect2 = new Rectangle(coords[4], coords[5], coords[6], coords[7]);
            if (rect1.overlaps(rect2))
                outLines.add("True");
            else
                outLines.add("False");
        }
        return outLines;
    }

    private static class Rectangle {
        private int upperLeftX, upperLeftY, lowerRightX, lowerRightY;

        public Rectangle(int upperLeftX, int upperLeftY, int lowerRightX, int lowerRightY) {
            this.upperLeftX = upperLeftX;
            this.upperLeftY = upperLeftY;
            this.lowerRightX = lowerRightX;
            this.lowerRightY = lowerRightY;
        }
        public boolean overlaps(Rectangle rect) {
            return (xOverlap(rect) || rect.xOverlap(this)) && (yOverlap(rect) || rect.yOverlap(this));
        }
        private boolean xOverlap(Rectangle rect) {
            return inRange(upperLeftX, rect.upperLeftX, rect.lowerRightX) || inRange(lowerRightX, rect.upperLeftX, rect.lowerRightX);
        }
        private boolean yOverlap(Rectangle rect) {
            return inRange(lowerRightY, rect.lowerRightY, rect.upperLeftY) || inRange(upperLeftY, rect.lowerRightY, rect.upperLeftY);
        }
        private boolean inRange(int x, int loBound, int hiBound) {  // inclusive
            return x >= loBound && x <= hiBound;
        }
    }
}
