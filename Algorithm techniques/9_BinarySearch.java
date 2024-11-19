//We are given an array of N numbers. Using the method "divide and conquer" write a binary search algorithm that searches for a given number M. 
//If the number is found, print the position of the number in the array. 
//If the number is not in the array, print "Ne postoi".


import java.util.Scanner;

public class BinarySearch {
    public static int binarysearch(int arr[], int m){
        int position = -1;
        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2; //calc the middle ID

            if(arr[mid] == m){
                return mid;
            }

            if(arr[mid] > m){
                right = mid - 1;
            }
            else if(arr[mid] < m){
                left = mid + 1;
            }
        }
        return position;
    }

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int []arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        if(binarysearch(arr, m) == -1){
            System.out.println("Ne postoi");
        }else{
            System.out.println(binarysearch(arr, m));
        }
    }

}
