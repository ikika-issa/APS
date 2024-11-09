//For a given array with N integers, all the elements that are lower than the average of the whole array need to be deleted. 
//For example for the array 1, 2, 3, 4, 5 the average is (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3 which means that the elements 1 and 2 should be deleted, 
//so the array after the transformation will be 3, 4, 5.

import java.util.Scanner;


public class Main {
    public static void moreThanAverage(int a[], int n){
        int aa[] = new int[n];

        float avg = 0;

        for(int i=0; i<n; i++){
            avg += (float)a[i];
        }

        avg /= n;

        int j = 0;
        for(int i=0; i<n; i++){
            if(avg <= (float)a[i]){
                aa[j++] = a[i];
            }
        }

        System.out.print("{");
        for(int i=0; i<j; i++){
            if(i != j-1){
                System.out.print(aa[i] + ",");
            }
            else{
                System.out.print(aa[i]);
            }
        }
        System.out.println("}\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int numbers[] = new int[n];

        for(int i = 0; i < n; i++){
            numbers[i] = sc.nextInt();
        }

        System.out.print("{");
        for(int i = 0; i < n; i++){
            if(i != n-1){
                System.out.print(numbers[i] + ",");
            }
            else{
                System.out.print(numbers[i]);
            }
        }
        System.out.println("}");
        moreThanAverage(numbers, n);
    }
}
