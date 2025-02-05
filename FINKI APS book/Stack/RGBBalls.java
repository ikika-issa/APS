//Да се напише алгоритам со коj ´ке се имплементира играта “Поништување топчиња”. Во оваа игра на располагање имате топчиња во три различни бои (R-црвена, G-зелена и B-сина), обележани со знакот + или -. 
//Поништување на топчиња може да настане само доколку тие се од иста боjа и со спротивен знак. 
//На почеток се генерира една случаjна листа со топчиња. Ваша задача е од тоj влез, како доа´гаат топчињата да направите поништување и да кажете колку, од каков тип 
//(+ или -) и од коjа боjа фалат за да се поништат сите топчиња од влезот
//Влез: R+ G- G+ G+ R+ B- B+ R- G+ R- B- B+ B+ R+
//Парови кои може да се формираат од овоj список се: (R+,R-); (B+, B-); (BB+); (R+, R-); (G-, G+); (R+, R-) Остануваат без партнер: G+, G+, B+, R+
//Излез:
//4
//R- G- G- B+

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

public class RGBBalls{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedStack<String> red = new LinkedStack<>();
        LinkedStack<String> green = new LinkedStack<>();
        LinkedStack<String> blue = new LinkedStack<>();

        String line = sc.nextLine();

        String[] balls = line.split(" ");
        for(String ball : balls){
            if(ball.contains("R")){
                if(!red.isEmpty()){
                    if(red.peek().equals(ball)){
                        red.push(ball);
                    }
                    else{
                        red.pop();
                    }
                }else {
                    red.push(ball);
                }
            }

            if(ball.contains("G")){
                if(!green.isEmpty()){
                    if(green.peek().equals(ball)){
                        green.push(ball);
                    }
                    else{
                        green.pop();
                    }
                }else {
                    green.push(ball);
                }
            }

            if(ball.contains("B")){
                if(!blue.isEmpty()){
                    if(blue.peek().equals(ball)){
                        blue.push(ball);
                    }
                    else{
                        blue.pop();
                    }
                }else {
                    blue.push(ball);
                }
            }
        }

        line = "";
        int n = 0;


        while(!red.isEmpty()){
            n++;
            if(red.pop().charAt(1) == '+'){
                line += "R+ ";
            }
            else{
                line += "R- ";
            }
        }

        while(!green.isEmpty()){
            n++;
            if(green.pop().charAt(1) == '+'){
                line += "G+ ";
            }
            else{
                line += "G- ";
            }
        }

        while(!blue.isEmpty()){
            n++;
            if(blue.pop().charAt(1) == '+'){
                line += "B+ ";
            }
            else{
                line += "B- ";
            }
        }

        System.out.println(n);
        System.out.println(line);
    }
}
