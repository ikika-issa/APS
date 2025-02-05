//Calculate the postfix
//Example:
//5 9 + 2 * 6 5 * +
//58.0

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


public class Postfix{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        sc.close();

        LinkedStack<Double> stack = new LinkedStack<>();
        Double r = null;

        for(int i = 0; i < line.length(); i++){
            char c = line.charAt(i);

            if(c ==' ') continue;
            else if(Character.isDigit(c)){
                stack.push((double)c - '0');
            }else{
                if(stack.size >= 2){
                    Double lastNum = stack.pop();
                    Double middleNum = stack.pop();

                    switch(c){
                        case '+': stack.push(middleNum + lastNum); break;
                        case '-': stack.push(middleNum - lastNum); break;
                        case '*': stack.push(middleNum * lastNum); break;
                        case '/': stack.push(middleNum / lastNum); break;
                    }
                }else{
                    System.out.println("Nevaliden vlez - nedostasuva operand na pozicija: " + i);
                }
            }
        }

        if(stack.size != 1){
            System.out.println("Nevaliden vlez - nedostasuva operator");
        }else{
            r = stack.pop();
        }

        System.out.println(r);
    }
}
