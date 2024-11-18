//We are given N trains. Write an algorithm that finds the minimum number of platforms needed to schedule those trains.

//Input: The first number in the input is the number of trains N, then in the next N lines are the arrival and departure minutes for each of the trains.

//Output: The minimum number of needed platforms to accommodate the trains.


import java.util.Arrays;
import java.util.Scanner;

public class TrainPlatforms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[] arrivals = new int[N];
        int[] departures = new int[N];


        for (int i = 0; i < N; i++) {
            arrivals[i] = scanner.nextInt();
            departures[i] = scanner.nextInt();
        }

        int platformsNeeded = MinimumPlatforms(arrivals, departures);
        System.out.println(platformsNeeded);
    }

    public static int MinimumPlatforms(int[] arrivals, int[] departures) {
        Arrays.sort(arrivals);
        Arrays.sort(departures);

        int n = arrivals.length;
        int platformCount = 0;
        int maxPlatforms = 0;

        int i = 0, j = 0;


        while (i < n && j < n) {
            if (arrivals[i] <= departures[j]) {
                platformCount++;
                
                maxPlatforms = Math.max(maxPlatforms, platformCount);
                i++;
            } else {
                platformCount--;
                j++;
            }
        }

        return maxPlatforms;
    }
}
