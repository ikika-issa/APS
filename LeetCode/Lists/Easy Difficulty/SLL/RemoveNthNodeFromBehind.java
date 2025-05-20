import java.util.Scanner;

public class RemoveNthNodeFromBehind {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        SLL<Integer> list = new SLL<>();

        for (int i = 0; i < N; i++) {
            list.insertLast(sc.nextInt());
        }

        int n = sc.nextInt();

        int left = list.size() - n;
        SLLNode<Integer> first = list.getFirst();
        int count = 0;

        while(first != null && count != left) {
            first = first.succ;
            count++;
        }

        list.delete(first);

        System.out.println(list);
    }
}
