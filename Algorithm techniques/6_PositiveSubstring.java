//You are given a binary string S. A binary string is a string that contains only '1's and '0's. 
//A substring of a binary string is called positive if the number of '1's in the substring is strictly greater than the number of '0's. 
//Print the number of positive substrings for the given string.


import java.util.Arrays;
import java.util.Scanner;

public class BinaryStrings {
    public static void positiveSubstrings(String string){
        int maxCount = 0;

        for(int i = 0; i < string.length(); i++){
            int count = 0;
            int countZeros = 0;
            for(int j = i; j < string.length(); j++){
                if(string.charAt(j) == '1'){
                    count++;
                }
                else if(string.charAt(j) == '0'){
                    countZeros++;
                }

                if(count > countZeros){
                    maxCount++;
                }
            }
        }

        System.out.println(maxCount);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();

        positiveSubstrings(s);
    }
}
