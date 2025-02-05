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

public class InfixToPostfix{
    public static int precedence(char c){
        switch(c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        LinkedStack<Character> operations = new LinkedStack<>();
        String result = "";

        for(int i = 0; i < line.length(); i++){
            char c = line.charAt(i);
            if(Character.isLetterOrDigit(c)){
                result += c + " ";
            }else if(c == '('){
                operations.push(c);

            }else if(c == ')'){
                while(!operations.isEmpty() && operations.peek() != '('){
                    result += operations.pop() + " ";
                }
                operations.pop();

            }else{
                while(!operations.isEmpty() && precedence(c) <= precedence(operations.peek())){
                    result += operations.pop() + " ";
                }
                operations.push(c);
            }
        }

        while(!operations.isEmpty()){
            result += operations.pop() + " ";
        }

        System.out.println(result);
    }
}
