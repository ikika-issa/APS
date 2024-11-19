//We are given a street with N possible positions on which we can put a light. 
//A single light will illuminate its own position and 2 more positions to the left and 2 more to the right of it. 
//Our task is to illuminate the street with the minimum possible lights. Not all possible positions must contain light.

//Input: The first number in the input is the number of possible positions to put a light bulb N and the length of the street M, 
//then in the next line are the possible positions on which we can put a light. 

//Output: The minimum lights we need to illuminate the street.


import java.util.*;

public class MinimumLights {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();


        int n = scanner.nextInt();

        int[] positions = new int[m];
        for (int i = 0; i < m; i++) {
            positions[i] = scanner.nextInt();
        }

        int lights = minimumLights(n, positions);
        System.out.println(lights);
    }

    public static int minimumLights(int m, int[] positions) {
        Arrays.sort(positions);

        int count = 0;
        int current = 1;

        while (current <= m) {
            int rightMost = -1;

            for (int pos : positions) {
                if (pos >= current - 2 && pos <= current + 2) {
                    rightMost = pos;
                } else if (pos > current + 2) {
                    break;
                }
            }

            if (rightMost == -1) {
                return -1; // Impossible to illuminate the street
            }

            count++;

            current = rightMost + 3;
        }

        return count;
    }
}
