package stack;

import java.io.IOException;

public class Postfix {

    private String       input;
    private IntegerStack integerStack;

    public Postfix(String input)
    {
        this.input = input;
    }

    public int eval()
    {
        integerStack = new IntegerStack(input.length());
        char ch;
        int i, num1, num2, temp = 0;

        for (i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            integerStack.displayStack(ch + " ");
            if (ch >= '0' && ch <= '9')
                integerStack.push((char) (ch - '0'));
            else {
                num2 = integerStack.pop();
                num1 = integerStack.pop();
                switch (ch) {
                    case '+' -> temp = num1 + num2;
                    case '-' -> temp = num1 - num2;
                    case '*' -> temp = num1 * num2;
                    case '/' -> temp = num1 / num2;
                }
                integerStack.push(temp);
            }
        }
        return integerStack.pop();
    }

}

class PostfixApp {

    public static void main(String[] args) throws IOException
    {
        String input;
        int output;

        while (true) {
            System.out.print("Enter postfix expression (single digit only): ");
            System.out.flush();
            input = IOUtility.readNextString();
            if (input.isEmpty()) break;
            Postfix postfix = new Postfix(input);
            output = postfix.eval();
            System.out.println("Evaluates to " + output);
        }
    }

}
