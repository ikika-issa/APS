//Peter is doing a cards trick. He has a deck of 51 cards (someone didn't return him one in the past), from which he lets you draw one card. 
//So that the trick is trustworthy, he doesn't know the card, but he knows on which position it is. 
//Peter's fault is that he doesn't know how to regularly shuffle a deck of cards, but he firstly takes the first seven cards, r
//everses their order (e.g. from 1 2 3 4 5 6 7 he arranges them to 7 6 5 4 3 2 1), then he takes one card from the reversed ones, 
//from the top of the deck, and he puts them at the end of the deck, until he does that for all the seven cards. With that he completes one deck shuffle.

//Your task is to make a simulation of this type of shuffling, so that for a given N-th card (1<=N<=51), you will count the numbers of this shuffles 
//Peter needs to do so that the drawn card will come to the top of the deck.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class card_trick {
    public static int count(int N) {
        Queue<Integer> cards = new LinkedList<>();
        Stack<Integer> inverted = new Stack<>();

        for (int i = 1; i <= 51; i++)
            cards.add(i);

        int count = 0;

        while (cards.peek() != N) {
            for (int i = 0; i < 7; i++)
                inverted.add(cards.remove());

            while (!inverted.isEmpty()) {
                cards.add(inverted.pop());
                cards.add(cards.remove());
            }

            count++;
        }

        return count;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
