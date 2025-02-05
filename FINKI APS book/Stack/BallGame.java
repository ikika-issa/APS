//Да се напише алгоритам со коj ´ке се имплементира играта за мобилен телефон “Подреди топчиња според боjа”. Во оваа игра на располагање имате топчиња во три различни бои (R-црвена, G-зелена и B-сина). 
//На екран имате 3 кутии. Во првата кутиjа се нао´гаат топчињата кои што ви ги доделува апликациjата на почеток на играта. Играта завршува кога ´ке се подредат топчињата според боjа (во
//третата кутиjа) и тоа во следниот редослед RGB (односно прво ´ке бидат црвените, па зелените и на краj сините топчиња), а другите кутии се празни. 
//Втората кутиjа може да jа користите како помошна при распределбата на топчињата.
//Притоа треба да се води грижа дека во еден момент само едно топче може да се вади или става од врвот на кутиjата. Исто така за оваа игра важи следново правило: 
//Доколку на влез доjдат последователно три топчиња од црвена боjа, тоа значи “бомба”. Ова значи поништување на сите топчиња последователно што се наогаат во таа кутиjа (што се црвена боjа), се додека не се 
//доjде до топче одразлична боjа.
//Влез:
//4
//R
//R
//R
//G
//Излез:
//G
//Влез:
//3
//B
//B
//B
//Излез:
//B B B


import java.util.List;
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

public class BallGame{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); 
        sc.nextLine(); 

        LinkedStack<String> inputStack = new LinkedStack<>();
        LinkedStack<String> helperStack = new LinkedStack<>();
        LinkedStack<String> finalStack = new LinkedStack<>();

        
        for (int i = 0; i < n; i++) {
            inputStack.push(sc.nextLine().trim());
        }
        
        LinkedStack<String> tempStack = new LinkedStack<>();
        while (!inputStack.isEmpty()) {
            tempStack.push(inputStack.pop());
        }
        
        while (!tempStack.isEmpty()) {
            String ball = tempStack.pop();

            
            helperStack.push(ball);
            
            if (helperStack.size() >= 3) {
                String top1 = helperStack.pop();
                String top2 = helperStack.pop();
                String top3 = helperStack.pop();

                if (top1.equals("R") && top2.equals("R") && top3.equals("R")) {
                    while (!helperStack.isEmpty() && helperStack.peek().equals("R")) {
                        helperStack.pop();
                    }
                } else {
                    helperStack.push(top3);
                    helperStack.push(top2);
                    helperStack.push(top1);
                }
            }
        }

        LinkedStack<String> redStack = new LinkedStack<>();
        Stack<String> greenStack = new LinkedStack<>();
        Stack<String> blueStack = new LinkedStack<>();

        while (!helperStack.isEmpty()) {
            String ball = helperStack.pop();
            if (ball.equals("R")) {
                redStack.push(ball);
            } else if (ball.equals("G")) {
                greenStack.push(ball);
            } else if (ball.equals("B")) {
                blueStack.push(ball);
            }
        }

        while (!blueStack.isEmpty()) finalStack.push(blueStack.pop());
        while (!greenStack.isEmpty()) finalStack.push(greenStack.pop());
        while (!redStack.isEmpty()) finalStack.push(redStack.pop());

        while (!finalStack.isEmpty()) {
            System.out.println(finalStack.pop());
        }
    }

}
