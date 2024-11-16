//Find the longest decreasing sequence in an array. 
//The numbers in the sequence don't need to be on consecutive indices in the array.


import java.util.Scanner;


public class LDS {
    private static int najdolgaOpagackaSekvenca(int[] a) {
        int[] dp = new int[a.length];
        int maxLength = 1;
        
        for (int i = 0; i < a.length; i++) {
            dp[i] = 1; //assumes that in the there will be at least one element
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
