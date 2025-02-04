//За дадена низа од N (1<=N<=50) природни броеви да се избришат дупликат вредностите кои се jавуваат на соседни позии. Односно доколку две и повеке вредности се една до друга во низата, да се остави само едно поjавување.
//На пример низата 1, 2, 2, 2, 3, 2, 4, 4, 1 по обработката ´ке биде 1, 2, 3, 2, 4, 1.
//Првиот броj од влезот е броjот на елементи во низата N, а потоа во секоj ред
//се дадени броевите.

import java.util.Scanner;
import java.util.*;

public class RemoveDuplicates{
    public static int[] removeDuplicates(int[] arr) {
        List<Integer> result = new ArrayList<>();
        result.add(arr[0]);

        for(int i = 1; i < arr.length; i++){
            if(arr[i] != arr[i-1]){
                result.add(arr[i]);
            }
        }

        return result.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int [] arr = new int[N];

        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }

        System.out.print(Arrays.toString(removeDuplicates(arr)));
    }
}
