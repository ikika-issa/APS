//We are given an array of N numbers. Using the method "divide and conquer" write a binary search algorithm that searches for a given number M. 
//If the number is found, print the position of the number in the array. If the number is not in the array, print "Ne postoi".

import java.util.Scanner;


public class Main {
    public static void BinarySearch(int n[], int k){
        boolean found = false;

        int pozicija = 0;
        int left = 0;
        int right = n.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2; //calc the middle id

            if(n[mid] == k){
                found = true;
                pozicija = mid;
            }

            if(n[mid] < k){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        if(!found){
            System.out.println("Ne postoi");
        }
        else{
            System.out.println(pozicija);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        BinarySearch(arr, k);
    }
}
