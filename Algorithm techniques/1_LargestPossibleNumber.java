//We are given N digits. Write an algorithm that composes the largest possible number from those digits.


import java.util.Arrays;
import java.util.Scanner;


public class Main{
    public static void LargestPossibleNumber(int arr[]){
        String[] str = new String[arr.length];


        for(int i = 0; i < arr.length; i++){
            str[i] = String.valueOf(arr[i]);
        }

        Arrays.sort(str, (a,b) -> (b + a).compareTo(a + b)); //2 + 3 = 23 vs 3 + 2 = 32

        StringBuilder strb = new StringBuilder();

        for(int i = 0; i < str.length; i++){
            strb.append(str[i]);
        }

        System.out.println(strb.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }


        LargestPossibleNumber(arr);
    }
}
