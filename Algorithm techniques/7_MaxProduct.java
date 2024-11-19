//For a given array of integers, find the maximum product formed by multiplication of the numbers of an increasing subarray of the given array.


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MaxProduct {

    public static void main(String[] args) throws Exception{
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        int arr[] = new int[N];
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            arr[i]=Integer.parseInt(pomniza[i]);
        }

        int[] dp = new int[N];
        System.arraycopy(arr, 0, dp, 0, N);

        int maxProduct = arr[0];

        // Fill the dp array
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] * arr[i]);
                }
            }
            maxProduct = Math.max(maxProduct, dp[i]);
        }

        System.out.println(maxProduct);
    }

}
