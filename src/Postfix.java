import java.io.IOException;

public class Postfix {
    private StackI stackI;
    private String input;

    public Postfix(String input) {
        this.input = input;
    }

    public int eval() {
        stackI = new StackI(input.length());
        char ch;
        int i, num1, num2, temp = 0;

        for (i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            stackI.displayStack(ch + " ");
            if (ch >= '0' && ch <= '9')
                stackI.push((char) (ch - '0'));
            else {
                num2 = stackI.pop();
                num1 = stackI.pop();
                switch (ch) {
                    case '+' -> temp = num1 + num2;
                    case '-' -> temp = num1 - num2;
                    case '*' -> temp = num1 * num2;
                    case '/' -> temp = num1 / num2;
                }
                stackI.push(temp);
            }
        }
        return stackI.pop();
    }
}

class PostfixApp {
    public static void main(String[] args) throws IOException {
        String input;
        int output;

        while (true) {
            System.out.print("Enter postfix expression (single digit only): ");
            System.out.flush();
            input = IOUtility.readNextString();
            if (input.equals(""))
                break;

            Postfix postfix = new Postfix(input);
            output = postfix.eval();
            System.out.println("Evaluates to " + output);
        }
    }
}
