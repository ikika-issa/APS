import java.util.Scanner;

public class RemoveDuplicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> list = new SLL<>();

        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        SLLNode<Integer> node = list.getFirst();

        while(node != null){
            SLLNode<Integer> prev = node;
            SLLNode<Integer> node2 = node.succ;

            while (node2 != null) {
                if (node.element.equals(node2.element)) {
                    list.delete(node2);
                    node2 = prev.succ;
                } else {
                    prev = node2;
                    node2 = node2.succ;
                }
            }
            node = node.succ;
        }

        System.out.println(list);
    }
}
