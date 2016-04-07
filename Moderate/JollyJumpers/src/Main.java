import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File(args[0]).toPath());
        for (String ln : lines) {
            int[] arr = Arrays.stream(ln.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            Set<Integer> diffsSet = new HashSet<>();
            for (int i=1; i < (arr.length-1); i++)
                diffsSet.add(Math.abs(arr[i]-arr[i+1]));
            if (isJollyJumper(diffsSet, arr[0]))
                System.out.println("Jolly");
            else
                System.out.println("Not jolly");
        }
    }

    private static boolean isJollyJumper(Set<Integer> diffsSet, int I) {
        // Number should be Jolly Jumper if in the set of its absolute differences: min=1 & max=N & sum=(N+1)*(N/2)
        int min = Collections.min(diffsSet);
        int max = Collections.max(diffsSet);
        int N = I - 1;
        int sum = diffsSet.stream().mapToInt(Integer::intValue).sum();
        return min==1 && max==N && sum==(N+1)*(N/2.0);
    }
}
