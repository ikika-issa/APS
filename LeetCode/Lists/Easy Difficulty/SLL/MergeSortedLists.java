import java.util.Scanner;

public class MergeSortedLists {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> list = new SLL<>();
        SLL<Integer> merged = new SLL<>();

        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        n = sc.nextInt();

        SLL<Integer> list2 = new SLL<>();

        for (int i = 0; i < n; i++) {
            list2.insertLast(sc.nextInt());
        }

        SLLNode<Integer> first = list.getFirst();
        SLLNode<Integer> first2 = list2.getFirst();

        while(first2 != null && first != null) {
            if(first.element <= first2.element) {
                merged.insertLast(first.element);
                first = first.succ;
            }else{
                merged.insertLast(first2.element);
                first2 = first2.succ;
            }
        }

        while(first != null){
            merged.insertLast(first.element);
            first = first.succ;
        }

        while(first2 != null){
            merged.insertLast(first2.element);
            first2 = first2.succ;
        }

        System.out.println(merged);
    }
}
