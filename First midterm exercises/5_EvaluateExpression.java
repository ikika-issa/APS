//Write an algorithm that will calculate (evaluate) a mathematical expression that consists of numbers and operations for adding (+) and multiplying (*).


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char lastOperator = '+';

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0'); // Accumulate digit
            }

            if (!Character.isDigit(c) && c != ' ' || i == expression.length() - 1) {
                if (lastOperator == '+') {
                    stack.push(currentNumber); 
                } else if (lastOperator == '*') {
                    stack.push(stack.pop() * currentNumber); 
                }

                lastOperator = c;
                currentNumber = 0;
            }
        }
        
        int result = 0;
        
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}
