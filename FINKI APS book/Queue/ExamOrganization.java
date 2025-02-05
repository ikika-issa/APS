//Влез: Во влезот е даден прво броjот на студенти кои се приjавиле само за е-тест, а потоа се наведуваат студентите според редоследот на приjавување за е-тест, потоа истото за студентите кои се приjавиле само за задачи, 
//па на краj студентите кои се приjавиле и за двете.
//Излез: На излез треба да се испечатат студентите според редоследот по коj влегле да полагаат прво за е-тест, потоа за задачи, соодветно по термини (еден термин има 20 студенти).

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


public class ExamOrganization {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedQueue<String> queue = new LinkedQueue<>(); //final queue
        LinkedQueue<String> eTest = new LinkedQueue<>();
        LinkedQueue<String> problems = new LinkedQueue<>();
        LinkedQueue<String> both = new LinkedQueue<>();
        String ss = "";

        int M = sc.nextInt();

        for(int i = 0; i < M; i++) {
            eTest.enqueue(sc.next());
        }

        M = sc.nextInt();

        for(int i = 0; i < M; i++) {
            problems.enqueue(sc.next());
        }

        M = sc.nextInt();

        for(int i = 0; i < M; i++) {
            both.enqueue(sc.next());
        }


        while(!both.isEmpty()) {
            String s = both.dequeue();
            eTest.enqueue(s);
            problems.enqueue(s);
        }


        System.out.println("Polagaat e-test:");

        int counter = 0;
        int i = 1;

        while(!eTest.isEmpty()) {
            if(counter == 0 || counter == 20){
                System.out.println("termin " + i);
                i++;
                counter = 0;
            }
            counter++;
            System.out.println(eTest.dequeue());
        }

        System.out.println("Polagaat zadaci:");

        counter = 0;
        i = 1;
        while(!problems.isEmpty()) {
            if(counter == 0 || counter == 20){
                System.out.println("termin " + i);
                i++;
                counter = 0;
            }
            counter++;
            System.out.println(problems.dequeue());
        }


    }
}
