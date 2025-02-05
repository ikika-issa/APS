//Да се напише алгоритам со коj ´ке се имплементира играта “Направи молекула на вода”. Во оваа игра на располагање имате два типа атоми (H-водород, и Oкислород). 
//За да се направи молекула на вода (H2O) потребно е да имате два атоми на водород и еден атом на кислород. На почеток се генерира една случаjна секвенца од атоми. 
//Ваша задача е од тоj влез, како доагаат атомите да генерирате молекули и да кажете колку такви молекули се креирале, и кои атоми останале несврзани.
//Влез:
//H H O H H O H H O H H H H H O H O H O O H O O H H H
//Излез:
//8
//H
//O

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

public class WaterMolecules{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedStack<String> hydrogen = new LinkedStack<>();
        LinkedStack<String> oxygen = new LinkedStack<>();

        String line = sc.nextLine();

        String [] atoms = line.split(" ");

        for(String atom : atoms){
            if(atom.equals("O")){
                oxygen.push(atom);
            }
            else if(atom.equals("H")){
                hydrogen.push(atom);
            }
        }

        int hPairs = hydrogen.size() / 2;
        int oCount = oxygen.size();
        int molecules = Math.min(hPairs, oCount);

        System.out.println(molecules);

        for (int i = 0; i < molecules * 2; i++) {
            hydrogen.pop();
        }
        for (int i = 0; i < molecules; i++) {
            oxygen.pop();
        }

        while (!hydrogen.isEmpty()) {
            System.out.println(hydrogen.pop() + " ");
        }
        while (!oxygen.isEmpty()) {
            System.out.println(oxygen.pop() + " ");
        }
    }
}
