//Given a sequence of N natural numbers. The array should be sorted using the so-called shaker (cocktail) sort. 
//This sort is a variation of the bubble sort in that in each iteration the array is traversed twice. 
//In the first pass, the smallest element is moved to the beginning of the array, and in the second pass, the largest element is moved to the end.

//In the first line of the input, the number of elements in the array N is given, and in the second line, the numbers are given. 
//The output should print the string after each pass in a separate line.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {

    static void shakerSort(int a[], int n)
    {
        boolean swapped = true;
        int start = 0;
        int end = n-1;


        while(swapped) {

            swapped = false;

            for (int i = end-1; i >= start; i--) {

                if (a[i] > a[i+1]) {
                    int temp = a[i];
                    a[i]=a[i+1];
                    a[i+1]=temp;
                    swapped = true;
                }
            }

            start++;

            for (int i = 0; i < n; i++) {
                System.out.print(a[i] + " ");
            }
            
            System.out.print("\n");

            for (int i = start; i < end; i++) {

                if (a[i] > a[i+1]) {
                    int temp = a[i];
                    a[i]=a[i+1];
                    a[i+1]=temp;
                    swapped = true;
                }
            }

            end--;

            for (int i = 0; i < n; i++) {
                System.out.print(a[i] + " ");
            }
            
            System.out.println();

        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}
