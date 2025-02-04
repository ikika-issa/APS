//Дадена е еднострано поврзана листа чии што jазли содржат по еден природен броj. 
//Во дадената листа треба да се пронаjдат елементите со наjмала и наjголема вредност и потоа листата треба да се подели на две резултантни еднострано поврзани листи, т.ш.
//во првата листа треба да се сместат сите jазли кои содржат броеви поблиски до наjмалиот елемент отколку до наjголемиот елемент, а во втората сите jазли кои содржат броеви поблиски до наjголемиот елемент отколку
//до наjмалиот. Доколку елементот е на исто растоjание од наjмалиот и наjголемиот елемент тогаш се сместува во листата на елементи поблиски до наjмалот елемент. 
//Jазлите во резултантните листи се додаваат според редоследот по коj се поjавуваат во дадената листа. (Помош: броjот 3 е на растоjание 2 од броjот 1 и на растоjание 4 од броjот 7. 
//Следува дека броjот 3 е поблиску до броjот 1 отколку до броjот 7).
//Влез:
//9
//1 5 7 3 2 9 4 8 6
//Излез:
//1->5->3->2->4
//7->9->8->6

import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
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
            SLLNode<E> tmp = first;
            ret += tmp.element;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp.element;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
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

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}

public class SLL_SeparateMinMax{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        SLL<Integer> list = new SLL<>();

        for(int i = 0; i < N; i++){
            list.insertLast(sc.nextInt());
        }
        sc.close();

        SLLNode<Integer> first = list.getFirst();
        int min = first.element;
        int max = first.element;

        while(first != null){
            if(min > first.element){
                min = first.element;
            }
            if(max < first.element){
                max = first.element;
            }
            first = first.succ;
        }

        first = list.getFirst();

        SLL<Integer> minList = new SLL<>();
        SLL<Integer> maxList = new SLL<>();


        while(first != null){
            if(first.element - min <= max - first.element){
                minList.insertLast(first.element);
            }else{
                maxList.insertLast(first.element);
            }

            first = first.succ;
        }

        System.out.println(minList);
        System.out.println(maxList);

    }
}
