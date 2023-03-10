import java.io.IOException;

public class Infix {
    private StackX stackX;
    private String input;
    private String output = "";

    public Infix(String input) {
        this.input = input;
        stackX = new StackX(input.length());
    }

    public void gotOper(char opThis, int prec1) {
        while (!stackX.isEmpty()) {
            char opTop = stackX.pop();
            if (opTop == '(') {
                stackX.push(opTop);
                break;
            } else {
                int prec2;

                if (opTop == '+' || opTop == '-')
                    prec2 = 1;
                else
                    prec2 = 2;

                if (prec2 < prec1) {
                    stackX.push(opTop);
                    break;
                } else
                    output = output + opTop;
            }
        }
        stackX.push(opThis);
    }

    public String toPostfix() {
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            stackX.displayStack("For " + ch + " ");
            switch (ch) {
                case '+':
                case '-':
                    gotOper(ch, 1);
                    break;
                case '*':
                case '/':
                    gotOper(ch, 2);
                    break;
                case '(':
                    stackX.push(ch);
                    break;
                case ')':
                    gotParen(ch);
                    break;
                default:
                    output = output + ch;
                    break;
            }
        }
        while (!stackX.isEmpty()) {
            stackX.displayStack("While ");
            output = output + stackX.pop();
        }
        stackX.displayStack("End ");
        return output;
    }

    private void gotParen(char ch) {
        while (!stackX.isEmpty()) {
            char chx = stackX.pop();
            if (chx == '(')
                break;
            else
                output = output + chx;
        }
    }
}

class InfixApp {
    public static void main(String[] args) throws IOException {
        String input, output;
        while (true) {
            System.out.print("Enter infix:\t");
            System.out.flush();
            input = IOUtility.readNextString();
            if (input.equals(""))
                break;

            Infix infix = new Infix(input);
            output = infix.toPostfix();
            System.out.println("Postfix is " + output);
            System.out.println();
        }
    }
}
