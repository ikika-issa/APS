//Se odrzuvaat konsultacii. Ima studenti so tri tipa na prashanja: kratki, zadachi i dvete. Vleguvaat po 1 so kratko pa 1 so zadachi etc..Ako nema vekje lugje so kratki prashanja, se dodava eden shto ima prashanja za dvete.
//Potoa, istiot student se dodava na zadachite redicata i obratno. 
//4
//IlinkaIvanoska
//IgorKulev
//MagdalenaKostoska
//HristinaMihajloska
//2
//AnastasMishev
//VladimirTrajkovik
//1
//SlobodanKalajdziski
//Output:
//IlinkaIvanoska
//AnastasMishev
//IgorKulev
//VladimirTrajkovik
//MagdalenaKostoska
//SlobodanKalajdziski
//HristinaMihajloska
//SlobodanKalajdziski

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


public class Consultations2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedQueue<String> queue = new LinkedQueue<>(); //final queue
        LinkedQueue<String> problems = new LinkedQueue<>();
        LinkedQueue<String> shortQuestions = new LinkedQueue<>();
        LinkedQueue<String> both = new LinkedQueue<>();


        int N = sc.nextInt();

        for(int i = 0; i < N; i++) {
            String b = sc.next();
            shortQuestions.enqueue(b);
        }

        N = sc.nextInt();

        for(int i = 0; i < N; i++) {
            String b = sc.next();
            problems.enqueue(b);
        }

        N = sc.nextInt();

        for(int i = 0; i < N; i++) {
            String b = sc.next();
            both.enqueue(b);
        }
        
        
        while (!shortQuestions.isEmpty() || !problems.isEmpty() || !both.isEmpty()) {
            if (!shortQuestions.isEmpty()) {
                queue.enqueue(shortQuestions.dequeue());
            } else if (!both.isEmpty()) {
                String person = both.dequeue();
                queue.enqueue(person);
                problems.enqueue(person);
            }
            
            if (!problems.isEmpty()) {
                queue.enqueue(problems.dequeue());
            } else if (!both.isEmpty()) {
                String person = both.dequeue();
                queue.enqueue(person);
                shortQuestions.enqueue(person);
            }
        }

        while(!queue.isEmpty()){
            System.out.println(queue.dequeue());
        }
    }
}
