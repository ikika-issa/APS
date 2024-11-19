//We are given N tasks with estimated completion time and the amount we can earn from that task. 
//Write an algorithm that finds the maximum earnings we can have for 40 hours. 
//Note that we can have a partial earning from a partially completed task.

//Solution: This problem is solved with the Greedy Algorithm in order to take account partially finished tasks as well.

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class MaxMoney {
    public static int maxEarnedMoney(int hours[], int earnings[]){
        int n = hours.length;

        double [][] tasks = new double[n][3]; //[0]: time, [1]: earning, [2]: earning/hour

        for(int i = 0; i < n; i++){
            tasks[i][0] = hours[i];
            tasks[i][1] = earnings[i];
            tasks[i][2] = (double)earnings[i] / hours[i];
        }

        Arrays.sort(tasks, Comparator.comparingDouble((double[]task)->task[2]).reversed());

        double maxEarnings = 0;
        int remainingHours = 40;

        for(double[] task : tasks){ //this is an enhanced for loop to access the columns
            int time = (int)task[0];
            double earning = task[1];

            if(remainingHours >= time){
                maxEarnings += earning;
                remainingHours -= time;
            }else{
                maxEarnings += task[2] * remainingHours;
                break;
            }
        }

        return (int)maxEarnings;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] hours = new int[n];
        int [] money = new int[n];


        for(int i = 0; i < n; i++){
            hours[i] = sc.nextInt();
            money[i] = sc.nextInt();
        }

        System.out.println(maxEarnedMoney(hours, money));
    }

}
