//Ima dadeni vagoni. Tie shto se obelezani so 0 se nefunkcionalni. Treba da se spojat funkcionalnite vagoni za lokomotivata vo rastechki redosled.

import java.util.*;

interface Queue<E> {
    public boolean isEmpty();

    public int size();

    public E peek();

    public void clear();

    public void enqueue(E x);


    public E dequeue();
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


class LinkedQueue<E> implements Queue<E> {

    SLLNode<E> front, rear;
    int length;


    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
        return (length == 0);
    }

    public int size () {
        return length;
    }

    public E peek () {
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}


public class Trains {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        ArrayList<Integer> arr = new ArrayList<>();

        int N = sc.nextInt();

        for(int i = 0; i < N; i++){
            int x = sc.nextInt();
            if(x != 0){
                arr.add(x);
            }
        }

        arr.sort(Collections.reverseOrder());

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < arr.size(); i++){
            stack.push(arr.get(i));
        }

        while(!stack.isEmpty()){
            queue.enqueue(stack.pop());
        }

        while(!queue.isEmpty()){
            System.out.print(queue.dequeue() + " ");;
        }
    }
}
