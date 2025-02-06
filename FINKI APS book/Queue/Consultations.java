//Влез: Во влезот е даден прво броjот на студенти кои се приjавиле за консултации АПС, а потоа се наведуваат студентите според редоследот на приjавување и се дава за коj тип прашање се приjавиле (A, B, C или D). 
//Следно се дава броjот на студенти кои се приjавиле за консултации ММС, а потоа се наведуваат студентите според редоследот на приjавување.
//Излез: На излез треба да се испечатат студентите според редоследот по коj влегле на консултации.
//PS: Ако се падне исти тип на прашање два пати, вториот студент оди на крајот од редот.
//Влез:
//3
//IlinkaIvanoska A
//MagdalenaKostoska A
//HristinaMihajloska B
//1
//IgorKulev
//Излез:
//IlinkaIvanoska
//IgorKulev
//HristinaMihajloska
//MagdalenaKostoska

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


public class Consultations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedQueue<String> queue = new LinkedQueue<>(); //final queue
        LinkedQueue<String> aps = new LinkedQueue<>();
        LinkedQueue<String> mms = new LinkedQueue<>();

        int N = sc.nextInt();
        boolean x = false;
        Map<String, String> questionType = new HashMap<>();

        for(int i = 0; i < N; i++){
            String name = sc.next();
            String question = sc.next();
            aps.enqueue(name);
            questionType.put(name, question);
        }

        N = sc.nextInt();

        for(int i = 0; i < N; i++){
            mms.enqueue(sc.next());
        }

        String last = "";
        String lastQuestion = "";

        while(!aps.isEmpty()){
            String name = aps.dequeue();
            String q = questionType.get(name);

            if(q.equals(lastQuestion)){
                last = name;
                name = mms.dequeue();
                queue.enqueue(name);
            }else{
                queue.enqueue(name);
            }
            
            lastQuestion = q;
        }

        queue.enqueue(last);

        while(!queue.isEmpty()){
            System.out.println(queue.dequeue());
        }
    }
}
