//Во една градина имаа одреден броj на растениjа. Секое од растениjата е третирано со одредена количина на пестициди. После секоj ден, ако некое растение има повеке пестициди од растението што е лево од него 
//(односно е послабо), истото умира.
//Со дадени инициjални вредности за количината на пестициди во секое растение, треба да се одреди после коj ден по ред (броjот на денот) нема веке да умира ни едно растение, 
//односно после коj ден нема да има растениjа кои имаат повеке пестициди од тие што се до нив од лева страна.
//Влез:
//7
//6 5 8 4 7 10 9
//Излез:
//2
//Влез:
//5
//1 1 1 1 1
//Излез:
//0

import java.util.NoSuchElementException;
import java.util.Scanner;

interface Stack<E> {
    public boolean isEmpty();

    public E peek();

    public void clear();

    public void push(E x);

    public E pop();

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


class LinkedStack<E> implements Stack<E> {
    private SLLNode<E> top;
    int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    public String toString() {
        SLLNode<E> current = top;
        StringBuilder s = new StringBuilder();
        while (current != null) {
            s.append(current.element);
            s.append(" ");
            current = current.succ;
        }
        return s.toString();
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public void clear() {
        // Go prazni stekot.
        top = null;
        size = 0;
    }

    public E peek() {
        if (top == null)
            throw new NoSuchElementException();
        return top.element;
    }

    public void push(E x) {
        top = new SLLNode<E>(x, top);
        size++;
    }

    public int size() {
        return size;
    }

    public E pop() {
        if (top == null)
            throw new NoSuchElementException();
        E topElem = top.element;
        size--;
        top = top.succ;
        return topElem;
    }

}

public class PoisonousPlants{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        LinkedStack<Integer> stack1 = new LinkedStack<>();

        int [] pesticides = new int[N];
        int [] daysToDie = new int[N]; //days for each plant to die

        for(int i = 0; i < N; i++){
            pesticides[i] = sc.nextInt();
        }

        int maxDays = 0;

        for(int i = 0; i < N; i++){
            int days = 0;

            while(!stack1.isEmpty() && pesticides[i] > pesticides[stack1.peek()]){
                days = Math.max(days, daysToDie[stack1.pop()]);
            }

            if(!stack1.isEmpty()){
                daysToDie[i] = days + 1;
            }

            maxDays = Math.max(maxDays, days);

            stack1.push(i);
        }

        System.out.println(maxDays);
    }
}
