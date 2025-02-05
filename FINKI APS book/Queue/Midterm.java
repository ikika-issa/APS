//Влез: Во влезот е даден прво капацитетот на студенти по термин (т.е. по колку студенти во еден термин може да полагаат). Следно се дава броjот и списокот на студенти кои истиот ден полагаат и Математика 
//(според редоследот по коj се приjавиле). Потоа се дава броjот и списокот на останатите студенти (според редоследот по коj се приjавиле). На краj се дава броj и список на студенти кои навистина полагаат Математика.
//Излез: На излез треба да се испечати броj на термин, па студентите кои полагаат во тоj термин.
//2
//4
//IlinkaIvanoska
//IgorKulev
//MagdalenaKostoska
//HristinaMihajloska
//3
//VladimirTrajkovik
//SlobodanKalajdziski
//AnastasMisev
//1
//IlinkaIvanoska
//Output:
//1
//IlinkaIvanoska
//VladimirTrajkovik
//2
//SlobodanKalajdziski
//AnastasMisev
//3
//IgorKulev
//MagdalenaKostoska
//4
//HristinaMihajloska

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
        LinkedQueue<String> queue = new LinkedQueue<>(); //final queue
        LinkedQueue<String> math = new LinkedQueue<>(); //3rd
        LinkedQueue<String> aps = new LinkedQueue<>(); //2nd
        LinkedQueue<String> math2 = new LinkedQueue<>(); //priority
        String ss = "";

        int M = sc.nextInt(); //kapacitet u termin
        int N = sc.nextInt(); //klk plagaat matematika

        for(int i = 0; i < N; i++) {
            String b = sc.next();
            math.enqueue(b);
        }

        N = sc.nextInt();

        for(int i = 0; i < N; i++) {
            String b = sc.next();
            aps.enqueue(b);
        }

        N = sc.nextInt();

        for(int i = 0; i < N; i++) {
            String b = sc.next();
            math2.enqueue(b);
        }


        while(!math2.isEmpty()){
            String s = math2.dequeue();
            queue.enqueue(s);
            ss += s + " ";
        }

        while(!aps.isEmpty()){
            String s = aps.dequeue();
            queue.enqueue(s);
            ss += s + " ";
        }

        while(!math.isEmpty()){
            String s = math.dequeue();
            if(!ss.contains(s)){
                queue.enqueue(s);
                ss += s + " ";
            }
        }

        int counter = 0;
        int i = 0;

        while(!queue.isEmpty()){
            if(counter == 0 || counter == M){
                System.out.println(++i);
                counter = 0;
            }
            System.out.println(queue.dequeue());
            counter++;
        }
    }
}
