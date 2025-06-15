package stack;

import queue.Dequeue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Reversing a String with Stacks
// Stack push/pop = O(1)
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
class StackX {
    private final int maxSize;
    private char[] stackArray;
    private int top;

    public StackX(int maxSize) {
        this.maxSize = maxSize;
        this.stackArray = new char[this.maxSize];
        this.top = -1;
    }

    public void push(char c) {
        stackArray[++top] = c;
    }

    public char pop() {
        return stackArray[top--];
    }

    public char peek() {
        return stackArray[top];
    }

    public String peekN(int n) {
        return String.valueOf(stackArray[n]);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack (bottom->top):\t");
        for (int i = 0; i < size(); i++) {
            System.out.print(peekN(i) + " ");
        }
        System.out.println();
    }
}

class StackXDQ {
    private final Dequeue dequeue;

    StackXDQ(int maxSize) {
        dequeue = new Dequeue(maxSize);
    }

    public void push(long l) {dequeue.insertLeft(l);}

    public long pop() {
        return dequeue.removeLeft();
    }

    public long peek() {
        long temp = dequeue.removeLeft();
        dequeue.insertLeft(temp);
        return temp;
    }

    public long size() {
        return dequeue.size();
    }

    public boolean isEmpty() {
        return dequeue.size() == 0;
    }
}

// Reverse a string using a stack
class Reverser {
    private String input;
    private String output;

    public Reverser(String input) {
        this.input = input;
    }

    @SuppressWarnings("StringConcatenationInLoop")
    public String doReverse() {
        int stackSize = input.length();
        StackX stackX = new StackX(stackSize);
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            stackX.push(c);
        }
        output = "";
        while (!stackX.isEmpty()) {
            char c = stackX.pop();
            output += c;
        }
        return output;
    }
}

// Use a stack to match brackets
class BracketChecker {
    private final String input;

    public BracketChecker(String input) {
        this.input = input;
    }

    public void check() {
        int stackSize = input.length();
        StackX stackX = new StackX(stackSize);
        for (int i = 0; i < stackSize; i++) {
            char c = input.charAt(i);
            switch (c) {
                case '{':
                case '[':
                case '(':
                    stackX.push(c);
                    break;
                case '}':
                case ']':
                case ')':
                    if (!stackX.isEmpty()) {
                        char pop = stackX.pop();
                        if ((c == '}' && pop != '{')
                                || (c == ']' && pop != '[')
                                || (c == ')' && pop != '(')) {
                            System.out.println("Error: " + c + " at " + i);
                        }
                    } else
                        System.out.println("Error: " + c + " at " + i);
                    break;
                default:
                    break;
            }
        }
        if (!stackX.isEmpty()) {
            System.out.println("Error: missing closing delimiter");
        }
    }
}

abstract class IOUtility {
    static String readNextString() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.readLine();
    }
}

class ReverserApp {
    public static void main(String[] args) throws IOException {
        String input, output;
        while (true) {
            System.out.print("Enter a string:");
            System.out.flush();
            input = IOUtility.readNextString();
            if (input.equals(""))
                break;

            Reverser reverser = new Reverser(input);
            output = reverser.doReverse();
            System.out.println("Reversed:\t" + output);
        }
    }

}

class BracketsApp {
    public static void main(String[] args) throws IOException {
        String input;
        while (true) {
            System.out.print("\tEnter string containing delimiters:\t");
            System.out.flush();
            input = IOUtility.readNextString();
            if (input.equals("")) {
                break;
            }
            BracketChecker checker = new BracketChecker(input);
            checker.check();
        }
    }
}
