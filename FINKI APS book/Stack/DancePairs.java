//Да се напише алгоритам со коj ´ке се изврши креирање на танцови парови по соодветни танц-групи во една танцова школа. Танцов пар се формира од машко и женско запишани на иста танцова група. 
//Во школата за танци на располагање има групи за основни танци О, стандардни танци S и латино танци L. Има уписен рок така што заинтересираните кандидати може да се упишат. 
//Со завршување на уписниот рок се врши формирање на танцови двоjки. Ваша задача е од добиениот список на сите запишани кандидати да направите соодветни парови и да кажете колку, од каков тип на кандидати
//(машко или женско) и за коjа танцова група фалат за да сите добиjат своj партнер.
//Влез:
//LM OZ OM OM LM SZ SM LZ OM LZ SZ SM SM LM
//4
//LZ SZ OZ OZ

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


public class DancePairs{
    public static void formPairs(LinkedStack<String> stack, List<String> unpaired) {
        LinkedStack<String> tempStack = new LinkedStack<>();
        while (!stack.isEmpty()) {
            String candidate = stack.pop();
            if (!tempStack.isEmpty() && !tempStack.peek().substring(1).equals(candidate.substring(1))) {
                tempStack.pop();
            } else {
                tempStack.push(candidate);
            }
        }

        while (!tempStack.isEmpty()) {
            unpaired.add(tempStack.pop());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        LinkedStack<String> stackO = new LinkedStack<>();
        LinkedStack<String> stackS = new LinkedStack<>();
        LinkedStack<String> stackL = new LinkedStack<>();

        List<String> unpaired = new ArrayList<>();

        for (String candidate : input) {
            String group = candidate.substring(0, 1);

            if (group.equals("O")) {
                stackO.push(candidate);
            } else if (group.equals("S")) {
                stackS.push(candidate);
            } else if (group.equals("L")) {
                stackL.push(candidate);
            }
        }

        formPairs(stackO, unpaired);
        formPairs(stackS, unpaired);
        formPairs(stackL, unpaired);

        if (unpaired.isEmpty()) {
            System.out.println("/");
        } else {
            System.out.println(unpaired.size() + "\n" + String.join(" ", unpaired));
        }
    }
}

