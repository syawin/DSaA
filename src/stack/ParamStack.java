package stack;

import recursion.Params;

public class ParamStack {
    private final int maxSize;
    private Params[] stackArray;
    private int top;

    public ParamStack(int maxSize) {
        this.maxSize = maxSize;
        this.stackArray = new Params[this.maxSize];
        this.top = -1;
    }

    public void push(Params c) {
        stackArray[++top] = c;
    }

    public Params pop() {
        return stackArray[top--];
    }

    public Params peek() {
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
