//Дадени се две двоjно поврзани листи чии што jазли содржат по една природен броj. Од првата листа треба да се избришат сите поjавувања на втората листа (поjавување на една листа во друга значи првата листа да е 
//подлиста на втората).Jазлите што ´ке останат во првата листа треба да се прикажат на излез. Ако не остане ниту еден jазел се печати Prazna lista.
//Влез:
//22
//1 2 3 4 5 6 1 2 3 4 5 6 1 2 6 5 1 3 4 1 5 2
//3
//4 5 6 
//Излез:
//1 2 3 1 2 3 1 2 6 5 1 3 4 1 5 2

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
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
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

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
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

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }
}

public class DLL_DeleteSublists{

    public static void removeSublist(DLL<Integer> list1, DLL<Integer> list2) {
        DLLNode<Integer> current = list1.getFirst();
        DLLNode<Integer> subFirst = list2.getFirst();

        while (current != null) {
            DLLNode<Integer> temp1 = current;
            DLLNode<Integer> temp2 = subFirst;
            
            while (temp1 != null && temp2 != null && temp1.element.equals(temp2.element)) {
                temp1 = temp1.succ;
                temp2 = temp2.succ;
            }
            
            if (temp2 == null) {
                DLLNode<Integer> toDelete = current;
                while (toDelete != temp1) {
                    DLLNode<Integer> next = toDelete.succ;
                    list1.delete(toDelete);
                    toDelete = next;
                }
                current = temp1;
                
            } else {
                current = current.succ;
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        DLL<Integer> list = new DLL<>();

        for(int i = 0; i < N; i++){
            list.insertLast(sc.nextInt());
        }

        int M = sc.nextInt();
        DLL<Integer> subList = new DLL<>();

        for(int i = 0; i < M; i++){
            subList.insertLast(sc.nextInt());
        }


        removeSublist(list, subList);
        System.out.println(list);
    }
}
