//Given a sequence of N natural numbers. It is necessary to sort the sequence so that in the first part of the sequence the odd numbers from it will be sorted in ascending order, 
//and in the second part the even numbers will be sorted in descending order.
//In the first line of the input, the number of elements in the array N is given, and in the second line, the numbers are given. The output should print the sorted array.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OddEvenSort {
    static void oddEvenSort(int a[], int n)
    {
        int even[] = new int[n+1];
        int odd[] = new int[n+1];

        int idEven = 0;
        int idOdd = 0;

        for(int i = 0; i < n; i++){
            if(a[i] % 2 == 0){
                even[idEven++] = a[i];
            }
            else{
                odd[idOdd++] = a[i];
            }
        }

        //sorting odd numbers
        for(int i = 0; i < idOdd; i++){
            for(int j = 0; j < idOdd - i - 1 ; j++){
                int tmp;
                if(odd[j] > odd[j+1]){
                    tmp = odd[j];
                    odd[j] = odd[j+1];
                    odd[j + 1] = tmp;
                }
            }
        }

        //sorting even numbers
        for(int i = 0; i < idEven; i++){
            for(int j = 0; j < idEven - i - 1 ; j++){
                int tmp;
                if(even[j] < even[j+1]){
                    tmp = even[j];
                    even[j] = even[j+1];
                    even[j + 1] = tmp;
                }
            }
        }

        int k = 0;

        for(int i = 0; i < idOdd; i++) {
            a[i] = odd[i];
        }

        for(int i = idOdd; i < n; i++) {
            a[i] = even[k++];
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
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}
