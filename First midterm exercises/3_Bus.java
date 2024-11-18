//There were N adults and M children that wanted to travel from the bus station in FinTown to the neighbouring city MinTown. 
//The price of one ticket is 100 den. If an adult wants to travel with K children, 
//he would need to pay one ticket for him and K-1 tickets for the children 
//(the ticket for one of the children is free). The adults can also travel by themselves, 
//in which case they only pay one ticket. 
//Additionally we know that the children can't travel without being accompanied by an adult.

//In the first row the number N is given, and then in the second row the number M. 
//You need to calculate the minimum and the maximum value in den. that the passengers can pay for their tickets, 
//and print them in two separate lines. There will be at least one adult in the bus.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        int min = 0;
        int max = 0;

        if(M == 0){ //no kids
            System.out.println(N * 100);
            System.out.println(N * 100);
            return;
        }
        else if(N == 1){ //one adult
            System.out.println(M * 100);
            System.out.println(M * 100);
            return;
        }
        
        
        if(N > M){ //more adults than kids
            min = N * 100;
        }
        else if(N < M){
            min = M * 100;
        }
        max = N * 100 + (M - 1) * 100;

        System.out.println(min);
        System.out.println(max);
    }

}
