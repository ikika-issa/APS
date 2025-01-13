//Given N words (N>=2), find the number of pairs of words that begin with the same letter.

import java.util.Scanner;

public class Pairs{
    public static void sumPairs(String arr[]){
        int sum = 0;

        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[i].charAt(0) == arr[j].charAt(0)){
                    sum++;
                }
            }
        }
        
        System.out.println(sum);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String [] arr = new String[n];
        
        for(int i=0; i<n; i++){
            arr[i] = sc.next();
        }

        sumPairs(arr);
    }
}
