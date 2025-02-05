//Check if the brackets are placed correctly.

import java.util.NoSuchElementException;
import java.util.Scanner;

interface Stack<E> {
    public boolean isEmpty();

    public E peek();

    public void clear();

    public void push(E x);

    public E pop();
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems; 
    private int depth; 

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        return (depth == 0);
    }

    public E peek() {
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }

    public void clear() {
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }

    public void push(E x) {
        elems[depth++] = x;
    }

    public int size() {
        return depth;
    }

    public E pop() {
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class Brackets {

    public static boolean isRight(char left, char right) {
        switch (left) {
            case '(': return (right == ')');
            case '[': return (right == ']');
            case '{': return (right == '}');
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        sc.close();

        boolean correct = true;
        ArrayStack<Character> bracketStack = new ArrayStack<>(1000);

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '{' || c == '(' || c == '[') {
                bracketStack.push(c);
            } else if (c == '}' || c == ')' || c == ']') {
                if (bracketStack.isEmpty()) {
                    correct = false;
                    break;
                }

                char left = bracketStack.pop();
                if (!isRight(left, c)) {
                    correct = false;
                    break;
                }
            }
        }

        if (!bracketStack.isEmpty()) {
            correct = false;
        }

        if (correct) {
            System.out.println(line + " ima korektni zagradi");
        } else {
            System.out.println(line + " ima nekorektni zagradi");
        }
    }
}
