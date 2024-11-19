//What is the minimum number of times we need to subtract the square of an integer for a number X to become 0?


import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int X = input.nextInt();
        int result;

        int [] dyno = new int[X + 1];
        Arrays.fill(dyno, 100000000);
        dyno[0] = 0;

        for(int i = 1; i <= X; i++){
            for(int j = 1; j*j <= i; j++){
                dyno[i] = Math.min(dyno[i], dyno[i - j*j] + 1);
            }
        }
        result = dyno[X];
        
        System.out.println(result);
    }
}
