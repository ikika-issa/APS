//We are given N scheduled meetings. Write an algorithm that finds the minimum number of meeting rooms needed to schedule those meetings.

//Input: The first number in the input is the number of meetings N, then in the next N lines are the start and end time for each of the meetings.

//Output: The minimum number of needed meeting rooms to schedule the meetings.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main{
    public static int minimumNumberOfRooms(int arr[], int dep[]){
        Arrays.sort(arr);
        Arrays.sort(dep);

        int rooms = 0;
        int minimumRooms = 0;

        int i = 0, j = 0;

        while(i < arr.length && j < dep.length){
            if(arr[i] <= dep[j]){
                rooms++;
                minimumRooms = Math.max(minimumRooms, rooms);
                i++;
            }else{
                rooms--;
                j++;
            }
        }

        return minimumRooms;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] start = new int[n];
        int [] end = new int[n];


        for(int i = 0; i < n; i++){
           start[i] = sc.nextInt();
           end[i] = sc.nextInt();
        }

        System.out.println(minimumNumberOfRooms(start, end));
    }

}
