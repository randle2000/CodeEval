import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<Section> track = new ArrayList<>();
        List<Car> carList = new ArrayList<>();
        String trackLine = lines.remove(0);
        Matcher m = Pattern.compile("(\\d+\\.\\d+) (\\d+\\.?\\d*)").matcher(trackLine);
        while (m.find())
            track.add(new Section(Double.parseDouble(m.group(1)), Double.parseDouble(m.group(2))));
        for (String ln : lines) {
            // each line will contain car info: id topSpeed accelTime breakTime
            String[] items = ln.split(" ");
            carList.add(new Car(Integer.parseInt(items[0]), Integer.parseInt(items[1]), Double.parseDouble(items[2]), Double.parseDouble(items[3])));
        }
        for (Car car : carList)
            for (Section section : track)
                car.travelOneSection(section.sectionLength, section.turnAngle);
        Collections.sort(carList);
        List<String> outLines = new ArrayList<>();
        for (Car car : carList)
            outLines.add(car.id + " " + new DecimalFormat("0.00").format(Math.round(car.getTotalTime() * 100.0) / 100.0));
        return outLines;
    }

    private static class Car implements Comparable<Car> {
        private final int id;
        private final double topSpeed, accelTime, breakTime;      // topSpeed is in miles per second
        private double currentSpeed = 0, totalTime = 0;

        // accelTime and breakTime are given in Seconds; topSpeed is given in MPH while it is stored in miles per second
        public Car(int id, int topSpeed, double accelTime, double breakTime) {
            this.id = id;
            this.topSpeed = (double)(topSpeed / 3600d);
            this.accelTime = accelTime;
            this.breakTime = breakTime;
        }

        // Returns speed that the car going at its top speed should slow down to before safely passing a turn with specified angle
        public double getTurnSpeed(double turnAngle) {
            return topSpeed * (1 - turnAngle / 180d);
        }

        // Returns time that it will take for the car to travel sectionLength starting with startSpeed and ending with endSpeed
        public double getSectionTime(double sectionLength, double startSpeed, double endSpeed) {
            //     A		     B            C
            // |-------|-------------------|-----|
            // A - accelerating
            // B - going at top speed
            // C - slowing down
            double lengthOfB = sectionLength - getAccelDistance(startSpeed, topSpeed) - getBreakDistance(topSpeed, endSpeed);
            double timeToTravelB = lengthOfB / topSpeed;
            double totalTime = getAccelTime(startSpeed, topSpeed) + timeToTravelB + getBreakTime(topSpeed, endSpeed);
            return totalTime;
        }

        // Returns time that it will take for the car to accelerate from startSpeed to endSpeed
        private double getAccelTime(double startSpeed, double endSpeed) {
            double avgSpeed = (startSpeed + endSpeed) / 2;
            double time = (endSpeed - startSpeed) * accelTime / topSpeed;
            return time;
        }

        // Returns time that it will take for the car to break from startSpeed to endSpeed
        private double getBreakTime(double startSpeed, double endSpeed) {
            double avgSpeed = (startSpeed + endSpeed) / 2;
            double time = (startSpeed - endSpeed) * breakTime / topSpeed;
            return time;
        }

        // Returns distance that the car will travel accelerating from startSpeed to endSpeed
        private double getAccelDistance(double startSpeed, double endSpeed) {
            double avgSpeed = (startSpeed + endSpeed) / 2;
            return avgSpeed * getAccelTime(startSpeed, endSpeed);
        }

        // Returns distance that the car will travel breaking from startSpeed to endSpeed
        private double getBreakDistance(double startSpeed, double endSpeed) {
            double avgSpeed = (startSpeed + endSpeed) / 2;
            return avgSpeed * getBreakTime(startSpeed, endSpeed);
        }

        public int getId() { return id; }

        public double getTotalTime() { return totalTime; }

        // Makes the car go 1 section. This will change currentSpeed and totalTime
        public void travelOneSection(double sectionLength, double turnAngle) {
            double endSpeed = getTurnSpeed(turnAngle);
            totalTime += getSectionTime(sectionLength, currentSpeed, endSpeed);
            currentSpeed = endSpeed;
        }

        public int compareTo(Car otherCar) {
            return Double.compare(totalTime, otherCar.totalTime);
        }
    }

    private static class Section {
        public final double sectionLength;
        public final double turnAngle;
        public Section(double sectionLength, double turnAngle) {
            this.sectionLength = sectionLength;
            this.turnAngle = turnAngle;
        }
    }
}

