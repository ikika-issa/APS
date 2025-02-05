//Да се направи имплементациjа за нова податочна структура QuasiStack коjа што ке биде неограничена структура. Новата структура треба да може да ги задоволи следните операции:
//• вметнување елемент – Вметнувањето елемент секогаш е вметнување на врвот на податочната структура. Оваа операциjа треба да се имплементира со сложеност O(1).
//• вадење елемент – Вадењето елемент од структурата може да биде вадење на елемент од врвот на структурата или од дното. Треба секогаш да се извади поголемиот елемент, при споредба на елементитe на врвот и дното.
//Доколку се еднакви го вади елементот од дното. Оваа операциjа треба да се имплементира со сложеност O(1).
//• да се провери дали структурата е празна
//• да се провери коj е елементот на врв на структурата
//• да се провери коj е елементот на дното на структурата

import java.util.NoSuchElementException;
import java.util.Scanner;

interface Stack<E> {
    public boolean isEmpty();

    public E peekTop();

    public E peekBottom();
  
    public void clear();

    public void push(E x);

    public E pop();
}

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class QuasiStack<E extends Comparable<E>> implements Stack<E> {
    private DLLNode<E> top, bottom;
    int size;

    public QuasiStack() {
        top = null;
        bottom = null;
        size = 0;
    }

    public String toString() {
        DLLNode<E> current = top;
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
        bottom = null;
        size = 0;
    }

    public E peekTop() {
        if (top == null)
            throw new NoSuchElementException();
        return top.element;
    }

    public E peekBottom() {
        if (bottom == null)
            throw new NoSuchElementException();
        return bottom.element;
    }

    public void push(E x) {
        DLLNode<E> ins = new DLLNode<>(x, null, top);

        if(top == null){
            bottom = ins;
        }else{
            top.pred = ins;
        }
        top = ins;

        size++;
    }

    public int size() {
        return size;
    }

    public E pop() { //returns the bigger element between the top and bottom element
        if (top == null)
            throw new NoSuchElementException();
        E topElem = top.element;
        E bottomElem = bottom.element;

        if(top == bottom){
            top = null;
            bottom = null;
            return topElem;
        }

        size--;

        if(topElem.compareTo(bottomElem) == 1){
            top = top.succ;
            top.pred = null;
            return topElem;
        }else{
            bottom = bottom.pred;
            bottom.succ = null;
            return bottomElem;
        }
    }

}


public class NewStructure{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        QuasiStack<Integer> qs = new QuasiStack<>();

        int n = sc.nextInt();

        for(int i = 0; i < n; i++){
            int x = sc.nextInt();
            qs.push(x);
        }

        System.out.println(qs.peekTop());
        System.out.println(qs.peekBottom());

        while(!qs.isEmpty()){
            System.out.println(qs.pop());
        }
    }
}
