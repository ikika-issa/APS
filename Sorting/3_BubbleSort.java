//Given is a doubly linked list with N nodes that each contain a natural number. 
//The list should be sorted using bubble sort.


import java.io.IOException;
import java.util.Scanner;

public class BubbleSortDLL {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = sc.nextInt();
        }

        boolean isSorted = true;

        while(isSorted){
            isSorted = false;
            for(int i = 0; i < n-1; i++){
                if(array[i] > array[i+1]){
                    int tmp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = tmp;
                    isSorted = true;
                }
            }
        }

        for(int i = 0; i < n; i++){
            System.out.print(array[i] + " ");
        }
    }

}
