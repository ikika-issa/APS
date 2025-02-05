//Додека Стефан ги потготвува испитите за полагање во jунска сесиjа, тоj има навика да ги чува сите книги на еден куп, една врз друга. 
//При пребарување на дадена книга коjа му е потребна, тоj секогаш ги трга прво наjгорните, една по една, се додека не jа земе книгата коjа му треба. 
//Штом ´ке jа извади таа книга, останатите кои биле над неа ги вра´ка во истиот редослед назад. Откако ´ке го научи дадениот предмет, jа вра´ка книгата одозгора врз сите други.
//Дадена е инициjалната поставеност на книгите на купот на Стефан (во редослед одоздола нагоре). Дадени се и испитите по распоред на полагање за jунска сесиjа. 
//Ваша задача е да одредите колку пати секоjа од книгите ´ке биде извадена и ставена назад на купот.
//Влез:
//7 3
//APS OS Мrezhi AOK Objektno Strukturno Kalkulus
//APS Objektno Мrezhi
//Излез: APS 3
//OS 1
//Мrezhi 2
//AOK 2
//Objektno 3
//Strukturno 3
//Kalkulus 3

import java.util.*;

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

public class Exams{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt();
        sc.nextLine();

        String line = sc.nextLine();
        String [] books = line.split(" ");

        line = sc.nextLine();
        String[] exams = line.split(" ");

        Map<String, Integer> bookCount = new HashMap<>();
        LinkedStack<String> bookStack = new LinkedStack<>();
        LinkedStack<String> tempStack = new LinkedStack<>();

        for(String book : books){
            bookStack.push(book);
            bookCount.put(book, 0);
        }

        for(String exam : exams){
            boolean found = false;

            while(!bookStack.isEmpty()){
                String book = bookStack.pop();
                bookCount.put(book, bookCount.get(book) + 1);

                if(!book.equals(exam)){
                    tempStack.push(book);
                }else{
                    found = true;
                    break;
                }
            }

            while(!tempStack.isEmpty()){
                String book = tempStack.pop();
                bookStack.push(book);
            }

            if(found){
                bookStack.push(exam);
            }
        }

        for(String book : books){
            System.out.println(book + " " + bookCount.get(book));
        }
    }
}

