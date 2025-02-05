//Пред командантот на воjската наредени се сите воjници и во двоjно поврзана листа дадени се нивните ID-a. На командантот не му се допа´га како се наредени воjниците и решава да одбере два под-интервали од воjници и 
//да им ги замени местата, односно воjниците што се нао´гаат во едниот под-интервал ´ке ги смести во другиот, и обратно.
//Влез: Во првиот ред даден е броjот на воjници.
//Во вториот ред дадени се ID-то на секоj од воjниците. Во третиот ред дадени се два броеви, ID на првиот воjник и ID на последниот воjник од првиот интервал.
//Во четвртиот ред дадени се два броеви, ID на првиот воjник и ID на последниот воjник од вториот интервал.
//Излез: Да се испечати новиот редослед на воjниците (т.е. на нивните ID-a)
//Забелешка 1: Интервалите никогаш нема да се преклопуваат и ´ке содржат барем еден воjник. Целата низа ´ке содржи наjмалку два воjника.
//Забелешка 2: Обратете посебно внимание кога интервалите се еден до друг и кога некоj од интервалите почнува од првиот воjник или завршува со последниот воjник.
//Пример.
//Влез:
//10
//1 2 3 4 5 6 7 8 9 10
//1 5
//6 10
//Излез:
//6 7 8 9 10 1 2 3 4 5

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

public class DLL_Vojska{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        DLL<Integer> list = new DLL<>();

        for(int i = 0; i < N; i++){
            list.insertLast(sc.nextInt());
        }

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> firstID = null;
        DLLNode<Integer> secondID = null;
        DLLNode<Integer> thirdID = null;
        DLLNode<Integer> fourthID = null;


        while(first != null){
            if(firstID != null && secondID != null){
                break;
            }
            if(first.element.equals(a)){
                firstID = first;
            }
            if(first.element.equals(b)){
                secondID = first;
            }
            first = first.succ;
        }

        first = list.getFirst();

        while(first != null){
            if(thirdID != null && fourthID != null){
                break;
            }
            if(first.element.equals(c)){
                thirdID = first;
            }
            if(first.element.equals(d)){
                fourthID = first;
            }
            first = first.succ;
        }

        DLLNode<Integer> tmp;


        DLLNode<Integer> temp1 = firstID;
        DLLNode<Integer> temp2 = secondID;
        DLLNode<Integer> temp3 = thirdID;
        DLLNode<Integer> temp4 = fourthID;

        DLL<Integer> list1 = new DLL<>();

        first = firstID;
        int k = 1;
        int p = 1;

        while(first != null && first != secondID){
            k++;
            first = first.succ;
        }

        first = thirdID;
        while(first != null && first != fourthID){
            p++;
            first = first.succ;
        }

        first = firstID;
        while(first != null && first != secondID){
            list1.insertLast(first.element);
            first = first.succ;
        }

        while(temp3 != null && temp3.pred != temp4){
            list.insertBefore(temp3.element, firstID);
            temp3 = temp3.succ;
        }

        while(temp1 != null && temp1.pred != temp2){
            list.insertBefore(temp1.element, thirdID);
            temp1 = temp1.succ;
        }

        first = firstID;

        while(first != null && first.pred != secondID){
            if(k == 0){
                break;
            }

            k--;
            tmp = first;
            first = first.succ;
            list.delete(tmp);
        }

        first = thirdID;

        while(first != null && first.pred != fourthID){
            if(p == 0){
                break;
            }

            p--;
            tmp = first;
            first = first.succ;
            list.delete(tmp);
        }

        System.out.println(list);
    }
}
