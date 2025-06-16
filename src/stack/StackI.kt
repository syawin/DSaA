package stack;

import java.util.ArrayList;
import java.util.List;

// Reversing a String with Stacks
// Stack push/pop = O(1)
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class StackI {

    private final int   maxSize;
    private       int[] stackArray;
    private       int   top;

    public StackI(int maxSize)
    {
        this.maxSize    = maxSize;
        this.stackArray = new int[this.maxSize];
        this.top        = -1;
    }

    public StackI push(int c)
    {
        stackArray[++top] = c;
        return this;
    }

    public int peek()
    {
        return stackArray[top];
    }

    public void displayStack(String s)
    {
        System.out.print(s);
        System.out.print("Stack (bottom->top):\t");
        for (int i = 0; i < size(); i++) {
            System.out.print(peekN(i) + " ");
        }
        System.out.println();
    }

    public int size()
    {
        return top + 1;
    }

    public String peekN(int n)
    {
        return String.valueOf(stackArray[n]);
    }

    public List<Integer> popAll()
    {
        List<Integer> list = new ArrayList<>();
        while (!isEmpty()) {
            list.add(pop());
        }
        return list;
    }

    public boolean isEmpty()
    {
        return top == -1;
    }

    public int pop()
    {
        return (top >= 0) ? stackArray[top--] : 0;
    }

}
