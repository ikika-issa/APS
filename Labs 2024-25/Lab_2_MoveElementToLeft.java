//You are given a doubly-linked list of integers. 
//Additionally, there is one more integer M and a natural number k. 
//You need to find the first occurence of M in the list and move that node k times to the left.

import javax.swing.*;
import java.util.Scanner;

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

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

}

public class Main {
    public static void MoveLeft(DLL<Integer> list, int m, int k){
        if(list.length() == 1){
            System.out.println(list);
            return;
        }

        DLLNode<Integer> number = list.getFirst();
        DLLNode<Integer> target = null;
        DLLNode<Integer> previous = null;
        DLLNode<Integer> next = null;
        int position = 0;
        int left;
        boolean f = false; //to see if this number even exists

        if(list.getFirst() == list.getLast()){
            System.out.println(list);
            return;
        }

        while(number != null){
            if(number.element == m){
                f = true;
            }
            number = number.succ;
        }

        if(!f){
            System.out.println("Elementot ne postoi vo listata");
            System.out.println(list);
            return;
        }

        number = list.getFirst();

        while(number != null){
            if(number.element == m && number == list.getFirst()){
                target = number;
                list.deleteFirst();
                if(k - 1 == 0){
                    list.insertLast(target.element);
                    return;
                }
                else
                    break;
            }
            else if(number.element == m && number == list.getLast()){
                target = number;
                list.deleteLast();
                list.insertBefore(target.element, number.pred);
                k--;
                if(k == 0){
                    return;
                }
                break;
            }
            else if(number.element == m){
                target = number;
                previous = number.pred;
                next = number.succ;
                previous.succ = next;
                next.pred = previous;
                k--;
                break;
            }

            position++;
            number = number.succ;
        }

        DLLNode<Integer> found = null;

        if(position - k > 0){
            left = position - k;
            number = list.getFirst();
            for(int i = 0; i < left; i++){
                if(i == left-1){
                    found = number;
                }
                number = number.succ;
            }
            list.insertBefore(target.element, found);
        }
        else if(position - k == 0){
            list.insertLast(target.element);
        }
        else if(position - k < 0){
            number = list.getLast();
            left = Math.abs(position - k);

            for(int i = 0; i < left; i++){
                if(i == left - 1){
                    found = number;
                }
                number = number.pred;
            }
            list.insertBefore(target.element, found);
        }

        System.out.println(list);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        DLL<Integer> list = new DLL<>();

        for(int i = 0; i < n; i++){
            list.insertLast(sc.nextInt());
        }

        int m = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(list);
        MoveLeft(list, m, k);
    }
}
