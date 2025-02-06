//Влез: Во влезот е даден прво броjот на асистенти и имињата на асистентите од наjмлад до наjстар. Следно се дава броjот на предмети за кои се потребни асистенти, па се наведуваат предметите и по колку 
//асистенти се потребни за секоj предмет. Потоа се дава броjот на асистенти кои се отсутни и списокот на тековно отсутните асистенти.
//Излез: На излез треба да се испечати предмет, па асистенти задолжени зачување на тоj предмет (за секоj од дадените предмети).
//Влез:
//4
//IlinkaIvanoska
//IgorKulev
//MagdalenaKostoska
//HristinaMihajloska
//3
//APS 3
//MIS 1
//OOS 2
//1
//HristinaMihajloska
//Излез:
//APS
//3
//IlinkaIvanoska
//IgorKulev
//MagdalenaKostoska
//MIS
//1
//IlinkaIvanoska
//OOS
//2
//IgorKulev
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


public class MidtermWeek {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedQueue<String> assistants = new LinkedQueue<>();

        int N = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < N; i++) {
            assistants.enqueue(sc.nextLine());
        }
        
        int numSubjects = sc.nextInt();
        sc.nextLine();
        LinkedHashMap<String, Integer> subjects = new LinkedHashMap<>();
        for (int i = 0; i < numSubjects; i++) {
            String subject = sc.next();
            int required = sc.nextInt();
            sc.nextLine();
            subjects.put(subject, required);
        }
        
        int numAbsent = sc.nextInt();
        sc.nextLine();
        Set<String> absent = new HashSet<>();
        for (int i = 0; i < numAbsent; i++) {
            absent.add(sc.nextLine());
        }
        
        LinkedHashMap<String, List<String>> assigned = new LinkedHashMap<>();
        
        for (String subject : subjects.keySet()) {
            int needed = subjects.get(subject);
            List<String> assignedAssistants = new ArrayList<>();

            while (needed > 0 && !assistants.isEmpty()) {
                String assistant = assistants.dequeue();
                if (!absent.contains(assistant)) {
                    assignedAssistants.add(assistant);
                    needed--;
                }
                assistants.enqueue(assistant);
            }

            assigned.put(subject, assignedAssistants);
        }

        for (String subject : assigned.keySet()) {
            System.out.println(subject);
            System.out.println(assigned.get(subject).size());
            for (String assistant : assigned.get(subject)) {
                System.out.println(assistant);
            }
        }
    }
}
