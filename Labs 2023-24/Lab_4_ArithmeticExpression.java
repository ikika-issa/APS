//An arithmetic expression is given. 
//An arithmetic expression is of the form (A+B) or (A-B) where A and B are both other arithmetic expressions or digits from 0-9. You need to evaluate the given expression.


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {

        if (l == r) {
            return c[l] - '0';
        }

        if (c[l] == '(' && c[r] == ')') {
            int count = 0;
            boolean isSurrounded = true;
            for (int i = l; i <= r; i++) {
                if (c[i] == '(') count++;
                if (c[i] == ')') count--;
                
                // If count drops to 0 before reaching the end, it's not fully surrounded
                if (count == 0 && i < r) {
                    isSurrounded = false;
                    break;
                }
            }
            
            // Recursively evaluate the inside without the outer parentheses if fully surrounded
            if (isSurrounded) {
                return presmetaj(c, l + 1, r - 1);
            }
        }

        // Find the main operator to split the expression
        int count = 0;
        for (int i = r; i >= l; i--) {
            if (c[i] == ')') count++;
            if (c[i] == '(') count--;
            
            // Look for the main operator at the "top level" of the current expression
            
            if (count == 0 && (c[i] == '+' || c[i] == '-')) {
                // Evaluate the left and right expressions around the operator
                int leftVal = presmetaj(c, l, i - 1);
                int rightVal = presmetaj(c, i + 1, r);
                
                return (c[i] == '+') ? leftVal + rightVal : leftVal - rightVal;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length - 1);
        System.out.println(rez);

        br.close();

    }
}
