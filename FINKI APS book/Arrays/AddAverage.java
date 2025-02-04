//За дадена низа од N (1<=N<=50) природни броеви ме´гу секои два соседи да се внесе нов елемент коj е (целоброен, заокружен) просек од двата соседи.
//На пример низата 1, 3, 5, 6 по обработката ´ке биде 1, 2, 3, 4, 5, 6, 6.
//Првиот броj од влезот е броjот на елементи во низата N, а потоа во секоj ред се дадени броевите.

import java.util.Scanner;
import java.util.*;

public class AddAverages{
    public static int[] removeDuplicates(int[] arr) {
        List<Integer> result = new ArrayList<>();
        result.add(arr[0]);

        for(int i = 1; i < arr.length; i++){
            float sum = arr[i] + arr[i - 1];
            result.add(Math.round(sum/2));
            result.add(arr[i]);
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
