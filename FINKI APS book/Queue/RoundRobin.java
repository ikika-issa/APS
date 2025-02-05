//Algorithm to simulate Round Robin
//Example:
//5
//A 40 2
//B 35 0
//C 28 10
//D 45 4
//E 32 2
//10
//Output:
//B A E D C B A E D C B A E D C B A E D D


import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

class Process implements Comparable<Process> {
    String name;
    int arrival_time, execution_time;

    public Process (String name, int arrival_time, int execution_time) {
        this.name = name;
        this.arrival_time = arrival_time;
        this.execution_time = execution_time;
    }

    @Override
    public int compareTo(Process o) {
        if(this.arrival_time > o.arrival_time) {
            return 1;
        }
        else if(this.arrival_time == o.arrival_time) {
            if(this.execution_time > o.execution_time) { //if the og execution time is longer, execute the other process
                return -1;
            }
            else{
                return 1;
            }
        }
        else{
            return -1;
        }
    }

    public void updateTime(int q) {
        if(execution_time < q){
            execution_time = 0;
        }else{
            execution_time -= q;
        }
    }

    public int getExecutionTime() {
        return execution_time;
    }

    public String getName(){
        return name;
    }
}

public class RoundRobin{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedQueue<Process> queue = new LinkedQueue<>();
        LinkedList<Process> processes = new LinkedList<>();

        int N = sc.nextInt();

        for(int i = 0; i < N; i++){
            String name = sc.next();
            int exec = sc.nextInt();
            int arrival = sc.nextInt();
            Process process = new Process(name, arrival, exec);
            processes.add(process);
        }

        int Q = sc.nextInt();

        Collections.sort(processes);

        for(int i = 0; i < processes.size(); i++){
            queue.enqueue(processes.get(i));
        }

        while(!queue.isEmpty()){
            Process p = queue.dequeue();
            p.updateTime(Q);
            if(p.getExecutionTime() != 0){
                queue.enqueue(p);
            }

            System.out.print(p.getName() + " ");
        }
    }
}
