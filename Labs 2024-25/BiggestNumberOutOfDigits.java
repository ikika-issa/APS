//We are given N digits. Write an algorithm that composes the largest possible number from those digits.

import java.util.Arrays;
import java.util.Scanner;

public class BiggestNumberOutOfDigits {

    public static void MaxOfDigits(int n[]){
        String [] str = new String[n.length];

        for(int i=0; i<n.length; i++){
            str[i] = String.valueOf(n[i]);
        }

        Arrays.sort(str, (a,b)->(b + a).compareTo(a + b));

        if(str[0].equals("0")){
            System.out.println(str[0]);
            return;
        }

        StringBuilder max =  new StringBuilder();
        for(String s : str){
            max.append(s);
        }

        System.out.println(max.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int arr[] = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        MaxOfDigits(arr);
    }
}
